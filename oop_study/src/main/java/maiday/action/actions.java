package maiday.action;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.io.IOException;
import java.util.Scanner;
import java.util.UUID;

import maiday.chara.*;
import maiday.game.*;
import maiday.fight.*;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type", defaultImpl = attackAction.class)
@JsonSubTypes({@JsonSubTypes.Type(value = attackAction.class, name = "attackAction")})
public class actions {

    protected actor _owner;
    protected String _name;
    protected String _description;
    private UUID _id;

    @JsonCreator 
    public actions(
        @JsonProperty("name") String name
    ){
        this._name = name;
        this._id = UUID.randomUUID();
    }

    public actions(actor owner, String name, String desc){
        this._owner = owner;
        this._name = name;
        this._description = desc;
        this._id = UUID.randomUUID();
    }

    protected actions(actor owner, String name, String desc, UUID id){
        this._owner = owner;
        this._name = name;
        this._description = desc;
        this._id = id;
    }

    public actions(actions example){
        this._owner = example.getOwner();
        this._name = example.getName();
        this._description = example.getDesc();
        this._id = UUID.randomUUID();
    }


    protected void onUse(actor target, fight combat, Logger logger, Scanner scanner)  throws IOException{

    }

    public void use(actor target, fight combat, Logger logger, Scanner scanner)  throws IOException{
        onUse(target, combat, logger, scanner);
    }

    public void setOwner(actor owner){this._owner = owner;}

    @JsonIgnore
    public actor getOwner(){return this._owner;}

    public String getDesc(){return this._description;}

    public String getName(){ return this._name;}

    public UUID getId(){return this._id;}

    @Override 
    public String toString(){return getName();}
    
}
