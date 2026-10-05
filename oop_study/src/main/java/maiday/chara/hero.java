package maiday.chara;

import java.io.IOException;

import maiday.action.attackAction;
import maiday.items.*;
import maiday.game.*;

import java.util.Map;
import java.util.UUID;


public class hero extends actor {

    public hero(String name, int hp, int def, int atk){
        super(name, hp, def, atk, "This is you.", true);
    }
    
    private equipment weapon;
    private equipment armor;

    /*@Override               REWRITE ACTIONS TO BE MANAGED BY GAME CLASS
    public void act(fight encounter, Scanner scanner){
        ArrayList<actor> enemies = new ArrayList<>(encounter.getParticipants());
            for(actor e : encounter.getParticipants()){
                if(e.isPC() == this.isPC()) enemies.remove(e);
            }
        
        System.out.println("Choose action:");
        System.out.println("ATTACK  ||  ITEMS  ||  ACT");
        String choice = scanner.nextLine().toLowerCase().replace(" ", "");
        if(choice.equals("attack")){
            System.out.println("Choose target:");
            
            for(actor e : enemies){
                System.out.println(e.toString());
            }
            choice = scanner.nextLine().toLowerCase().replace(" ", "");
            for(actor e : enemies){
                if(e.getName().toLowerCase().replace(" ", "").equals(choice)){
                    System.out.println("You attacked " + e.toString() + " and dealt " + attack(e, encounter) + " damage!");
                    return;
                }

                else System.out.println("Not a valid target");
            }
        }
        else if(choice.equals("items")){
                System.out.println("Choose item: ");
                for(item e : inventory){
                    System.out.println(e.toString());
                }
                choice = scanner.nextLine().toLowerCase().replace(" ", "");
                for(item e : inventory){
                    if(choice.equals(e.toString())){
                    e.onUse(this, 1);
                    return;
                    }
                }
                System.out.println("No such item in inventory.");
            }
        else if(choice.equals("act")){
            System.out.println("Choose act: ");
            System.out.println("INSPECT   ||  RUN   || STATS");
            choice = scanner.nextLine().toLowerCase().replace(" ", "");
            if(choice.equals("inspect")){
                System.out.println("Choose target:");
            
                for(actor e : enemies){
                    System.out.println(e.toString());
                }
                choice = scanner.nextLine().toLowerCase().replace(" ", "");
                for(actor e : enemies){
                    if(e.getName().toLowerCase().replace(" ", "").equals(choice)){
                    System.out.println("This is a " + e.getName() + ". " + e.getDesc());
                    return;
                    }

                    
                }
                System.out.println("Not a valid target");
            }
            else if(choice.equals("run")){
                System.out.println("You ran away like a coward");
                encounter.getParticipants().remove(this);
            }
            else if(choice.equals("stats")){
                System.out.println("This is you.");
                System.out.println("Your name is " + this.getName());
                System.out.println("You have " + this.getHp() + "/" + this.getMaxHp() + " HP");
                System.out.println("Your defense is " + this.getDef());
                System.out.println("Your attack is " + this.getAtk());
            }
           
            else{
            System.out.println("Unknown action. Try again.");
            act(encounter, scanner);
        }

        }
        
    }
*/
    @Override 
    public void equip(equipment equipment, Logger logger) throws IOException{
        if (inventory.containsValue(equipment)){
            if((equipment.getSlot().equals("weapon") && this.weapon != null)||(equipment.getSlot().equals("armor") && this.armor != null)){
                if(equipment.getSlot().equals("armor")) dequip(armor, logger);
                else if(equipment.getSlot().equals("weapon")) dequip(weapon, logger);
            }
            if((equipment.getSlot().equals("weapon") && this.weapon == null)||(equipment.getSlot().equals("armor") && this.armor == null)){
                equipment.equip(this);
                if(equipment.getSlot().equals("weapon")) this.weapon = equipment;
                if(equipment.getSlot().equals("armor")) this.armor = equipment;
                UUID equipmentID = UUID.fromString("");
                for(Map.Entry<UUID, item> entry : this.getInventory().entrySet()){
                    item value = entry.getValue();
                    UUID key = entry.getKey();
                    if(equipment.toString().toLowerCase().equals(value.toString().toLowerCase())){
                        equipmentID = key;
                    }
                }
                inventory.remove(equipmentID);
                logger.log("Successfully equiped " + equipment.toString());
                return;
            }
            
        }

        else logger.log("No such equipment " + equipment.toString() + " in inventory");
    }

    public void dequip(equipment equipment, Logger logger) throws IOException{
        if(equipment.getSlot() == "weapon" && equipment == this.weapon){
            weapon.dequip(this);
            inventory.put(weapon.getId(), weapon);
            weapon = null;
            return;
        }
        if(equipment.getSlot() == "armor" && equipment == this.armor){
            armor.dequip(this);
            inventory.put(armor.getId(), armor);
            armor = null;
            return;
        }
       logger.log("Nothing to dequip");
    }

    public void inspect(actor target){
        System.out.println(target.toString());
        System.out.println(target.getDesc());
        System.out.println("They have " + target.getHp() + " health left.");
    }
    public void inspect(item target){
        if(inventory.values().contains(target)){
            System.out.println(target.getDesc());
        }
        else System.out.println("No such item in inventory");
    }

}
