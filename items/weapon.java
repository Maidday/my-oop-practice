package items;

import chara.actor;

public class weapon extends equipment{
    protected int _dmg;
    public weapon(String name, int dmg){
        super(name, "weapon", "");
        this._dmg = dmg;
    }
    public weapon(String name, int dmg, String desc){
        super(name, "weapon", desc);
        this._dmg = dmg;
    }
    @Override 
    protected void modStats(actor target, int mod){
        target.setAtk(target.getAtk() + this._dmg * mod);
    }
}
