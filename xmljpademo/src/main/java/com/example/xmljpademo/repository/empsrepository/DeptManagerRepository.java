package com.example.xmljpademo.repository.empsrepository;

import com.example.xmljpademo.model.DeptManager;
import com.example.xmljpademo.model.EmployeeNO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeptManagerRepository  extends JpaRepository<DeptManager, EmployeeNO> {
}
