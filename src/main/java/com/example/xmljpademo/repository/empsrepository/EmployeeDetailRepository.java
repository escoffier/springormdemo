package com.example.xmljpademo.repository.empsrepository;

import com.example.xmljpademo.model.EmployeeDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeDetailRepository extends JpaRepository<EmployeeDetail, Long> {
}
