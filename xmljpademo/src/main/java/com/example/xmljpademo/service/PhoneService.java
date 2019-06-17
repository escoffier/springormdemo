package com.example.xmljpademo.service;

import com.example.xmljpademo.model.Phone;
import com.example.xmljpademo.repository.empsrepository.PhoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Component
public class PhoneService {

    @Autowired
    private PhoneRepository phoneRepository;

    @Transactional(value = "employeesTransactionManager", propagation = Propagation.REQUIRED)
    public List<Phone> getPhones(Long id) {
        List<Phone> phones = phoneRepository.findAllById(Arrays.asList(id));
        if (phones.isEmpty()) {
            throw new RuntimeException("no phone");
        }
        return phones;
    }

    public Phone insertPhone(Phone phone) {
        return phoneRepository.save(phone);
    }
}
