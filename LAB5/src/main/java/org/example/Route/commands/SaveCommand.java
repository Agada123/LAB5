package org.example.Route.commands;

import org.example.Route.core.CollectionManager;
import org.example.Route.exceptions.CommandExecutionException;
import org.example.Route.utils.FileManager;
import org.example.Route.utils.XMLParser;


public class SaveCommand implements Command {

    private CollectionManager collectionManager;

    public SaveCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String[] args) throws CommandExecutionException {
        String fileName = System.getenv("ROUTE_FILE");
        if (fileName == null || fileName.isEmpty()) {
            throw new CommandExecutionException("Environment variable ROUTE_FILE is not set or empty.");
        }
        try {
            String xml = XMLParser.serializeCollection(collectionManager);
            FileManager.writeFile(fileName, xml);
            System.out.println("Collection saved to file: " + fileName);
        } catch (Exception e) {
            throw new CommandExecutionException("Error saving collection: " + e.getMessage());
        }
    }

    @Override
    public String getDescription() {
        return "Save the collection to file.";
    }
}

