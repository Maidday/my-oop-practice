//a fight is used to determine outcomes of encounters.
package maiday.fight;

import java.io.IOException;
import java.util.*;
import maiday.chara.*;
import maiday.game.*;

public class fight {
    private ArrayList<actor> participants = new ArrayList<>();

    public fight(ArrayList<actor> participants){
        this.participants = participants;
        
    }

    public ArrayList<actor> getParticipants(){
        return participants;
    }

    public void StartCombat(Scanner scanner, Logger logger) throws IOException{
        flow(scanner, logger);
    }

    //characters act on their turns. Fight is finished when all enemies perish
    private void flow(Scanner scanner, Logger logger) throws IOException{
        while(true){
        for(actor fighter : new ArrayList<actor>(participants)){
            game.getInstance().processTurn(fighter, this, logger, scanner);
            boolean endofcombat = participants.stream().allMatch(n -> n.isPC() == fighter.isPC());
            if(endofcombat && fighter.isPC()){
                logger.log("You won in that combat!");
                return;
            }
            else if(endofcombat && !fighter.isPC()){
                logger.log("You lost in that combat!");
                return;
            }
        }
    }
}



}
