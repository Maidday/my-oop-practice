package items;
import chara.*;

public class healthPotion extends usable{

    public healthPotion(){
        super("Health Potion", "Heals you for 5 HP", true);
    }

    @Override 
    public void onUse(actor target, int mod){
        System.out.println("You are healed for " + 5*mod + " HP.");
        target.heal(5*mod);
        consume(target);
    }
    
}
