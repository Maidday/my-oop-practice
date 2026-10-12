package maiday.items;

import java.io.IOException;

import maiday.chara.*;
import maiday.game.*;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class item {
    private String name;
    protected String desc;
    private UUID id;
    protected int stat;

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

    public item(String name, int stat, String desc){
        this.stat = stat;
        this.name = name;
        this.desc = desc;
        this.id = UUID.randomUUID();
    }

    @JsonCreator 
    public item(
        @JsonProperty("id") UUID id,
        @JsonProperty("name") String name,
        @JsonProperty("stat") int stat,
        @JsonProperty("desc") String desc
    ){
        this.id = id;
        this.name=name;
        this.desc = desc;
        this.stat = stat;
    }

    public String getDesc(){
        return desc;
    }

    public equipment toEquipment(){
        return new equipment(this.name, "", this.getDesc());
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
