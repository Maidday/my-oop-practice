package game;
import chara.*;
import fight.fight;
import action.*;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.*;

public class game {

    private ArrayList<actor> knownActors = new ArrayList<>();

    private game() { }

    private static final game INSTANCE = new game();

    public static game getInstance() {
        return INSTANCE;
    }

    public void processTurn(actor fighter, fight combat, Logger logger, Scanner scanner) throws IOException{
        if(fighter.isPC()){
            logger.log(fighter.getName() + " chooses an action. List of actions: ");
            for(actions a : fighter.getActions()){
                logger.log(a.toString());
            }
            String chosen = scanner.nextLine().toLowerCase().replace(" ", "");
            for(actions a : fighter.getActions()){
                if(a.toString().toLowerCase().replace(" ", "").equals(chosen)){
                    a.use(fighter, combat, logger, scanner);
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

    public ArrayList<actor> getKnownActors(){ return knownActors;}
    public void addActor(actor _actor){
        knownActors.add(_actor);
    }
    public void removeActor(actor _actor){
        knownActors.remove(_actor);
    }
    public void removeActor(String _actor){
        ArrayList<actor> temp = new ArrayList<>(knownActors);
        for(actor a : temp){
            if(a.getName().equals(_actor)) {
                knownActors.remove(a);
                return;
            }
        }
    }
    public void removeActor(UUID actorID){
        ArrayList<actor> temp = new ArrayList<>(knownActors);
        for(actor a : temp){
            if(a.getId().equals(actorID)) {
                knownActors.remove(a);
                return;
            }
        }
    }

    private boolean checkDeath(actor target){
        if(target.getHp() <= 0) return true;
        else return false;
    }
    
}
