package com.example.model;

public class Table {

    private static int lastTableNumber = 0;
    private int id;

    private int number;
    private Command command;
    private boolean isFree;
    private int nbSeats;


    public Table(int nbSeats) {
        this.id = lastTableNumber++;
        this.number = lastTableNumber;
        this.isFree = true;
        this.nbSeats = nbSeats;
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

    public void freeTable(){
        this.isFree = true;
        this.command = null;
    }
}
