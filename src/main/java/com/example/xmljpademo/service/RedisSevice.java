package com.example.xmljpademo.service;

import com.example.xmljpademo.model.Employee;
import com.example.xmljpademo.repository.empsrepository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.hash.HashMapper;
import org.springframework.data.redis.hash.Jackson2HashMapper;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.*;

@Component
public class RedisSevice {

    private static final Logger logger = LoggerFactory.getLogger(RedisSevice.class);
    @Autowired
    EmployeeRepository employeeRepository;

    HashMapper<Object, String, Object> mapper = new Jackson2HashMapper(false);

    @Resource(name = "redisTemplate")
    HashOperations<String, String, Object> hashOperations;

    //@Resource(name = "redisTemplate")
    //@Qualifier("redisTemplate")
    @Autowired
    //@Resource
    //@Qualifier("redisTemplate")
    PipeLineHashOperations<String, String, Object> pipeLineHashOperations;

    BoundKeyOperations<String> boundKeyOperations;

    private int number = 0;

    @PostConstruct
    public void init(){
        //pipeLineHashOperations = new PipeLineHashOperations<String, String, Object>(hashOperations.getOperations())

    }

    public void db2redis(String mode) {
        Pageable pageable = PageRequest.of(0, 5000);
        Page<Employee> emPage = null;

        while (true) {
            emPage = employeeRepository.findAll(pageable);

            List<Employee> employeeList = emPage.getContent();
            logger.info("get page: "  + employeeList.size());
            //pipelineWrite(employeeList);
            if (mode.equals("pipeline")) {
                pipelineWrite(employeeList);

            } else {
                for (Employee em : employeeList) {
//                Map<String, Object> stringObjectMap = mapper.toHash(em);
//                hashOperations.putAll("em:" + em.getEmployeeNo(),stringObjectMap );
                    saveEmployee(em);
                }
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
//        hashOperations.getOperations().executePipelined((RedisCallback<Object>) connection -> {
//            for (Employee em : employeeList) {
//                Map<String, Object> stringObjectMap = mapper.toHash(em);
//                String key = "em:" + em.getEmployeeNo();
//                //connection.hashCommands().hMSet();
//                hashOperations.putAll(key, stringObjectMap);
//            }
//            return null;
//        });
        pipeLineHashOperations.putAll(employeeList);
    }


//    private class PipeLineHashOperations<K, HK, HV>{
//        RedisTemplate<K, Object> template;
//
//        PipeLineHashOperations(RedisTemplate<K, ?> template) {
//            this.template = (RedisTemplate<K, Object>)template;
//        }
//
//        public void putAll(K key, Map<? extends HK, ? extends HV> m) {
//            if (m.isEmpty()) {
//                return;
//            }
//
//            byte[] rawKey = rawKey(key);
//
//            Map<byte[], byte[]> hashes = new LinkedHashMap<>(m.size());
//
//            for (Map.Entry<? extends HK, ? extends HV> entry : m.entrySet()) {
//                hashes.put(rawHashKey(entry.getKey()), rawHashValue(entry.getValue()));
//            }
//
//            RedisCallback<?> redisCallback = new RedisCallback<Object>() {
//                @Override
//                public Object doInRedis(RedisConnection connection) throws DataAccessException {
//                    return null;
//                }
//            };
//            template.executePipelined(redisCallback);
//        }
//
//        RedisSerializer keySerializer() {
//            return template.getKeySerializer();
//        }
//
//        RedisSerializer valueSerializer() {
//            return template.getValueSerializer();
//        }
//
//        RedisSerializer hashKeySerializer() {
//            return template.getHashKeySerializer();
//        }
//
//        RedisSerializer hashValueSerializer() {
//            return template.getHashValueSerializer();
//        }
//
//        RedisSerializer stringSerializer() {
//            return template.getStringSerializer();
//        }
//
//        byte[] rawKey(Object key) {
//
//            Assert.notNull(key, "non null key required");
//
//            if (keySerializer() == null && key instanceof byte[]) {
//                return (byte[]) key;
//            }
//
//            return keySerializer().serialize(key);
//        }
//
//        <HK> byte[] rawHashKey(HK hashKey) {
//            Assert.notNull(hashKey, "non null hash key required");
//            if (hashKeySerializer() == null && hashKey instanceof byte[]) {
//                return (byte[]) hashKey;
//            }
//            return hashKeySerializer().serialize(hashKey);
//        }
//
//        <HV> byte[] rawHashValue(HV value) {
//
//            if (hashValueSerializer() == null & value instanceof byte[]) {
//                return (byte[]) value;
//            }
//            return hashValueSerializer().serialize(value);
//        }
//    }

}
