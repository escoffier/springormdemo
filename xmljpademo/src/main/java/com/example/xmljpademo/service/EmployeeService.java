package com.example.xmljpademo.service;

import com.example.xmljpademo.model.DeptEmployee;
import com.example.xmljpademo.model.Employee;
import com.example.xmljpademo.model.EmployeeDetail;
import com.example.xmljpademo.repository.empsrepository.DeptEmployeesRepository;
import com.example.xmljpademo.repository.empsrepository.EmployeeDetailRepository;
import com.example.xmljpademo.repository.empsrepository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

//import javax.transaction.Transactional;

@Component
@Transactional(value = "employeesTransactionManager")
public class EmployeeService {

    @Autowired
    EmployeeRepository repository;

    @Autowired
    EmployeeDetailRepository detailRepository;

    @Autowired
    DeptEmployeesRepository deptEmployeesRepository;

    public Employee getEmployee(Long id) {
         return repository.findById(id).orElse(new Employee());
    }

    public EmployeeDetail getEmpDetail(Long id) {
        return  detailRepository.findById(id).orElse(new EmployeeDetail());
    }

    public DeptEmployee getDeptEmp(Long id) {return  deptEmployeesRepository.findById(id).orElse(new DeptEmployee()); }
}
