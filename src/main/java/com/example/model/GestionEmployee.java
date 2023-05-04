package com.example.model;
import java.util.ArrayList;
import java.util.List;
public class GestionEmployee {

private List<Employee> employees;

    public GestionEmployee(Object gestionEmployee, Object gestionEmployee1) {
        this.GestionEmployee = gestionEmployee;
        this.GestionEmployee1 = gestionEmployee1;
    }



    public static void main(String[] args) {
        GestionEmployee gestionEmployee = new GestionEmployee();

    }

// Add the new employee to the list
    public void addEmployee(Employee employee) {
        employees.add(employee);
        this.employee.add(employee);
         }


    // Remove the employee from the list by  id
    public void removeEmployee(int id) {
        employees.removeIf(employee -> employee.getid() == id);
         }

    // delete the employees
    public void deleteEmployee(Employee employee) {
        this.employee.remove(employee);
    }
/*
// Print the list of employees

    GestionEmployee.printEmployees();

    public void printEmployees() {
        for (Employee employee : employees) {
        System.out.println(employee.getName() + " (" + employee.getPosition() + ")");
        }
    } */

// Record the hours worked the employee

    public void recordHoursWorked(int id, int hours) {
        for (Employee employee : employees) {
            if (employee.getid() == id) {
                employee.addHoursWorked(hours);
                break;
           }
          }
        }


    // Viewing the list of employees
    public void showListEmployes() {
        for (Employee employee : this.employee) {
            System.out.println(employee.getName() + " - " + employee.getPosition() + " - " + employee.getHoursWorked() + "h");
        }
    }

    public GestionEmployee getEmployee(String name) {
        for (GestionEmployee GestionEmployee  : this.GestionEmployee) {
            if (GestionEmployee.getName().equals(name)) {
                return GestionEmployee;
            }
        }
        return null;
    }
    private Object getName() {
        return null;
    }

}