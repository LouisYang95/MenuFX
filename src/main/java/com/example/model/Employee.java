package com.example.model;

public class Employee {
    private String name;
    private String position;
    private float hoursWorked;

    public Employee(String name, String position, float hoursWorked) {
        this.name = name;
        this.position = position;
        this.hoursWorked = hoursWorked;
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



}
