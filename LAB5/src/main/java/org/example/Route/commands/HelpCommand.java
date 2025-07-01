package org.example.Route.commands;

import org.example.Route.core.CommandManager;


public class HelpCommand implements Command {

    private CommandManager commandManager;


    public HelpCommand(CommandManager commandManager) {
        this.commandManager = commandManager;
    }

    @Override
    public void execute(String[] args) {
        commandManager.printHelp();
    }

    @Override
    public String getDescription() {
        return "Display help on available commands.";
    }
}
