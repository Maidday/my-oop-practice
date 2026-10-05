package maiday.game;
import maiday.chara.*;
import maiday.fight.fight;
import maiday.action.*;
import maiday.items.*;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.*;

public class game {

    private HashMap<UUID, actor> knownActors = new HashMap<>(); //all instances of new actors should be added to this list
    private HashMap<UUID, item> knownItems = new HashMap<>(); //same for items
    private HashMap<UUID, actions> knownActions = new HashMap<>(); //same for actions
    private game() { }

    private static final game INSTANCE = new game(); //game is a singleton. refer to it as game.getInstance()...

    public static game getInstance() {
        return INSTANCE;
    }

    public void processTurn(actor fighter, fight combat, Logger logger, Scanner scanner) throws IOException{
        if(fighter.isPC()){
            logger.log(fighter.getName() + " chooses an action. List of actions: ");
            for(Map.Entry<UUID, actions> entry : fighter.getActions().entrySet()){
                logger.log(entry.getValue().toString());
            }
            String chosen = scanner.nextLine().toLowerCase().replace(" ", "");
            for(Map.Entry<UUID, actions> entry : fighter.getActions().entrySet()){
                if(entry.getValue().toString().toLowerCase().replace(" ", "").equals(chosen)){
                    entry.getValue().use(fighter, combat, logger, scanner);
                    continue;
                }
                

            }
            logger.log("Action unknown");
        }
        else if(!fighter.isPC()){
            fighter.defaultAct(fighter, combat, logger, scanner);
        }

        else throw new UncheckedIOException("Boolean is not boolean.",null);

        if(checkDeath(fighter)){
            logger.log(fighter.toString() + "has perished.");
            combat.getParticipants().remove(fighter);
        }
    }

    public HashMap<UUID, actor> getKnownActors(){ return knownActors;}
    public void addActor(actor _actor){
        knownActors.put(_actor.getId(), _actor);
    }
    public void removeActor(actor _actor){
        knownActors.remove(_actor.getId());
    }
    public void removeActor(String _actor){
        UUID target = UUID.fromString("");
        for(Map.Entry<UUID, actor> entry : knownActors.entrySet()){
            if(entry.getValue().toString().toLowerCase().equals(_actor)){
                target = entry.getKey();
                break;
            }
        }
        if(target.toString().equals("")) return;
        else knownActors.remove(target);

    }
    public void removeActor(UUID actorID){
        if(knownActors.containsKey(actorID)) knownActors.remove(actorID);
        
    }

    public HashMap<UUID, item> getKnownItems(){ return knownItems;}
    public void addItem(item _item){
        knownItems.put(_item.getId(), _item);
    }
    public void removeItem(item _item){
        knownItems.remove(_item.getId());
    }
    public void removeItem(String _item){
        UUID target = UUID.fromString("");
        for(Map.Entry<UUID, item> entry : knownItems.entrySet()){
            if(entry.getValue().toString().toLowerCase().equals(_item)){
                target = entry.getKey();
                break;
            }
        }
        if(target.toString().equals("")) return;
        else knownActors.remove(target);

    }
    public void removeItem(UUID itemID){
        if(knownActors.containsKey(itemID)) knownActors.remove(itemID);
        
    }

    public HashMap<UUID, actions> getKnownActions(){return knownActions;}
    public void addAction(actions action){
        knownActions.put(action.getId(), action);
    }

    public void removeAction(actions _action){
        knownActions.remove(_action.getId());
    }
    public void removeAction(String _action){
        UUID target = UUID.fromString("");
        for(Map.Entry<UUID, actions> entry : knownActions.entrySet()){
            if(entry.getValue().toString().toLowerCase().equals(_action)){
                target = entry.getKey();
                break;
            }
        }
        if(target.toString().equals("")) return;
        else knownActions.remove(target);

    }
    public void removeAction(UUID actionID){
        if(knownActors.containsKey(actionID)) knownActions.remove(actionID);
        
    }

    private boolean checkDeath(actor target){
        if(target.getHp() <= 0) return true;
        else return false;
    }
    
}
