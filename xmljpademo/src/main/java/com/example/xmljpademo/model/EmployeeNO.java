package com.example.xmljpademo.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class EmployeeNO implements Serializable {

    @Column(name = "emp_no")
    Long employeeNo;

    @Column(name = "dept_no")
    String deptNo;

    public EmployeeNO() {
    }

    public EmployeeNO(Long employeeNo, String deptNo) {
        this.employeeNo = employeeNo;
        this.deptNo = deptNo;
    }

    public Long getEmployeeNo() {
        return employeeNo;
    }

    public String getDeptNo() {
        return deptNo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDeptNo(), getEmployeeNo());
    }

    @Override
    public boolean equals(Object obj) {
        //return super.equals(obj);
        if (this == obj) return true;
        if (!(obj instanceof EmployeeNO)) return false;
        EmployeeNO that = (EmployeeNO) obj;

        return Objects.equals(getEmployeeNo(), that.getEmployeeNo()) &&
                 getDeptNo().equals(that.getDeptNo());
    }
}
