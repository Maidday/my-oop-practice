package maiday.items;

import maiday.chara.actor;

public class armor extends equipment{
    protected int _def;
    public armor(String name, int def){
        super(name, "armor", def, "");
    }
    public armor(String name, int def, String desc){
        super(name, "armor", def, desc);
        this._def = def;
    }

    @Override 
    public void modStats(actor target, int mod){
        target.setDef(target.getDef() + this._def * mod);
    }
    
}
