package org.example.Route.commands;

import org.example.Route.core.CollectionManager;
import org.example.Route.exceptions.CommandExecutionException;


public class RemoveByIdCommand implements Command {

    private CollectionManager collectionManager;

    public RemoveByIdCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String[] args) throws CommandExecutionException {
        if (args.length < 1) {
            throw new CommandExecutionException("Missing id argument for remove_by_id.");
        }
        try {
            // Change from int/Integer.parseInt to long/Long.parseLong
            long id = Long.parseLong(args[0]);
            boolean removed = collectionManager.removeById(id);
            if (removed) {
                System.out.println("Route with id " + id + " removed successfully.");
            } else {
                System.out.println("No route found with id " + id + ".");
            }
        } catch (NumberFormatException e) {
            // Also update the error message to be more general
            throw new CommandExecutionException("Invalid id format. Must be a number.");
        }
    }

    @Override
    public String getDescription() {
        return "Remove an element from the collection by its id.";
    }
}
