package org.example.Route.core;

import org.example.Route.commands.Command;

import java.util.HashMap;
import java.util.Map;


public class CommandManager {
    private Map<String, Command> commands = new HashMap<>();


    public void register(String name, Command command) {
        commands.put(name, command);
    }


    public void execute(String inputLine) {
        String[] tokens = inputLine.trim().split(" ");
        String commandName = tokens[0];
        String[] args = new String[tokens.length - 1];
        if (tokens.length > 1) {
            System.arraycopy(tokens, 1, args, 0, tokens.length - 1);
        }
        Command command = commands.get(commandName);
        if (command != null) {
            try {
                command.execute(args);
            } catch (Exception e) {
                System.out.println("Error executing command: " + e.getMessage());
            }
        } else {
            System.out.println("Unknown command. Type 'help' for the list of commands.");
        }
    }


    public void printHelp() {
        System.out.println("Available commands:");
        commands.forEach((name, command) ->
                System.out.println(name + " - " + command.getDescription()));
    }


    public Map<String, Command> getCommands() {
        return commands;
    }
}
