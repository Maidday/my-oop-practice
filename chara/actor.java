package chara;

import game.*;
import items.*;

import java.io.IOException;
import java.util.*;

import action.actions;
import action.attackAction;
import fight.fight;

public abstract class actor {
    private String name;  //unique character name.
    private int maxHp;       //Health points. Creatures are dead whenever their hp drops to 0
    private int hp;
    private int def;      //Defence. Useful against attacks
    private int atk;    //Attack. Used to harm things
    private UUID id;    //Unique ID of an actor
    protected  boolean PC;      //PC is for "Player Character". Actor act according to their "AI" if they are not PCs
    protected String desc; //description for enemies
    protected actions _defaultAct = new attackAction(this);


    protected ArrayList<actions> _actions = new ArrayList<>();
    protected ArrayList<item> inventory = new ArrayList<>();

    public actor(String name, int hp, int def, int atk, boolean isPC){
        _actions.add(new attackAction(this));
        this.name = name;
        this.maxHp = hp;
        this.hp = hp;
        this.def = def;
        this.atk = atk;
        this.PC = isPC;
        this.id = UUID.randomUUID();
    }

    public actor(String name, int hp, int def, int atk, String desc, boolean isPC){
        _actions.add(new attackAction(this));
        this.name = name;
        this.maxHp = hp;
        this.hp = hp;
        this.def = def;
        this.atk = atk;
        this.desc = desc;
        this.PC = isPC;
        this.id = UUID.randomUUID();
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
    public ArrayList<item> getInventory(){return inventory;}
    public UUID getId() {return id;}

    public void add(actions action){
        _actions.add(action);
    }

    public void add(item stuff){
        inventory.add(stuff);
    }

    public void add(item stuff, int quantity){
        for(int i = 0; i<quantity; i++){
            inventory.add(stuff);
        }
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

     public ArrayList<actions> getActions(){ return this._actions;}

     public int defaultAct(actor target, fight combat, Logger logger, Scanner scanner) throws IOException{
        this._defaultAct.use(target, combat, logger, scanner);
        return 0;
     }

     public void equip(equipment equipment, Logger logger) throws IOException{

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
