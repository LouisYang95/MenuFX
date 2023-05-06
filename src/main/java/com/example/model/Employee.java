package com.example.model;

import java.util.ArrayList;
import java.util.List;

public class Employee {
    private String name;
    private String position;
    private float hoursWorked;
    private int age;

    public Employee(String name, int age) {
        this.name = name;
        this.position = position;
        this.age = age;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

}
