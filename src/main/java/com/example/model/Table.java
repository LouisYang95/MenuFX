package com.example.model;

public class Table {
    private int id;

    private int number;
    private Command command;
    private boolean isFree;


    public Table(int id, int number) {
        this.id = id;
        this.number = number;
        this.isFree = true;
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

    public void freeTable(){
        this.isFree = true;
        this.command = null;
    }
}
