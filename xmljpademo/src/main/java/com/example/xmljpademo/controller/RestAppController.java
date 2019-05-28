package com.example.xmljpademo.controller;

import com.example.xmljpademo.dto.DeptManagerDto;
import com.example.xmljpademo.dto.PostCommentDTO;
import com.example.xmljpademo.model.*;
import com.example.xmljpademo.model.post.Post;
import com.example.xmljpademo.model.post.PostComment;
import com.example.xmljpademo.service.EmployeeService;
import com.example.xmljpademo.service.PostsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RestAppController {

    private static final Logger logger = LoggerFactory.getLogger(RestAppController.class);
    @Autowired
    EmployeeService employeeService;

    @Autowired
    PostsService postsService;

    @GetMapping("/redis")
    String redis(@RequestParam(name = "mode", defaultValue = "single") String mode) {
//        switch (mode) {
//            case "single":
//                return employeeService.saveToRedis();
//            case "pipeline":
//                return employeeService.saveToRedis();
//            default:
//                return "invalid mode";
//        }
        return employeeService.saveToRedis(mode);
        //return employeeService.saveToRedis();
    }

    @GetMapping("/emp/{id}")
    Employee getEmp(@PathVariable("id") Long id) {
        return employeeService.getEmployeeCached1(id);
    }

    @GetMapping("/employees/{id}")
    Employee getEmployee(@PathVariable("id") Long id) {
        return employeeService.getEmployee(id);
    }

    @PostMapping("/emp")
    Employee insertEmp(@RequestBody Employee employee) {
        return employeeService.inserEmp(employee);

    }

    @GetMapping("/employeesdetail/{id}")
    EmployeeDetail getEmployeeDetail(@PathVariable("id") Long id) {
        EmployeeDetail detail = employeeService.getEmpDetail(id);
        List<DeptEmployee> deptEmployeeList = detail.getDeptEmployees();
        deptEmployeeList.stream().forEach(d -> {
            logger.info("DeptNo: " + d.getDeptNo());
        });
        return detail;
    }

    @GetMapping("/empdetail/{id}")
    EmployeeDetail getEmpDetail(@PathVariable("id") Long id) {
        EmployeeDetail employeeDetail = employeeService.getEmployeeDetail(id);

        return employeeDetail;
    }
    @GetMapping("/posts/{id}")
    Post getPost(@PathVariable("id") Long id) {
        return postsService.getPost(id);
    }

    @PostMapping("/posts")
    PostComment addPost(@RequestBody PostCommentDTO commentDTO) {
        return postsService.addPostComment(commentDTO);
    }

    @GetMapping("/deptemployee/{id}")
    DeptEmployee getDeptEmployee(@PathVariable("id") Long id) {
        return employeeService.getDeptEmp(id);
    }

    @GetMapping("/deptManager/{id}")
    DeptManager getDeptManager(@PathVariable("id") Long id) {
        DeptManager manager = employeeService.getDeptManager(new EmployeeNO(id, "d001"));
        return manager;
    }

    @PostMapping("/deptManager")
    DeptManagerDto addDeptManager(@RequestBody DeptManagerDto deptManagerDto) {
        employeeService.addDeptManager(deptManagerDto);
        return deptManagerDto;
    }
}
