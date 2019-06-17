package com.example.xmljpademo.service;

import com.example.xmljpademo.dto.DeptManagerDto;
import com.example.xmljpademo.model.*;
import com.example.xmljpademo.repository.empsrepository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Executors;

//import javax.transaction.Transactional;

@Component
//@Transactional(value = "employeesTransactionManager")
public class EmployeeService {
    private static final Logger logger = LoggerFactory.getLogger(EmployeeService.class);
    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    EmployeeDetailRepository detailRepository;

    @Autowired
    DeptEmployeesRepository deptEmployeesRepository;

    @Autowired
    DeptManagerRepository deptManagerRepository;

    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    PhoneRepository phoneRepository;

    @Autowired
    SalaryRepository salaryRepository;

    @Autowired
    RedisSevice redisSevice;

    //@Scheduled(fixedDelay = 10000)
    public String saveToRedis(String mode) {
        Executors.newSingleThreadExecutor().execute(() -> redisSevice.db2redis(mode));
        return "begin merge data to redis";
    }
    public Employee getEmployeeCached(Long id) {
        String key = "em:" + id;
        Employee employee = redisSevice.getEmployee(key);
        if (employee != null) {
            return employee;
        } else {
            Optional<Employee> em = employeeRepository.findById(id);
            em.ifPresent(emp -> redisSevice.saveEmployee(emp));
            return  em.orElse(new Employee());
            //return employeeRepository.findById(id).orElse(new Employee());
        }
    }

    public Employee getEmployeeCached1(Long id) {
        String key = "em:" + id;
        Optional<Employee> employee = redisSevice.getEmployee1(key);
        return employee.orElseGet(()-> {
            Optional<Employee> em = employeeRepository.findById(id);
            em.ifPresent(emp -> redisSevice.saveEmployee(emp));
            return  em.orElse(new Employee());
        });
//        if (employee != null) {
//            return employee;
//        } else {
//            Optional<Employee> em = employeeRepository.findById(id);
//            em.ifPresent(emp -> redisSevice.saveEmployee(emp));
//            return  em.orElse(new Employee());
//            //return employeeRepository.findById(id).orElse(new Employee());
//        }
    }

    @Transactional(value = "employeesTransactionManager",propagation = Propagation.REQUIRES_NEW)
    public List<Phone> getPhones(Long id) {
        List<Phone> phones = phoneRepository.findAllById(Arrays.asList(id));

        if (phones.isEmpty()) {
            throw new RuntimeException("no phone");
        }
        return phones;
    }

    @Transactional(value = "employeesTransactionManager", propagation = Propagation.REQUIRES_NEW)
    public List<Salary> getSalaries(Long id) {
        return salaryRepository.findAllById(Arrays.asList(id));
    }

    @Transactional(value = "employeesTransactionManager")
    public Employee getEmployee(Long id) {
        String key = "em:" + id;
        Optional<Employee> emp = redisSevice.getEmployee1(key);
        return emp.orElseGet(()->{
            Optional<Employee> employee = employeeRepository.findById(id);
            employee.ifPresent(employee1 -> redisSevice.saveEmployee(employee1));
            return employee.get();
            //return  employeeRepository.findById(id).orElse(new Employee());
        });
    }

    @Transactional(value = "employeesTransactionManager", propagation = Propagation.REQUIRED)
    public EmployeeDetail getEmployeeDetail(Long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new RuntimeException("employee"));
        List<Salary> salaries = getSalaries(id);
        List<Phone> phones = getPhones(id);

        EmployeeDetail employeeDetail = new EmployeeDetail();
        employeeDetail.setSalaries(salaries);
        employeeDetail.setPhones(phones);
        return employeeDetail;
    }

    public EmployeeDetail getEmpDetail(Long id) {
        return  detailRepository.findById(id).orElse(new EmployeeDetail());
    }

    public DeptEmployee getDeptEmp(Long id) {return  deptEmployeesRepository.findById(id).orElse(new DeptEmployee()); }

    public DeptManager getDeptManager(EmployeeNO id) {
        return deptManagerRepository.findById(id).orElse(new DeptManager());
    }

    public DeptManager addDeptManager(DeptManagerDto deptManager) {
        Employee em = employeeRepository.getOne(deptManager.getEmployeeNo());
        Department department = departmentRepository.getOne(deptManager.getDeptNo());
        DeptManager deptManager1 = new DeptManager();
        deptManager1.setEmployee(em);
        deptManager1.setDepartment(department);
        deptManager1.setEmployeeNO(new EmployeeNO(deptManager.getEmployeeNo(), deptManager.getDeptNo()));
        deptManager1.setFromDate(deptManager.getFromDate());
        deptManager1.setToDate(deptManager.getToDate());
        deptManagerRepository.save(deptManager1);
        return deptManager1;
    }

    //@Transactional(value = "employeesTransactionManager", propagation = Propagation.REQUIRED)
    public Employee insertEmp(Employee employee) {
        Employee emp = employeeRepository.save(employee);
//        List<Phone> phones = getPhones(employee.getEmployeeNo());
//        logger.info(phones.toString());
        return emp;
    }

    @Transactional(value = "employeesTransactionManager", propagation = Propagation.REQUIRED)
    public Phone insertPhone(Phone phone) {
//        List<Phone> phones = getPhones(employee.getEmployeeNo());
////        logger.info(phones.toString());
        return phoneRepository.save(phone);
    }
}
