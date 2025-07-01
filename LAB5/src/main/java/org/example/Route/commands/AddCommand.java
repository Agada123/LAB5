package org.example.Route.commands;

import org.example.Route.core.CollectionManager;
import org.example.Route.exceptions.CommandExecutionException;
import org.example.Route.models.Coordinates;
import org.example.Route.models.Location;
import org.example.Route.models.Route;
import org.example.Route.utils.InputManager;


public class AddCommand implements Command {

    private CollectionManager collectionManager;
    private InputManager inputManager;

    public AddCommand(CollectionManager collectionManager, InputManager inputManager) {
        this.collectionManager = collectionManager;
        this.inputManager = inputManager;
    }

    @Override
    public void execute(String[] args) throws CommandExecutionException {
        try {
            System.out.println("Enter route name:");
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

            Route route = new Route(name, coordinates, from, to, distance);
            collectionManager.addRoute(route);
            System.out.println("Route added successfully.");
        } catch (IllegalArgumentException e) {
            throw new CommandExecutionException("Error adding route: " + e.getMessage());
        }
    }

    @Override
    public String getDescription() {
        return "Add a new element to the collection.";
    }
}