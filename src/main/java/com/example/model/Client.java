package com.example.model;

import java.util.List;

public class Client {
    private int id;
    private static int currentId = 0;
    private String name;
    private List<Command> commands;


    public Client( String name) {
        this.name = name;
    }

    public Client(){
        this.id = currentId++;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Command> getCommands() {
        return commands;
    }

    public void setCommands(List<Command> commands) {
        this.commands = commands;
    }

    public void addCommand(Command command){
        this.commands.add(command);
    }
}
