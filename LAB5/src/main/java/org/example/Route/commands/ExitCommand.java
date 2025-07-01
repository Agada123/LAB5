package org.example.Route.commands;


public class ExitCommand implements Command {

    @Override
    public void execute(String[] args) {
        System.out.println("Exiting application...");
        System.exit(0);
    }

    @Override
    public String getDescription() {
        return "Exit the program (without saving).";
    }
}
