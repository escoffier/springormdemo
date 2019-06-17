package com.example.xmljpademo.service;

import com.example.xmljpademo.model.Employee;
import com.example.xmljpademo.model.Phone;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class PropagationService {
    private static final Logger logger = LoggerFactory.getLogger(PropagationService.class);

    @Autowired
    EmployeeService employeeService;

    @Autowired
    PhoneService phoneService;

    @Transactional(value = "employeesTransactionManager", propagation = Propagation.REQUIRED)
    public Employee insertEmp(Employee employee) {
        Employee emp = employeeService.insertEmp(employee);
//        List<Phone> phones = phoneService.getPhones(employee.getEmployeeNo());
//        logger.info(phones.toString());

        try {
            List<Phone> phones = phoneService.getPhones(employee.getEmployeeNo());
            logger.info(phones.toString());
        } catch (RuntimeException ex) {
            logger.error(ex.getMessage());
        }
        return emp;
    }
}
