package org.example.Route.commands;

import org.example.Route.core.CollectionManager;
import org.example.Route.models.Route;


public class ShowCommand implements Command {

    private CollectionManager collectionManager;

    public ShowCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String[] args) {
        boolean empty = true;
        for (Route route : collectionManager.getRoutes()) {
            System.out.println(route);
            empty = false;
        }
        if (empty) {
            System.out.println("Collection is empty.");
        }
    }

    @Override
    public String getDescription() {
        return "Print all elements of the collection.";
    }
}
