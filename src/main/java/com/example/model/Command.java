package com.example.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Command {
    private int id;
    private String client;
    private static int currentId = 0;
    private final List<Dish> dishes;
    private int table;
    private String status;
    private Date date;
    private String totalPrice;

    public Command() {
        this.dishes = new ArrayList<>();
        this.id = currentId++;
        this.date = new Date();
        this.totalPrice = "0";
    }

    /**
     * Constructor to initialize the command
     *
     * @param dishes   The dishes of the command
     * @param status   The status of the command
     * @param table    The table of the command
     * @param client The idClient of the command
     */
    public Command(List<Dish> dishes, String status, int table, String client) {
        this.dishes = dishes;
        this.status = status;
        this.table = table;
        this.client = client;
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


    public void addDish(Dish dish) {
        dishes.add(dish);
    }

    public int getTable() {
        return table;
    }

    public void setTable(int table) {
        this.table = table;
    }

    public String getClient() {
        return client;
    }

    public Client getNameClient(String name, List<Client> clients) {
        for (Client clientList : clients) {
            if (clientList.getName().equals(name)) {
                return clientList;
            }
        }
        return null;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public Dish getDishesByName(String name, List<Dish> dishes) {
        for (Dish dish : dishes) {
            if (dish.getName().equals(name)) {
                return dish;
            }
        }
        return null;
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public List<Dish> setDishes(List<Dish> dish){
        return dishes;
    }

    public Table getTableNumber(int number, List<Table> tables) {
        for (Table tableList : tables) {
            if (tableList.getNumber() == number) {
                return tableList;
            }
        }
        return null;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
