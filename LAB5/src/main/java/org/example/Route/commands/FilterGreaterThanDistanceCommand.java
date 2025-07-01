package org.example.Route.commands;

import org.example.Route.core.CollectionManager;
import org.example.Route.exceptions.CommandExecutionException;
import org.example.Route.models.Route;


public class FilterGreaterThanDistanceCommand implements Command {

    private CollectionManager collectionManager;

    public FilterGreaterThanDistanceCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String[] args) throws CommandExecutionException {
        if (args.length < 1) {
            throw new CommandExecutionException("Missing distance argument for filter_greater_than_distance.");
        }
        try {
            int distance = Integer.parseInt(args[0]);
            boolean found = false;
            for (Route route : collectionManager.getRoutes()) {
                if (route.getDistance() > distance) {
                    System.out.println(route);
                    found = true;
                }
            }
            if (!found) {
                System.out.println("No elements with distance greater than " + distance + " found.");
            }
        } catch (NumberFormatException e) {
            throw new CommandExecutionException("Invalid distance format. Must be an integer.");
        }
    }

    @Override
    public String getDescription() {
        return "Output elements whose distance field value is greater than the specified value.";
    }
}
