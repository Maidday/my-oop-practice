package chara;

import game.*;
import items.*;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

import fight.fight;

public abstract class actor {
    private String name;  //unique character name.
    private int maxHp;       //Health points. Creatures are dead whenever their hp drops to 0
    private int hp;
    private int def;      //Defence. Useful against attacks
    private int atk;    //Attack. Used to harm things
    protected  boolean PC;      //PC is for "Player Character". Actor act according to their "AI" if they are not PCs
    protected String desc; //description for enemies

    protected ArrayList<item> inventory = new ArrayList<>();

    public actor(String name, int hp, int def, int atk, boolean isPC){
        this.name = name;
        this.maxHp = hp;
        this.hp = hp;
        this.def = def;
        this.atk = atk;
        this.PC = isPC;
    }

    public actor(String name, int hp, int def, int atk, String desc, boolean isPC){
        this.name = name;
        this.maxHp = hp;
        this.hp = hp;
        this.def = def;
        this.atk = atk;
        this.desc = desc;
        this.PC = isPC;
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

    public void add(item stuff){
        inventory.add(stuff);
    }

    public void add(item stuff, int quantity){
        for(int i = 0; i<quantity; i++){
            inventory.add(stuff);
        }
    }

    public int attack(actor target, fight encounter){
        int dmg = atk + (new Random().nextInt(10)) - target.getDef() - (new Random().nextInt(10));
        if(dmg > 0) {target.harm(dmg, encounter); return dmg;}
        else return 0;
    }

    public void harm(int dmg, fight encounter){
        if(dmg > 0)
            hp -= dmg;
        if(hp<=0) death(encounter);
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

    public int defaultAct(fight encounter){
        ArrayList<actor> enemies = new ArrayList<>(encounter.getParticipants());
        for(actor e : encounter.getParticipants()){
            if(e.isPC() == this.isPC()) enemies.remove(e);
        }
        if(enemies.isEmpty()) return -1;
        actor target = enemies.get(ThreadLocalRandom.current().nextInt(enemies.size()));
        System.out.println(this.getName() + " attacks " + target.getName() + " for " + attack(target, encounter) + " damage.");
        return 0;
    }

     public void act(fight encounter, Scanner scanner){
     }

     public void equip(equipment equipment){

     }

    @Override
    public String toString(){
        return getName();
    }

    protected void death(fight encounter){
        System.out.println(name + " fainted!");
        encounter.getParticipants().remove(this);
    }
}
