package com.example.xmljpademo.service;

import com.example.xmljpademo.model.Employee;
import com.example.xmljpademo.repository.empsrepository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.BoundKeyOperations;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.hash.HashMapper;
import org.springframework.data.redis.hash.Jackson2HashMapper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
public class RedisSevice {

    private static final Logger logger = LoggerFactory.getLogger(RedisSevice.class);
    @Autowired
    EmployeeRepository employeeRepository;

    HashMapper<Object, String, Object> mapper = new Jackson2HashMapper(false);

    @Resource(name = "redisTemplate")
    HashOperations<String, String, Object> hashOperations;

    BoundKeyOperations<String> boundKeyOperations;

    private int number = 0;

    public void start() {
        Pageable pageable = PageRequest.of(0, 1000);
        Page<Employee> emPage = null;

        while (true) {
            emPage = employeeRepository.findAll(pageable);

            List<Employee> employeeList = emPage.getContent();
            logger.info("get page: "  + employeeList.size());
            //pipelineWrite(employeeList);
            for (Employee em : employeeList) {
//                Map<String, Object> stringObjectMap = mapper.toHash(em);
//                hashOperations.putAll("em:" + em.getEmployeeNo(),stringObjectMap );
                saveEmployee(em);
            }

            if (!emPage.hasNext()) {
                break;
            }
            pageable = emPage.nextPageable();
        }
    }

    public Employee getEmployee(String id) {
        if(!hashOperations.getOperations().hasKey(id)) {
            return null;
        }

        Map map = hashOperations.entries(id);
        Employee employee = (Employee) mapper.fromHash(map);
        logger.info(employee.toString());
        return employee;
    }

    public Optional<Employee> getEmployee1(String id) {
        Employee employee = null;
        if(hashOperations.getOperations().hasKey(id)) {
            Map map = hashOperations.entries(id);
            employee = (Employee) mapper.fromHash(map);
            logger.info(employee.toString());
        }
        return Optional.ofNullable(employee);
    }

    public void saveEmployee(Employee employee) {
        Map<String, Object> stringObjectMap = mapper.toHash(employee);
        hashOperations.putAll("em:" + employee.getEmployeeNo(),stringObjectMap );
    }

//    public boolean isExist(String id) {
//        hashOperations.getOperations().e
//    }

    private void pipelineWrite(List<Employee> employeeList) {
        hashOperations.getOperations().executePipelined((RedisCallback<Object>) connection -> {
            for (Employee em : employeeList) {
                Map<String, Object> stringObjectMap = mapper.toHash(em);
                String key = "em:" + em.getEmployeeNo();
                hashOperations.putAll(key, stringObjectMap);
            }
            return null;
        });
    }

}
