package commands;

import java.io.IOException;
import java.util.*;
import game.*;
import records.*;


public abstract class commandListener {
    private commandListener() { }

    private static final commandListener INSTANCE = new commandListener(){}; //listener is a singleton. refer to it as listener.getInstance()...

    public static commandListener getInstance() {
        return INSTANCE;
    }
    
    public void listener(ArrayList<String> arguments, HashMap<String, String> options, Scanner scanner, Logger logger, String interfaceMode) throws IOException, Exception{
        logger.log("Awaiting for command input now\n");
        System.out.print(">");
        String[] command = scanner.nextLine().split(" ");
        parserResult parseCommand = parser.parse(command);
        ArrayList<String> commandArguments = parseCommand.arguments();
        HashMap<String, String> commandOptions = parseCommand.options();
        logger.log("Args: " + commandArguments.toString() + "\nOptions: " + commandOptions.toString());
        String iHateThatJavaDoesNotKnowWhatDeleteIs = commandArguments.get(0).toLowerCase();
        switch(iHateThatJavaDoesNotKnowWhatDeleteIs){
            case "create":
                createCommand.create(arguments, commandArguments, options, commandOptions, scanner, logger);
                break;
            case "ls":
                listCommand.list(arguments, commandArguments, options, commandOptions, scanner, logger);
                break;
            case "act":


            default:
                throw new IllegalArgumentException("Unknown command for characters");

        }
        
    }


    
}
