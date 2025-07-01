package org.example.Route.commands;

import org.example.Route.core.CollectionManager;


public class InfoCommand implements Command {

    private CollectionManager collectionManager;

    public InfoCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String[] args) {
        System.out.println("Collection type: " + collectionManager.getRoutes().getClass().getName());
        System.out.println("Initialization date: " + collectionManager.getInitializationDate());
        System.out.println("Number of elements: " + collectionManager.size());
    }

    @Override
    public String getDescription() {
        return "Print collection information.";
    }
}
