package org.example.Route.commands;


public interface Command {

    void execute(String[] args) throws Exception;


    String getDescription();
}

