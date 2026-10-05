package maiday.items;

import maiday.chara.*;

public abstract class usable extends item{
    private boolean consumable;
    
    public usable(String name, boolean consumable){
        super(name);
        this.consumable = consumable;
    }
    public usable(String name, String desc, boolean consumable){
        super(name, desc);
        this.consumable = consumable;
    }

    public boolean isConsumable(){return consumable;}

    protected void consume(actor target){
        target.getInventory().remove(this);
    }
}
