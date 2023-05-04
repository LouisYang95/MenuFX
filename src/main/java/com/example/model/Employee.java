package com.example.model;

import java.util.ArrayList;
import java.util.List;

public class Employee {
    private String name;
    private String position;
    private float hoursWorked;
    private List<Employee> employees;
    private Object GestionEmployee;
    private int id;

    public Employee(String name, String position) {
        this.name = name;
        this.position = position;
        this.hoursWorked = hoursWorked;
        this.employees = new ArrayList<>();
        this.GestionEmployee = GestionEmployee;
        this.id = id ;
    }

    public Employee() {

    }

    public static void add(Employee employee1) {

    }

    public int getid() {
        return id;
    }
    public void setid(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public float getHoursWorked() {
        return hoursWorked;
    }

    public void setHoursWorked(float hoursWorked) {
        this.hoursWorked = hoursWorked;
    }

    public void addHoursWorked(float hoursWorked) {
        this.hoursWorked += hoursWorked;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void addEmployee(Employee employee) {
        this.employees.add(employee);
    }

    public void removeEmployee(Employee employee) {
        this.employees.remove(employee);
    }


}







