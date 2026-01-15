package org.example.Route.commands;

import org.example.Route.core.CollectionManager;
import org.example.Route.exceptions.CommandExecutionException;
import org.example.Route.models.Route;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class RemoveAllByDistanceCommand implements Command {

    private CollectionManager collectionManager;

    public RemoveAllByDistanceCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String[] args) throws CommandExecutionException {
        if (args.length < 1) {
            throw new CommandExecutionException("Missing distance argument for remove_all_by_distance.");
        }
        try {
            int distance = Integer.parseInt(args[0]);

            
            List<Long> removedIds = new ArrayList<>();

            
            for (Route route : new ArrayList<Route>((Collection<Route>) collectionManager.getRoutes())) {

                if (route.getDistance() == distance) {
                    collectionManager.removeById(route.getId());
                    removedIds.add(route.getId());
                }
            }
            System.out.println("Removed " + removedIds.size() + " elements with distance " + distance + ".");
        } catch (NumberFormatException e) {
            throw new CommandExecutionException("Invalid distance format. Must be an integer.");
        }
    }

    @Override
    public String getDescription() {
        return "Remove all elements whose distance field value equals the given one.";
    }
}
