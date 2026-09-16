package items;

import chara.*;

public class clothArmor extends equipment{
    private int def = 3;

    public clothArmor(){
        super("Cloth armor", "armor", "Cheapest armor awailable.");
    }
    
    @Override 
    public void modStats(actor target, int mod){
        target.setDef(target.getDef() + def * mod);
    }
}
