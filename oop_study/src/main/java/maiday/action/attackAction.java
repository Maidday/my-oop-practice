package maiday.action;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import maiday.chara.*;
import maiday.game.*;
import maiday.fight.*;

public class attackAction extends actions{

    public attackAction(actor _owner){
        super(_owner, "Attack action", "Attack an enemy with your standart atttack.");
    }

    @Override 
    protected void onUse(actor target, fight combat, Logger logger, Scanner scanner) throws IOException{
        List<actor> enemies = combat.getParticipants().stream().filter(actor -> actor.isPC() != _owner.isPC()).collect(Collectors.toList());

        if(_owner.isPC() && target.equals(null)){
            target = chooseTarget(enemies, logger, scanner);
        }

        else if(!_owner.isPC()){
            target = randomTarget(enemies);
        }

        int dmg = this._owner.getAtk() + new Random().nextInt(10);
        logger.log(_owner.toString() + " attacked " + target + " for " + dmg + " damage.");
        target.harm(dmg, logger);
    }

    protected actor chooseTarget(List<actor> enemies, Logger logger, Scanner scanner) throws IOException{
        logger.log(_owner.toString() + " Chooses target for attack: ");
            for(actor e : enemies){
                logger.log(e.toString());
            }
            String choice = scanner.nextLine().toLowerCase().replace(" ", "");
            for(actor e : enemies){
                if(e.toString().toLowerCase().replace(" ", "").equals(choice)){
                    return e;
                }
            }
            logger.log("No valid target chosen");
            return chooseTarget(enemies, logger, scanner);
    }

    protected actor randomTarget(List<actor> enemies){
        return enemies.get(new Random().nextInt(enemies.size()));
    }
    
}
