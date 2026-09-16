//a fight is used to determine outcomes of encounters.
package fight;

import java.util.*;
import chara.*;

public class fight {
    private ArrayList<actor> participants = new ArrayList<>();

    public fight(ArrayList<actor> participants, Scanner scanner){
        this.participants = participants;
        flow(scanner);
        
    }

    public ArrayList<actor> getParticipants(){
        return participants;
    }

    //characters act on their turns. Fight is finished when all enemies perish
    private void flow(Scanner scanner){
        while(true){
        for(actor fighter : new ArrayList<actor>(participants)){
            if(fighter.isPC()){
                fighter.act(this, scanner);
                
            }
            else if(!fighter.isPC()){
                if(fighter.defaultAct(this) == -1)return;
            }
            boolean endofcombat = participants.stream().allMatch(n -> n.isPC() == fighter.isPC());
            if(endofcombat && fighter.isPC()){
                System.out.println("You won in that combat!");
                return;
            }
            else if(endofcombat && !fighter.isPC()){
                System.out.println("You lost in that combat!");
                return;
            }
        }
    }
}



}
