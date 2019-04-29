package com.example.xmljpademo.repository.empsrepository;

import com.example.xmljpademo.model.DeptEmployee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeptEmployeesRepository extends JpaRepository<DeptEmployee, Long> {

}
