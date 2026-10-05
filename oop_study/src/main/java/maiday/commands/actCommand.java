package maiday.commands;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.UUID;

import maiday.game.*;
import maiday.chara.*;
import maiday.fight.fight;
import maiday.action.actions;

public abstract class actCommand {

    public static void act(ArrayList<String> arguments, ArrayList<String> commandArguments, HashMap<String, String> options, HashMap<String, String> commandOptions, Scanner scanner, Logger logger) throws IOException{
        String name = commandArguments.get(2).toLowerCase();
        UUID id = UUID.fromString(name);
        actor acting = new hero(name, 0, 0, 0);
        if(game.getInstance().getKnownActors().containsKey(id)) acting = game.getInstance().getKnownActors().get(id);
        else if (game.getInstance().getKnownActors().toString().toLowerCase().contains(name)){
            for(Map.Entry<UUID, actor> entry : game.getInstance().getKnownActors().entrySet()){
                UUID key = entry.getKey();
                actor value = entry.getValue();
                if(value.toString().toLowerCase().equals(name)) acting = value;
            }
        }
        else throw new IllegalArgumentException("Actor not found.");

        String actionName = commandArguments.get(1).toLowerCase();
        UUID actionId = UUID.fromString(actionName);
        actions actingAction = new actions(null, "Lorem", "Ipsum");
        if(acting.getActions().containsKey(actionId)) actingAction = acting.getActions().get(actionId);
        else if(acting.getActions().toString().contains(actionName)){
            for(Map.Entry<UUID, actions> entry : acting.getActions().entrySet()){
                UUID key = entry.getKey();
                actions value = entry.getValue();
                if(value.toString().toLowerCase().equals(actionName)) actingAction = acting.getActions().get(key);
            }
        }
        else throw new IllegalArgumentException("Actor does not have action " + actionName);
        
        String targetName = commandArguments.get(3).toLowerCase();
        UUID targetId = UUID.fromString(targetName);
        actor target = new zhegan();
        if(game.getInstance().getKnownActors().containsKey(targetId)) target = game.getInstance().getKnownActors().get(targetId);
        else if(game.getInstance().getKnownActors().values().toString().contains(targetName)){
            for(Map.Entry<UUID, actor> entry : game.getInstance().getKnownActors().entrySet()){
                UUID key = entry.getKey();
                actor value = entry.getValue();
                if(value.toString().toLowerCase().equals(targetName)) target = game.getInstance().getKnownActors().get(key);
            }
        
        }
        else throw new IllegalArgumentException("Enemy not found");

        ArrayList<actor> participants = new ArrayList<>();
        participants.add(acting);
        participants.add(target);
        fight combat = new fight(participants);
        actingAction.use(target, combat, logger, scanner);

    }

}
