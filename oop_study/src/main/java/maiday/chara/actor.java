package maiday.chara;

import maiday.game.*;
import maiday.items.*;

import java.io.IOException;
import java.util.*;

import maiday.action.actions;
import maiday.action.attackAction;
import maiday.fight.fight;

public abstract class actor {
    private String name;  //unique character name.
    private int maxHp;       //Health points. Creatures are dead whenever their hp drops to 0
    private int hp;
    private int def;      //Defence. Useful against attacks
    private int atk;    //Attack. Used to harm things
    private UUID id;    //Unique ID of an actor
    protected  boolean PC;      //PC is for "Player Character". Actor act according to their "AI" if they are not PCs
    protected String desc; //description for enemies
    protected actions _defaultAct;


    protected HashMap<UUID, actions> _actions = new HashMap<>();
    protected HashMap<UUID, item> inventory = new HashMap<>();

    public actor(String name, int hp, int def, int atk, boolean isPC){
        actions baseAttack = new attackAction(this);
        _actions.put(baseAttack.getId(), baseAttack);
        this._defaultAct = baseAttack;
        this.name = name;
        this.maxHp = hp;
        this.hp = hp;
        this.def = def;
        this.atk = atk;
        this.PC = isPC;
        this.id = UUID.randomUUID();
    }

    public actor(String name, int hp, int def, int atk, String desc, boolean isPC){
        actions baseAttack = new attackAction(this);
        _actions.put(baseAttack.getId(), baseAttack);
        this._defaultAct = baseAttack;
        this.name = name;
        this.maxHp = hp;
        this.hp = hp;
        this.def = def;
        this.atk = atk;
        this.desc = desc;
        this.PC = isPC;
        this.id = UUID.randomUUID();
    }

    protected actor(UUID id, String name, int maxHp, int hp, int def, int atk,
                String desc, boolean isPC,
                Map<UUID, actions> actions, Map<UUID, item> inventory) {
    this.id = id;
    this.name = name;
    this.maxHp = maxHp;
    this.hp = hp;
    this.def = def;
    this.atk = atk;
    this.desc = desc;
    this.PC = isPC;
    actions.values().forEach(this::add);   
    this.inventory.putAll(inventory);
}

    public boolean isPC(){
        return PC;
    }

    public void setAtk(int num){
        atk = num;
    }

    public void setDef(int num){
        def = num;
    }

    public void setMaxHp(int num){
        maxHp = num;
    }

    public int getAtk(){ return atk;}
    public int getDef(){return def;}
    public int getMaxHp(){return maxHp;}
    public int getHp(){return hp;}
    public String getDesc(){ return desc;}
    public HashMap<UUID, item> getInventory(){return inventory;}
    public UUID getId() {return id;}

    public void add(actions action){
        action.setOwner(this);
        _actions.put(action.getId(), action);
        

    }

    public void add(item stuff){
        inventory.put(stuff.getId(), stuff);
    }

    public void harm(int dmg, Logger logger) throws IOException{
        dmg -= this.getDef();
        if(dmg > 0)
            hp -= dmg;
        else dmg = 0;
        logger.log(this.getName() + " recieved " + dmg + " damage.");
    }

    public void heal(int heal){
        if(hp + heal <= maxHp) hp += heal;
        else{
            hp = maxHp;
        }
    }

    public String getName(){
        return name;
    }

    public actions getDefaultAct(){
        return _defaultAct;
    }

    public void setDefaultAct(actions action){
        this._defaultAct = action;
    }

     public void act(fight encounter, Scanner scanner){
     }

     public HashMap<UUID, actions> getActions(){ return this._actions;}

     public int defaultAct(actor target, fight combat, Logger logger, Scanner scanner) throws IOException{
        this._defaultAct.use(target, combat, logger, scanner);
        return 0;
     }

     public void equip(equipment equipment, Logger logger) throws IOException{

     }

     public hero toHero(){
        hero rHero = new hero(this.id, this.name, this.maxHp, this.hp, this.def, this.atk, this.getActions(), this.getInventory());
        return rHero;
     }

    @Override
    public String toString(){
        return getName();
    }

    protected boolean death(Logger logger) throws IOException{
        if(this.hp<=0){
            logger.log(name + " fainted!");
            return true;
        }
        else return false;
    }
}
