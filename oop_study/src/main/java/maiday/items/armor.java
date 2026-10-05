package maiday.items;

import maiday.chara.actor;

public class armor extends equipment{
    protected int _def;
    public armor(String name, int def){
        super(name, "armor");
        this._def = def;
    }
    public armor(String name, int def, String desc){
        super(name, "armor", desc);
        this._def = def;
    }

    @Override 
    public void modStats(actor target, int mod){
        target.setDef(target.getDef() + this._def * mod);
    }
    
}
