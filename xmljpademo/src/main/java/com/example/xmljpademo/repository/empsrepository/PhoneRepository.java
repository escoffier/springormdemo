package com.example.xmljpademo.repository.empsrepository;

import com.example.xmljpademo.model.Phone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhoneRepository extends JpaRepository<Phone, Long> {
}
