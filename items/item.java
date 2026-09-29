package items;

import java.io.IOException;

import chara.*;
import game.*;
import java.util.UUID;

public abstract class item {
    private String name;
    protected String desc;
    private UUID id;

    public UUID getId(){return id;}


    public item(String name){
        this.name = name;
        this.id = UUID.randomUUID();
    }

    public item(String name, String desc){
        this.name = name;
        this.desc = desc;
        this.id = UUID.randomUUID();
    }

    public String getDesc(){
        return desc;
    }



    public String getName(){
        return name;
    }

    @Override 
    public String toString(){
        return getName();
    }

    protected void modStats(actor target, int mod){

    }
    
    public void onUse(actor target, int mod, Logger logger) throws IOException{
        
    }

    
}
