package com.example.xmljpademo;

import com.example.xmljpademo.model.Employee;
import com.example.xmljpademo.repository.empsrepository.*;
import com.example.xmljpademo.service.EmployeeService;
import com.example.xmljpademo.service.RedisSevice;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
//@SpringBootTest
public class EmployeeServiceTests {
    @TestConfiguration
    static class EmployeeServiceTestContextConfiguration {
        @Bean
        public EmployeeService employeeService() {
            return new EmployeeService();
        }
    }

    @Autowired
    private EmployeeService employeeService;

    @MockBean
    private EmployeeRepository employeeRepository;
    @MockBean
    EmployeeDetailRepository detailRepository;

    @MockBean
    DeptEmployeesRepository deptEmployeesRepository;

    @MockBean
    DeptManagerRepository deptManagerRepository;

    @MockBean
    DepartmentRepository departmentRepository;

    @MockBean
    RedisSevice redisSevice;

    @Before
public void setUp() {
        Employee employee = new Employee();
        employee.setEmployeeNo(Long.valueOf(106941));
        employee.setFirstName("robbie");
        employee.setLastName("lee");


        Mockito.when(employeeRepository.findById(employee.getEmployeeNo()))
                .thenReturn(Optional.of(employee));

        Mockito.when(redisSevice.getEmployee1("em:" + employee.getEmployeeNo()))
                .thenReturn(Optional.empty());
    }

    @Test
    public void whenValidName_thenEmployeeShouldBeFound() {
        Long id = new Long(106941);
        Employee found = employeeService.getEmployee(id);
        assertEquals(id, found.getEmployeeNo());
    }

}
