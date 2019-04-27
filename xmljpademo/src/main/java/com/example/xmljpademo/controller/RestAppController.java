package com.example.xmljpademo.controller;

import com.example.xmljpademo.model.Employee;
import com.example.xmljpademo.model.EmployeeDetail;
import com.example.xmljpademo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestAppController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping("/employees/{id}")
    Employee getEmployee(@PathVariable("id") Long id) {
        return employeeService.getEmployee(id);
    }

    @GetMapping("/employeesdetail/{id}")
    EmployeeDetail getEmployeeDetail(@PathVariable("id") Long id) {
        return employeeService.getEmpDetail(id);
    }

}
