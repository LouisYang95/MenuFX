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

    public Table(int lastTableNumber, int nbSeats) {
        this.id = lastTableNumber++;
        this.number = lastTableNumber;
        this.isFree = true;
        this.nbSeats = nbSeats;
        this.tables = new ArrayList<>();
    }


    public Table(int number) {
        this.id = lastTableNumber++;
        this.tables = new ArrayList<>();
        this.number = number;
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
    public void freeTable(){
        this.isFree = true;
        this.command = null;
    }
}