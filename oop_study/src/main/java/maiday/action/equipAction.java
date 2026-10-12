package maiday.action;

import java.io.IOException;
import java.util.*;

import maiday.chara.actor;
import maiday.items.*;
import maiday.fight.fight;
import maiday.game.Logger;

public class equipAction extends actions{

    public equipAction(actor owner){
        super(owner, "Equip", "Equip an equipment.");
    }

    @Override 
    public void onUse(actor target, fight combat, Logger logger, Scanner scanner) throws IOException{
        target = _owner;
        logger.log(target.toString() + ": Chooses an item to equip.");
        for(Map.Entry<UUID, item> item : _owner.getInventory().entrySet()){
            logger.log(item.getValue().toString() + "   " + item.getKey().toString());

        }
        String targetItemName = scanner.nextLine();
        item targetItem = null;
        try{
            targetItem = _owner.getInventory().get(UUID.fromString(targetItemName));
        }
        catch(IllegalArgumentException ignored){
        }
        if(targetItem == null){
            for(Map.Entry<UUID, item> entry : _owner.getInventory().entrySet()){
                if(entry.getValue().toString().equalsIgnoreCase(targetItemName)){
                    targetItem = entry.getValue();
                    break;
                }
            }
        }
        if(targetItem == null) throw new IllegalArgumentException("Item not found");
        if(targetItem instanceof equipment equipmentItem) _owner.equip(equipmentItem, logger);
        else logger.log(targetItem.toString() + " is not equipment.");


        
    }
    
}
