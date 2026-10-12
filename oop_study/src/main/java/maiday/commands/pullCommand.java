package maiday.commands;

import java.io.IOException;
import java.util.*;
import maiday.game.Logger;
import maiday.infra.WebAPI.pull;

public abstract class pullCommand {
    public static void pull(ArrayList<String> arguments, ArrayList<String> commandArguments, HashMap<String, String> options, HashMap<String, String> commandOptions, Scanner scanner, Logger logger) throws IOException{

        switch (commandArguments.get(1).toLowerCase()) {
            case "actor":
            case "hero":
            case "character":
                pullHero(arguments, commandArguments, options, commandOptions, scanner, logger);
                break;
            case "item":
            case "weapon":
                pullWeapon(arguments, commandArguments, options, commandOptions, scanner, logger);
                break;
            default:
                throw new IllegalArgumentException("Illegal argument: " + commandArguments.get(1));
                
        }
    }

    private static void pullHero(ArrayList<String> arguments, ArrayList<String> commandArguments, HashMap<String, String> options, HashMap<String, String> commandOptions, Scanner scanner, Logger logger) throws IOException{
        for(Map.Entry<String, String> option : commandOptions.entrySet()){
            String key = option.getKey();
            String value = option.getValue();
            switch(key){
                case "--name":
                    pull.pullCharacter(value);
                    break;
                default: throw new IllegalArgumentException("Illegal argument: " + key);
            }

        }

    }
    private static void pullWeapon(ArrayList<String> arguments, ArrayList<String> commandArguments, HashMap<String, String> options, HashMap<String, String> commandOptions, Scanner scanner, Logger logger) throws IOException{
        for(Map.Entry<String, String> option : commandOptions.entrySet()){
            String key = option.getKey();
            String value = option.getValue();
            switch(key){
                case "--name":
                    pull.pullWeapon(value);
                    break;
                default: throw new IllegalArgumentException("Illegal argument: " + key);
            }

        }
    }
}
