package com.example.model;

import java.util.ArrayList;
import java.util.List;

public class Restaurant {

    private List<Table> tables;

    public Restaurant() {
        this.tables = new ArrayList<>();
    }

    public List<Table> getTables() {
        return tables;
    }

    public void setTables(List<Table> tables) {
        this.tables = tables;
    }

    public void addTable(Table table) {
        this.tables.add(table);
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
