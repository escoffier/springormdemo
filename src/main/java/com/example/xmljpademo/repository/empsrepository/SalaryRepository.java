package com.example.xmljpademo.repository.empsrepository;

import com.example.xmljpademo.model.Salary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalaryRepository extends JpaRepository<Salary, Long> {
}
