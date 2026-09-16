package items;

import chara.*;

public class rustySword extends equipment{

    private int dmg = 5;

    public rustySword(String name){
        super(name, "weapon", "Rustic sword. Cuts a little bit. More dangerous to you than to enemy.");
    }

    @Override 
    protected void modStats(actor target, int mod){
        target.setAtk(target.getAtk() + dmg * mod);
    }
}