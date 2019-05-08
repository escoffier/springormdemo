package com.example.xmljpademo.service;

import com.example.xmljpademo.dto.DeptManagerDto;
import com.example.xmljpademo.model.*;
import com.example.xmljpademo.repository.empsrepository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

//import javax.transaction.Transactional;

@Component
@Transactional(value = "employeesTransactionManager")
public class EmployeeService {

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

    public Employee getEmployee(Long id) {
         return employeeRepository.findById(id).orElse(new Employee());
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
}
