package maiday.commands;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.UUID;
import maiday.chara.actor;
import maiday.chara.hero;
import maiday.game.*;
import maiday.items.item;
import maiday.action.actions;

public abstract class listCommand {

    public static void list(ArrayList<String> arguments, ArrayList<String> commandArguments, HashMap<String, String> options, HashMap<String, String> commandOptions, Scanner scanner, Logger logger) throws IOException{
        if(commandArguments.size()<2){
            logger.log("Choose castegory: characters, items or abilities.");
            String answer = scanner.nextLine().toLowerCase();
            commandArguments.add(1, answer);
        }
        if(commandOptions.size()<1){
            String category = commandArguments.get(1).toLowerCase();
            switch(category){
                case "characters":
                case "chars":
                case "char":
                case "actors":
                case "actor":
                    for(Map.Entry<UUID, actor> entry : game.getInstance().getKnownActors().entrySet()){
                        logger.log(entry.getValue().toString() + " UUID: " + entry.getKey().toString());
                    }
                    break;
                case "items":
                case "item":
                    for(Map.Entry<UUID, item> entry : game.getInstance().getKnownItems().entrySet()){
                        logger.log(entry.getValue().getName() + " UUID: " + entry.getKey().toString());
                    }
                    
                    break;
                case "actions":
                case "action":
                case "abilities":
                case "ability":
                    for(Map.Entry<UUID, actions> entry : game.getInstance().getKnownActions().entrySet()){
                        logger.log(entry.getValue().toString() + " UUID: " + entry.getKey().toString());
                    }
                    break;
                default:
                    throw new IllegalArgumentException("Unknown category for ls command.");
            }
            
        }
        for(Map.Entry<String, String> Entry : commandOptions.entrySet()){
                String key = Entry.getKey().toLowerCase();
                String value = Entry.getValue();
                UUID IDvalue = UUID.fromString(value);
                switch(key){
                    case "--id":
                        
        
                        switch(commandArguments.get(1).toLowerCase()){
                            case "chars":
                            case "characters":
                            case "actors":
                            case "actor":
                                actor targetActor = new hero("John Doe", 0, 0, 0);
                                if(game.getInstance().getKnownActors().containsKey(IDvalue)){
                                    targetActor = game.getInstance().getKnownActors().get(IDvalue);
                                    
                                }
                                else if (game.getInstance().getKnownActors().toString().contains(value)){
                                    for(Map.Entry<UUID, actor> entry : game.getInstance().getKnownActors().entrySet()){
                                        if(entry.getValue().toString().equals(value)){
                                            targetActor = entry.getValue();
                                        }
                                    }
                                }
                                else{
                                    logger.log("Unknown actor or UUID");
                                    return;
                                }
                                logger.log(targetActor.getName() + "\n" + targetActor.getDesc() +  
                                                "\nHP: " + targetActor.getHp() + "/" + targetActor.getMaxHp() +
                                                "\nAttack: " + targetActor.getAtk() +
                                                "\nDefence: " + targetActor.getDef() +
                                                "\nID: " + targetActor.getId() +
                                                "\nKnown actions: ");
                                    for(Map.Entry<UUID, actions> entry : targetActor.getActions().entrySet()) logger.log(entry.getValue().toString() + "    " + entry.getKey().toString());
                                    break;
                            case "item":
                            case "items":
                                item targetItem = new item("");
                                if(game.getInstance().getKnownItems().containsKey(IDvalue)){
                                    targetActor = game.getInstance().getKnownActors().get(IDvalue);
                                    
                                }
                                else if (game.getInstance().getKnownItems().toString().contains(value)){
                                    for(Map.Entry<UUID, item> entry : game.getInstance().getKnownItems().entrySet()){
                                        if(entry.getValue().toString().equals(value)){
                                            targetItem = entry.getValue();
                                        }
                                    }
                                }
                                else{
                                    logger.log("Unknown item or UUID");
                                    return;
                                }
                                logger.log(targetItem.getName() + "\n" + targetItem.getDesc() + "\nID: " + targetItem.getId());
                                    break;
                                case "action":
                                case "ability":
                                    actions targetAction = new actions("anonimous action");
                                    if(game.getInstance().getKnownActions().containsKey(IDvalue)){
                                    targetAction = game.getInstance().getKnownActions().get(IDvalue);
                                    
                                }
                                else if (game.getInstance().getKnownActions().toString().contains(value)){
                                    for(Map.Entry<UUID, actions> entry : game.getInstance().getKnownActions().entrySet()){
                                        if(entry.getValue().toString().equals(value)){
                                            targetAction = entry.getValue();
                                        }
                                    }
                                }
                                else{
                                    logger.log("Unknown action or UUID");
                                    return;
                                }
                                logger.log(targetAction.getName() + "\n" + targetAction.getDesc() + "\nOwner: " + targetAction.getOwner() + "\nID: " + targetAction.getId());
                        }
                }
            }
    }
    
}
