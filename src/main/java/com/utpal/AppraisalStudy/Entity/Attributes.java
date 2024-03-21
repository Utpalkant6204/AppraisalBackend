package com.utpal.AppraisalStudy.Entity;

import jakarta.persistence.*;

@Entity
public class Attributes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private long java;
    private long python;
    private long react;

    private long angular;

    private long flutter;

    private long uiDevelopment;

    private long apiDevelopment;

    private long deadlines;

    private long projectManagement;

    private long communication;

    private long behaviour;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    private Employees employees;


    public Attributes() {
    }

    public Attributes(long id, long java, long python, long react, long angular, long flutter, long uiDevelopment, long apiDevelopment, long deadlines, long projectManagement, long communication, long behaviour, Employees employees) {
        this.id = id;
        this.java = java;
        this.python = python;
        this.react = react;
        this.angular = angular;
        this.flutter = flutter;
        this.uiDevelopment = uiDevelopment;
        this.apiDevelopment = apiDevelopment;
        this.deadlines = deadlines;
        this.projectManagement = projectManagement;
        this.communication = communication;
        this.behaviour = behaviour;
        this.employees = employees;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getJava() {
        return java;
    }

    public void setJava(long java) {
        this.java = java;
    }

    public long getPython() {
        return python;
    }

    public void setPython(long python) {
        this.python = python;
    }

    public long getReact() {
        return react;
    }

    public void setReact(long react) {
        this.react = react;
    }

    public long getAngular() {
        return angular;
    }

    public void setAngular(long angular) {
        this.angular = angular;
    }

    public long getFlutter() {
        return flutter;
    }

    public void setFlutter(long flutter) {
        this.flutter = flutter;
    }

    public long getUiDevelopment() {
        return uiDevelopment;
    }

    public void setUiDevelopment(long uiDevelopment) {
        this.uiDevelopment = uiDevelopment;
    }

    public long getApiDevelopment() {
        return apiDevelopment;
    }

    public void setApiDevelopment(long apiDevelopment) {
        this.apiDevelopment = apiDevelopment;
    }

    public long getDeadlines() {
        return deadlines;
    }

    public void setDeadlines(long deadlines) {
        this.deadlines = deadlines;
    }

    public long getProjectManagement() {
        return projectManagement;
    }

    public void setProjectManagement(long projectManagement) {
        this.projectManagement = projectManagement;
    }

    public long getCommunication() {
        return communication;
    }

    public void setCommunication(long communication) {
        this.communication = communication;
    }

    public long getBehaviour() {
        return behaviour;
    }

    public void setBehaviour(long behaviour) {
        this.behaviour = behaviour;
    }

    public Employees getEmployees() {
        return employees;
    }

    public void setEmployees(Employees employees) {
        this.employees = employees;
    }
}
