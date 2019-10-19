package com.example.xmljpademo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


import javax.persistence.*;

@JsonIgnoreProperties("employee")
@Entity
@Table(name = "phones")
public class Phone {
    @Id
    Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_no")
    EmployeeDetail employee;
    //Long employeeNo;

    @Column(name = "p_number")
    String number;

    @Column(name = "area_code")
    String areaCode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EmployeeDetail getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeDetail employee) {
        this.employee = employee;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }
}
