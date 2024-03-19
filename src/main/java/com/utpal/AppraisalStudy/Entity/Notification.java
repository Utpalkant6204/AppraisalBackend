package com.utpal.AppraisalStudy.Entity;

import jakarta.persistence.*;

@Entity
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private long employeeId;

    @Column(nullable = false)
    private String employeeName;

    public Notification() {
    }

    public Notification(long id, long employeeId, String employeeName) {
        this.id = id;
        this.employeeId = employeeId;
        this.employeeName = employeeName;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(long employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }
}
