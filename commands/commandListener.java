package commands;

import java.io.IOException;
import java.util.*;
import game.*;
import records.*;


public abstract class commandListener {
    
    public static void listener(ArrayList<String> arguments, HashMap<String, String> options, Scanner scanner, Logger logger) throws IOException, Exception{
        logger.log("Awaiting for command input now\n");
        String[] command = scanner.nextLine().split(" ");
        parserResult parseCommand = parser.parse(command);
        ArrayList<String> commandArguments = parseCommand.arguments();
        HashMap<String, String> commandOptions = parseCommand.options();
        if(commandArguments.get(0).toLowerCase().equals("create")) createCommand.create(arguments, commandArguments, options, commandOptions, scanner, logger);;
    }


    
}
