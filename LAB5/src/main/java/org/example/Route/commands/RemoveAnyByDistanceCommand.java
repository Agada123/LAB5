package org.example.Route.commands;

import org.example.Route.core.CollectionManager;
import org.example.Route.exceptions.CommandExecutionException;
import org.example.Route.models.Route;

import java.util.ArrayList;
import java.util.Collection;


public class RemoveAnyByDistanceCommand implements Command {

    private CollectionManager collectionManager;

    public RemoveAnyByDistanceCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String[] args) throws CommandExecutionException {
        if (args.length < 1) {
            throw new CommandExecutionException("Missing distance argument for remove_any_by_distance.");
        }
        try {
            int distance = Integer.parseInt(args[0]);
            for (Route route : new ArrayList<Route>((Collection<Route>) collectionManager.getRoutes())) {

                if (route.getDistance() == distance) {
                    collectionManager.removeById(route.getId());
                    System.out.println("Removed one element with distance " + distance + ".");
                    return;
                }
            }
            System.out.println("No element found with distance " + distance + ".");
        } catch (NumberFormatException e) {
            throw new CommandExecutionException("Invalid distance format. Must be an integer.");
        }
    }

    @Override
    public String getDescription() {
        return "Remove one element whose distance field value equals the given one.";
    }
}
