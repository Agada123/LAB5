package org.example.Route.core;

import org.example.Route.commands.*;
import org.example.Route.utils.FileManager;
import org.example.Route.utils.InputManager;
import org.example.Route.utils.XMLParser;

import java.io.IOException;
import java.util.Scanner;
import jakarta.xml.bind.JAXBException;

public class Main {

    public static void main(String[] args) {

        InputManager inputManager = new InputManager(new Scanner(System.in));
        CollectionManager collectionManager = new CollectionManager();

        String fileName = System.getenv("ROUTE_FILE");
        if (fileName == null || fileName.isEmpty()) {
            System.out.println("Environment variable ROUTE_FILE is not set or empty. Starting with an empty collection.");
        } else {

            try {
                String xmlData = FileManager.readFile(fileName);
                XMLParser.populateCollection(xmlData, collectionManager);
            } catch (IOException e) {

                System.out.println("Error: Could not read the file '" + fileName + "'. Please check file permissions and path.");
            } catch (JAXBException e) {

                System.out.println("Error: The file '" + fileName + "' is corrupted or has an invalid format. Starting with an empty collection.");

            } catch (Exception e) {

                System.out.println("An unexpected error occurred while loading the file: " + e.getMessage());
            }
        }


        CommandManager commandManager = new CommandManager();
        commandManager.register("help", new HelpCommand(commandManager));
        commandManager.register("info", new InfoCommand(collectionManager));
        commandManager.register("show", new ShowCommand(collectionManager));
        commandManager.register("add", new AddCommand(collectionManager, inputManager));
        commandManager.register("update", new UpdateCommand(collectionManager, inputManager));
        commandManager.register("remove_by_id", new RemoveByIdCommand(collectionManager));
        commandManager.register("clear", new ClearCommand(collectionManager));
        commandManager.register("save", new SaveCommand(collectionManager));
        commandManager.register("execute_script", new ExecuteScriptCommand(collectionManager, commandManager, inputManager));
        commandManager.register("exit", new ExitCommand());
        commandManager.register("add_if_min", new AddIfMinCommand(collectionManager, inputManager));
        commandManager.register("remove_greater", new RemoveGreaterCommand(collectionManager, inputManager));
        commandManager.register("remove_lower", new RemoveLowerCommand(collectionManager, inputManager));
        commandManager.register("remove_all_by_distance", new RemoveAllByDistanceCommand(collectionManager));
        commandManager.register("remove_any_by_distance", new RemoveAnyByDistanceCommand(collectionManager));
        commandManager.register("filter_greater_than_distance", new FilterGreaterThanDistanceCommand(collectionManager));


        System.out.println("Welcome to the Route Manager. Type 'help' to see available commands.");
        while (true) {
            System.out.print("> ");
            String inputLine = inputManager.readLine();
            if (inputLine == null || inputLine.trim().isEmpty()) {
                continue;
            }
            commandManager.execute(inputLine);
        }
    }
}