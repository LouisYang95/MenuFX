package com.example.model;

import java.util.ArrayList;
import java.util.List;

public class Command {
    private int id;
    private int idClient;
    private static int currentId = 0 ;
    private List<Dish> dishes;

    private Table table;

    private String status;

    public Command(){
        this.dishes = new ArrayList<>();
        this.id = currentId++;
    }

    public Command(List<Dish> dishes, String status, Table table, int idClient){
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

    public List<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }

    public void addDish(Dish dish){
        this.dishes.add(dish);
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }
}
