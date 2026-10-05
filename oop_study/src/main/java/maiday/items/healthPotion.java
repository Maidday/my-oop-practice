package maiday.items;
import maiday.chara.*;
import maiday.game.*;

public class healthPotion extends usable{

    public healthPotion(){
        super("Health Potion", "Heals you for 5 HP", true);
    }

    @Override 
    public void onUse(actor target, int mod, Logger logger){
        System.out.println("You are healed for " + 5*mod + " HP.");
        target.heal(5*mod);
        consume(target);
    }
    
}
