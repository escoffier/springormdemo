package com.example.xmljpademo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "dept_emp")
@JsonIgnoreProperties("employee")
public class DeptEmployee implements Serializable {

    private static final long serialVersionUID = 41232L;

//    @Id()
//    @Column(name = "emp_no")
//    Long employeeNo;
//
    @Id
    @Column(name = "dept_no")
    String deptNo;

//    @EmbeddedId
//    private EmployeeNO employeeNo;

    @Column(name = "from_date")
    Date fromDate;

    @Column(name = "to_date")
    Date toDate;



    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "emp_no")
    private Employee employee;

//    public void setEmployeeNo(EmployeeNO employeeNo) {
//        this.employeeNo = employeeNo;
//    }
//
//    public EmployeeNO getEmployeeNo() {
//        return employeeNo;
//    }

    public String getDeptNo() {
        return deptNo;
    }

    public void setDeptNo(String deptNo) {
        this.deptNo = deptNo;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }
}
