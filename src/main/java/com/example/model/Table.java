package com.example.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Table {

    private static int lastTableNumber = 0;
    private int id;

    private int number;
    private Command command;
    private boolean isFree;
    private int nbSeats;
    private List<Table> tables;


    public Table(int id, int number, int nbSeats) {
        this.id = id;
        this.number = number;
        this.isFree = true;
        this.nbSeats = nbSeats;
        this.tables = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public boolean isFree() {
        return isFree;
    }

    public void setFree(boolean free) {
        isFree = free;
    }

    public Command getCommand() {
        return command;
    }

    public void setCommand(Command command) {
        this.command = command;
    }
    public int getNbSeats() { return nbSeats; }
    public void setNbSeats(int nbSeats) { this.nbSeats = nbSeats; }

    public void addTable(Table tables) {
        this.tables.add(tables);
    }

    /**
     * Get the list of tables.
     * Remove a table from the list of tables.
     * @return The list of tables
     */
    public boolean removeTable(int tableNumber) {
        Iterator<Table> iterator = this.tables.iterator();
        while (iterator.hasNext()) {
            Table table = iterator.next();
            if (table.getNumber() == tableNumber) {
                iterator.remove();
                return true;
            }
        }
        return false;
    }

    /**
     * Set the list of tables.
     *
     * @return The list of tables
     */

    public static int setLastTableNumber(List<Table> tables) {
        int maxTableNumber = 0;
        for (Table table : tables) {
            if (table.getNumber() > maxTableNumber) {
                maxTableNumber = table.getNumber();
            }
        }
        return maxTableNumber + 1;
    }

    public void assignCommand(Command command) {
        this.command = command;
        this.isFree = false;
    }




    public void freeTable(){
        this.isFree = true;
        this.command = null;
    }
}