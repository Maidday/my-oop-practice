package items;

import chara.actor;

public class heroSword extends equipment{

    private int dmg = 99;

    public heroSword(){
        super("Hero Sword", "A sword of heroes.");
    }

    @Override 
    protected void modStats(actor target, int mod){
        target.setAtk(target.getAtk() + dmg * mod);
    }
    
}
