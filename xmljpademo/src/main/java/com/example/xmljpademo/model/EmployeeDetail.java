package com.example.xmljpademo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@JsonIgnoreProperties({"handler"})
@Entity
@Table(name = "employees")
public class EmployeeDetail implements Serializable {

    private static final long serialVersionUID = 10002L;

//    private int employeeNo;
//    private LocalDate birthDate;
//    private String firstName;
//    private String lastName;
//    private Gender gender;
//    private LocalDate hireDate;

    @Id
    @Column(name = "emp_no")
    private Long employeeNo;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "hire_date")
    private LocalDate hireDate;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "emp_no")
    private List<Title> titles;

    @OneToMany
    @JoinColumn(name = "emp_no")
    private List<Salary> salaries;

    @OneToMany(mappedBy = "employee")
    private List<Phone> phones;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "employee")
    private List<DeptEmployee> deptEmployees;

    public Long getEmployeeNo() {
        return employeeNo;
    }

    public void setEmployeeNo(Long employeeNo) {
        this.employeeNo = employeeNo;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public LocalDate getHireDate() {
        return hireDate;
    }

    public void setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
    }

    public List<Title> getTitle() {
        return titles;
    }

    public void setTitle(List<Title> title) {
        this.titles = title;
    }

    public List<Salary> getSalaries() {
        return salaries;
    }

    public void setSalaries(List<Salary> salaries) {
        this.salaries = salaries;
    }

    public List<Title> getTitles() {
        return titles;
    }

    public void setTitles(List<Title> titles) {
        this.titles = titles;
    }

    public List<Phone> getPhones() {
        return phones;
    }

    public void setPhones(List<Phone> phones) {
        this.phones = phones;
    }

    public List<DeptEmployee> getDeptEmployees() {
        return deptEmployees;
    }

    public void setDeptEmployees(List<DeptEmployee> deptEmployees) {
        this.deptEmployees = deptEmployees;
    }
}
