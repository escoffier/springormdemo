package com.example.xmljpademo.service;

import com.example.xmljpademo.model.Employee;
import com.example.xmljpademo.model.EmployeeDetail;
import com.example.xmljpademo.repository.EmployeeDetailRepository;
import com.example.xmljpademo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmployeeService {

    @Autowired
    EmployeeRepository repository;

    @Autowired
    EmployeeDetailRepository detailRepository;

    public Employee getEmployee(Long id) {
         return repository.findById(id).orElse(new Employee());
    }

    public EmployeeDetail getEmpDetail(Long id) {return  detailRepository.findById(id).orElse(new EmployeeDetail());}
}
