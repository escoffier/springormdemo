package com.example.xmljpademo.repository.empsrepository;

import com.example.xmljpademo.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee , Long> {
}
