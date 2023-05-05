package com.example.model;

import java.util.ArrayList;
import java.util.List;

public class Restaurant {

    /** In this class, we are intializing the variables and the constructor
     *
     */

    /** Lists of tables and employees
     *
     */
    private List<Table> tables;
    private List<Employee> employees;

    /** This constructor is used to create a restaurant
     *
     */
    public Restaurant() {
        this.tables = new ArrayList<>();
    }

    public List<Table> getTables() {
        return tables;
    }
    public List<Employee> getEmployees() {
        return employees;
    }

    

    /** Methods to add a table or an employee
     *
     * @param nbSeats
     */
    public void addTable(int nbSeats) {
        int nextId = tables.size() + 1;
        int nextTableNumber = Table.setLastTableNumber(this.tables);
        Table newTable = new Table(nextTableNumber, nbSeats);
        tables.add(newTable);
    }

    /** Method addEmployee
     *
     * @param name
     * @param position
     * @param age
     */
    public void addEmployee(String name, String position, int age) {
        int nextId = employees.size() + 1;
        Employee newEmployee = new Employee(name, age);
        employees.add(newEmployee);
    }

    /** Method removeTable
     *
     * @param tableNumber
     * @Condition if the table number is equal to the table number, then remove the table
     * @return true
     */
    public boolean removeTable(int tableNumber) {
        for (Table table : this.tables) {
            if (table.getNumber() == tableNumber) {
                this.tables.remove(table);
                return true;
            }
        }
        return false;
    }

    /** Method removeEmployee
     *
     * @param employeeName
     * @Condition if the employee name is equal to the employee name, then remove the employee
     * @return true
     */
    public boolean removeEmployee(String employeeName) {
        for (Employee employee : this.employees) {
            if (employee.getName() == employeeName) {
                this.employees.remove(employee);
                return true;
            }
        }
        return false;
    }
}
