package maiday.commands;

import java.io.IOException;
import java.util.*;

import maiday.game.Logger;
import maiday.game.game;
import maiday.infra.Data.writer;
import maiday.chara.*;
import maiday.items.item;
import maiday.text.textDocument;

public abstract class saveCommand {
    public static void save(ArrayList<String> arguments, ArrayList<String> commandArguments, HashMap<String, String> options, HashMap<String, String> commandOptions, Scanner scanner, Logger logger) throws IOException{
        String type = commandArguments.size() > 1 ? commandArguments.get(1) : "";
        switch(type.toLowerCase()){
            case "actor":
            case "hero":
                saveHero(arguments, commandArguments, options, commandOptions, scanner, logger);
                break;
            case "item":
                saveItem(arguments, commandArguments, options, commandOptions, scanner, logger);
                break;
            case "doc":
            case "document":
                saveDoc(arguments, commandArguments, options, commandOptions, scanner, logger);
                break;
            default: throw new IllegalArgumentException("This command only supports heroes(player actors), items and docs");
        }
    }

    private static void saveHero(ArrayList<String> arguments, ArrayList<String> commandArguments, HashMap<String, String> options, HashMap<String, String> commandOptions, Scanner scanner, Logger logger) throws IOException{
            actor target = null;
            boolean convertActor = false;
        for(Map.Entry<String, String> option : commandOptions.entrySet()){
            String key = option.getKey().toLowerCase();
            String value = option.getValue();
            
            switch(key){
                case "-t":
                case "--target":
                case "-a":
                case "--actor":
                case "-h":
                case "--hero":
                    UUID actorId = UUID.fromString(value);
                    if(game.getInstance().getKnownActors().containsKey(actorId)) target = game.getInstance().getKnownActors().get(actorId);
                    else if(game.getInstance().getKnownActors().values().toString().toLowerCase().contains(value.toLowerCase())){
                        for(Map.Entry<UUID, actor> entry : game.getInstance().getKnownActors().entrySet()){
                            if(entry.getValue().toString().toLowerCase().equals(value.toLowerCase())) target = entry.getValue();
                        }
                    }
                    else throw new IllegalArgumentException("Actor not found");
                    break;
                case "-c":
                case "--convert":
                    if(value.toLowerCase().equals("true")) convertActor = true;
                    break;
                default: throw new IllegalArgumentException("Unknown option: " + key);
            }
            
            

        }
        if(convertActor) writer.get_instance().write(target.toHero(), logger);
            else if(!convertActor && target.getClass().equals(hero.class)) writer.get_instance().write(target, logger);
            else throw new IllegalArgumentException("Actor must be of hero class or be converted to hero class with -c flag.");
    }

    private static void saveItem(ArrayList<String> arguments, ArrayList<String> commandArguments, HashMap<String, String> options, HashMap<String, String> commandOptions, Scanner scanner, Logger logger) throws IOException{
        item target = null;
        for(Map.Entry<String, String> option : commandOptions.entrySet()){
            String key = option.getKey().toLowerCase();
            String value = option.getValue();
            
            switch(key){
                case "-t":
                case "--target":
                case "-i":
                case "--item":
                    UUID itemId = UUID.fromString(value);
                    if(game.getInstance().getKnownItems().containsKey(itemId)) target = game.getInstance().getKnownItems().get(itemId);
                    else if(game.getInstance().getKnownItems().values().toString().toLowerCase().contains(value.toLowerCase())){
                        for(Map.Entry<UUID, item> entry : game.getInstance().getKnownItems().entrySet()){
                            if(entry.getValue().toString().toLowerCase().equals(value.toLowerCase())) target = entry.getValue();
                        }
                    }
                    else throw new IllegalArgumentException("Item not found");
                    break;
                default: throw new IllegalArgumentException("Unknown option: " + key);
            }
        }
        try{
            writer.get_instance().write(target, logger);
        }
        catch(Error e){
            logger.log(e.getStackTrace().toString());
            e.printStackTrace();
        }
    }
    private static void saveDoc(ArrayList<String> arguments, ArrayList<String> commandArguments, HashMap<String, String> options, HashMap<String, String> commandOptions, Scanner scanner, Logger logger) throws IOException{
        writer.get_instance().write(textDocument.getInstance(), logger);
    }
    
}
