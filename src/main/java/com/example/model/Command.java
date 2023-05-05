package com.example.model;

import java.util.ArrayList;
import java.util.List;

public class Command {
    private int id;
    private int idClient;
    private static int currentId = 0 ;
    private List<Dish> dishes;
    private int table;
    private String status;
    private List<Client> clients;

    public Command(){
        this.dishes = new ArrayList<>();
        this.id = currentId++;
    }

    /**
     * Constructor to initialize the command
     * @param dishes          The dishes of the command
     * @param status   The status of the command
     * @param table         The table of the command
     * @param idClient         The idClient of the command
     */
    public Command(List<Dish> dishes, String status, int table, int idClient){
        this.dishes = dishes;
        this.status = status;
        this.table = table;
        this.idClient = idClient;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Client> getClient() {
        return clients;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }

    public void addDish(Dish dish){
        this.dishes.add(dish);
    }

    public int getTable() {
        return table;
    }

    public void setTable(int table) {
        this.table = table;
    }

    public int getIdClient() {
        return idClient;
    }

    public Client getNameClient(String name, List<Client> clients) {
        for (Client  client : clients) {
            if (client.getName().equals(name)) {
                return client;
            }
        }
        return null;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public List<Dish> getDishes(String name, List<Dish> dishes) {
        for (Dish dish : dishes) {
            if (dish.getName().equals(name)) {
                return dishes;
            }
        }
        return null;
    }

    public Table getTableNumber(int number, List<Table> tables) {
        for (Table table : tables) {
            if (table.getNumber() == number) {
                return table;
            }
        }
        return null;
    }


    public void addTable(int table) {
    }
}
