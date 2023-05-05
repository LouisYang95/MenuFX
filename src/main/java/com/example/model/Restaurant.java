package com.example.model;

import java.util.ArrayList;
import java.util.List;

public class Restaurant {

    private List<Table> tables;
    private List<Employee> employees;

    public Restaurant() {
        this.tables = new ArrayList<>();
    }

    public List<Table> getTables() {
        return tables;
    }
    public List<Employee> getEmployees() {
        return employees;
    }

    public void setTables(List<Table> tables) {
        this.tables = tables;
    }
    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public void addTable(int nbSeats) {
        int nextId = tables.size() + 1;
        int nextTableNumber = Table.setLastTableNumber(this.tables);
        Table newTable = new Table(nextId, nextTableNumber, nbSeats);
        tables.add(newTable);
    }

    public void addEmployee(String name, String position, float hoursWorked) {
        int nextId = employees.size() + 1;
        Employee newEmployee = new Employee(name, position, hoursWorked);
        employees.add(newEmployee);
    }

    public boolean removeTable(int tableNumber) {
        for (Table table : this.tables) {
            if (table.getNumber() == tableNumber) {
                this.tables.remove(table);
                return true;
            }
        }
        return false;
    }
}
