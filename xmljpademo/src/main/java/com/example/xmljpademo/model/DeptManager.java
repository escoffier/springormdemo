package com.example.xmljpademo.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;


//child side of employees table
@Entity
@Table(name = "dept_manager")
public class DeptManager implements Serializable {

    private static final long serialVersionUID = 415232L;

    //@OneToOne(mappedBy = , cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @EmbeddedId
    private EmployeeNO employeeNO;

    //@OneToOne(mappedBy = "employeeNo", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    //@JoinColumn(name = "emp_no")
//    //@JoinColumn(name = "emp_no", insertable = false, updatable = false)
    @OneToOne
    @JoinColumn(name = "emp_no", insertable = false, updatable = false)
    private Employee employee;

//    @Column(name = "emp_no")
//    int employeeNo;

//    @Column(name = "dept_no")
//    String deptNo;

    @Column(name = "from_date")
    LocalDate fromDate;

    @Column(name = "to_date")
    LocalDate toDate;

    public EmployeeNO getEmployeeNO() {
        return employeeNO;
    }

    public void setEmployeeNO(EmployeeNO employeeNO) {
        this.employeeNO = employeeNO;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

//    public int getEmployeeNo() {
//        return employeeNo;
//    }
//
//    public void setEmployeeNo(int employeeNo) {
//        this.employeeNo = employeeNo;
//    }

//    public String getDeptNo() {
//        return deptNo;
//    }
//
//    public void setDeptNo(String deptNo) {
//        this.deptNo = deptNo;
//    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
    }

    public LocalDate getToDate() {
        return toDate;
    }

    public void setToDate(LocalDate toDate) {
        this.toDate = toDate;
    }
}
