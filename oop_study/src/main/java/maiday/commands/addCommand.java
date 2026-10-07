package maiday.commands;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.UUID;

import maiday.action.actions;
import maiday.chara.hero;
import maiday.chara.actor;
import maiday.game.*;
import maiday.items.item;

public abstract class addCommand {
    
    public static void addCharsVer(ArrayList<String> arguments, ArrayList<String> commandArguments, HashMap<String, String> options, HashMap<String, String> commandOptions, Scanner scanner, Logger logger) throws IOException{
        if (commandOptions.size()<2){
            logger.log("This command requires a target(--char or --actor) and an item or ability to add(--item or --act");
            return;
        }
        
                actor target = new hero("", 0, 0, 0);
                actions targetAction = new actions(null, "", "");
                item targetItem = new item("");
                UUID targetId = new UUID(0, 0);
        for(Map.Entry<String, String> entry : commandOptions.entrySet()){
                String key = entry.getKey();
                String value = entry.getValue().toLowerCase();
                switch(key){
                    case "-c":
                    case "--char":
                    case "--character":
                    case "-a":
                    case "--actor":
                    case "--target":
                        
                        targetId = UUID.fromString(value);
                        if(game.getInstance().getKnownActors().containsKey(targetId)){
                            target = game.getInstance().getKnownActors().get(targetId);
                        }
                        else if(game.getInstance().getKnownActors().toString().toLowerCase().contains(value.toLowerCase())){
                            for(Map.Entry<UUID, actor> entry2 : game.getInstance().getKnownActors().entrySet()){
                                if(entry2.getValue().toString().toLowerCase().equals(value)){
                                    target = game.getInstance().getKnownActors().get(entry2.getKey());
                                }
                            }
                        }
                        else throw new IllegalArgumentException("Actor not found.");
                        break;
                    case "--item":
                    case "-i":
                    case "--act":
                    case "--action":
                        UUID actionItemId = UUID.fromString(value);
                        
                        if(game.getInstance().getKnownActions().containsKey(actionItemId)) targetAction = game.getInstance().getKnownActions().get(actionItemId);
                        else if(game.getInstance().getKnownActions().toString().toLowerCase().contains(value)){
                            for(Map.Entry<UUID, actions> entry2 : game.getInstance().getKnownActions().entrySet()){
                                if(entry2.getValue().toString().toLowerCase().equals(value)){
                                    targetAction = game.getInstance().getKnownActions().get(entry2.getKey());
                                }
                            }
                        }
                        else if(game.getInstance().getKnownItems().containsKey(actionItemId)) targetItem = game.getInstance().getKnownItems().get(actionItemId);
                        else if(game.getInstance().getKnownItems().toString().toLowerCase().contains(value)){
                            for(Map.Entry<UUID, item> entry2 : game.getInstance().getKnownItems().entrySet()){
                                if(entry2.getValue().toString().toLowerCase().equals(value)){
                                    targetItem = game.getInstance().getKnownItems().get(entry2.getKey());
                                }
                            }
                        }
                        else throw new IllegalArgumentException("Item or action not found");
                        break;
                    default: throw new IllegalArgumentException("Unknown option");


                }
                
            }
            if(target.toString().isEmpty()) throw new IllegalArgumentException("No valid actor selected");
                else if(targetAction.toString().isEmpty() && targetItem.toString().isEmpty()) throw new IllegalArgumentException("Not a valid action or item");
                else if(!targetAction.toString().isEmpty()){
                    target.add(targetAction);
                }
                else if(!targetItem.toString().isEmpty()){
                    target.add(targetItem);
                }
                else return;
    }

    public static void addTextVer(){

    }
}
