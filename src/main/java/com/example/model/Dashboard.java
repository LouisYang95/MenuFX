package com.example.model;

import java.util.ArrayList;
import java.util.List;

public class Dashboard {
    /**
     * The list of commands
     */
    private List<Command> commands;
    /**
     * The list of dishes
     */
    private List<Dish> dishes;
    /**
     * The list of employees
     */
    private List<Employee> employees;
    /**
     * The list of tables
     */
    private List<Table> tables;

    /**
     * Constructor to initialize the dashboard
     */
    public Dashboard(){
        this.commands = new ArrayList<>();
        this.dishes = new ArrayList<>();
        this.employees = new ArrayList<>();
        this.tables = new ArrayList<>();
    }

    /**
     * Get the list of commands
     * @return commands returning my list of commands
     */
    public List<Command> getCommands() {
        return commands;
    }

    public void setCommands(List<Command> commands) {
        this.commands = commands;
    }

    /**
     * Get the list of dishes
     * @return dishes returning my list of dishes
     */
    public List<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }
    /**
     * Get the list of employees
     * @return employees returning my list of employees
     */
    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    /**
     * Get the list of tables
     * @return tables returning my list of tables
     */
    public List<Table> getTables() {
        return tables;
    }

    public void setTables(List<Table> tables) {
        this.tables = tables;
    }

    /**
     * Add a command to the list of commands
     * @param command receiving a command object
     */
    public void addCommand(Command command){
        this.commands.add(command);
    }
    /**
     * Add a dish to the list of dishes
     * @param dish receiving a dish object
     */
    public void addDish(Dish dish){
        this.dishes.add(dish);
    }
    /**
     * Add an employee to the list of employees
     * @param employee receiving an employee object
     */
    public void addEmployee(Employee employee){
        this.employees.add(employee);
    }
    /**
     * Add a table to the list of tables
     * @param table receiving a table object
     */
    public void addTable(Table table){
        this.tables.add(table);
    }

    public void updateCommand(Command newSelection) {
        for (Command command : commands) {
            if (command.getId() == newSelection.getId()) {
                command.setDishes(newSelection.getDishes());
                command.setTable(newSelection.getTable());
                command.setTotalPrice(newSelection.getTotalPrice());
                command.setStatus(newSelection.getStatus());
            }
        }
    }
}
