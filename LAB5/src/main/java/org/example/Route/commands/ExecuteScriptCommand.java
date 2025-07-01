package org.example.Route.commands;

import org.example.Route.core.CollectionManager;
import org.example.Route.core.CommandManager;
import org.example.Route.exceptions.CommandExecutionException;
import org.example.Route.utils.FileManager;
import org.example.Route.utils.InputManager;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class ExecuteScriptCommand implements Command {

    private CollectionManager collectionManager;
    private CommandManager commandManager;
    private InputManager inputManager;


    private static final Set<String> activeScripts = new HashSet<>();

    public ExecuteScriptCommand(CollectionManager collectionManager, CommandManager commandManager, InputManager inputManager) {
        this.collectionManager = collectionManager;
        this.commandManager = commandManager;
        this.inputManager = inputManager;
    }

    @Override
    public void execute(String[] args) throws CommandExecutionException {
        if (args.length < 1) {
            throw new CommandExecutionException("Missing file name argument for execute_script.");
        }
        String scriptFileName = args[0];


        if (activeScripts.contains(scriptFileName)) {
            throw new CommandExecutionException("Recursion detected! The script '" + scriptFileName + "' is already being executed.");
        }


        try {

            activeScripts.add(scriptFileName);

            String fileContent = FileManager.readFile(scriptFileName);
            Scanner fileScanner = new Scanner(fileContent);
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine().trim();
                if (!line.isEmpty()) {

                    System.out.println("Executing from " + scriptFileName + ": " + line);
                    commandManager.execute(line);
                }
            }
            fileScanner.close();
        } catch (Exception e) {

            throw new CommandExecutionException("Error executing script '" + scriptFileName + "': " + e.getMessage());
        } finally {

            activeScripts.remove(scriptFileName);
        }
    }

    @Override
    public String getDescription() {
        return "Read and execute the script from the specified file.";
    }
}