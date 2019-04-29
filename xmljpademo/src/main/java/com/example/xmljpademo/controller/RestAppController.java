package com.example.xmljpademo.controller;

import com.example.xmljpademo.model.DeptEmployee;
import com.example.xmljpademo.model.Employee;
import com.example.xmljpademo.model.EmployeeDetail;
import com.example.xmljpademo.model.post.Post;
import com.example.xmljpademo.service.EmployeeService;
import com.example.xmljpademo.service.PostsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RestAppController {

    private static final Logger logger = LoggerFactory.getLogger(RestAppController.class);
    @Autowired
    EmployeeService employeeService;

    @Autowired
    PostsService postsService;


    @GetMapping("/employees/{id}")
    Employee getEmployee(@PathVariable("id") Long id) {
        return employeeService.getEmployee(id);
    }

    @GetMapping("/employeesdetail/{id}")
    EmployeeDetail getEmployeeDetail(@PathVariable("id") Long id) {
        EmployeeDetail detail = employeeService.getEmpDetail(id);
        List<DeptEmployee> deptEmployeeList =  detail.getDeptEmployees();
        deptEmployeeList.stream().forEach( d -> {logger.info("DeptNo: " + d.getDeptNo());});
        return detail;
    }

    @GetMapping("/posts/{id}")
    Post getPost(@PathVariable("id") Long id) {

        return postsService.getPost(id) ;}

    @GetMapping("/deptemployee/{id}")
    DeptEmployee getDeptEmployee(@PathVariable("id") Long id) {return employeeService.getDeptEmp(id) ;}

}
