package org.example.Route.commands;

import org.example.Route.core.CollectionManager;
import org.example.Route.exceptions.CommandExecutionException;
import org.example.Route.models.Coordinates;
import org.example.Route.models.Location;
import org.example.Route.models.Route;
import org.example.Route.utils.InputManager;


public class UpdateCommand implements Command {

    private CollectionManager collectionManager;
    private InputManager inputManager;

    public UpdateCommand(CollectionManager collectionManager, InputManager inputManager) {
        this.collectionManager = collectionManager;
        this.inputManager = inputManager;
    }

    @Override
    public void execute(String[] args) throws CommandExecutionException {
        if (args.length < 1) {
            throw new CommandExecutionException("Missing id argument for update.");
        }
        try {
            long id = Long.parseLong(args[0]);
            if (!collectionManager.getRoutes().stream().anyMatch(r -> r.getId().equals(id))) {
                System.out.println("No route found with id " + id + ".");
                return;
            }

            System.out.println("Enter new data for the route with id " + id + ":");
            String name = inputManager.readNonEmptyString("Name");

            System.out.println("Enter coordinates:");
            Integer x = inputManager.readInt("Coordinate x:");
            Double y = inputManager.readDouble("Coordinate y (max 271):", 271.0);
            Coordinates coordinates = new Coordinates(x, y);

            System.out.println("Enter 'from' location:");
            float fx = inputManager.readFloat("Location from x:");
            Long fy = inputManager.readLong("Location from y:");
            Double fz = inputManager.readDouble("Location from z:");
            Location from = new Location(fx, fy, fz);

            System.out.println("Enter 'to' location:");
            float tx = inputManager.readFloat("Location to x:");
            Long ty = inputManager.readLong("Location to y:");
            Double tz = inputManager.readDouble("Location to z:");
            Location to = new Location(tx, ty, tz);

            int distance = inputManager.readInt("Enter distance (must be > 1):", 1);

            Route newRoute = new Route(name, coordinates, from, to, distance);
            boolean success = collectionManager.updateRoute(id, newRoute);
            if (success) {
                System.out.println("Route updated successfully.");
            } else {

                System.out.println("No route found with id " + id + ".");
            }
        } catch (NumberFormatException e) {
            throw new CommandExecutionException("Invalid id format. Must be an integer.");
        } catch (IllegalArgumentException e) {
            throw new CommandExecutionException("Error updating route: " + e.getMessage());
        }
    }

    @Override
    public String getDescription() {
        return "Update the element whose id is equal to the given one.";
    }
}