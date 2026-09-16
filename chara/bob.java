package chara;

import fight.fight;

public class bob extends actor{

    public bob(){
        super("Bob", 99, 99, 0, false);
    }

    @Override
    public int attack(actor target, fight encounter){
        int dmg = this.getAtk();
        if(dmg > 0) {target.harm(dmg, encounter); return dmg;}
        else return 0;
    }
}
