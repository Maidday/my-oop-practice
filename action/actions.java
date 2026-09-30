package action;

import java.io.IOException;
import java.util.Scanner;
import java.util.UUID;

import chara.*;
import game.*;
import fight.*;

public class actions {

    protected actor _owner;
    protected String _name;
    protected String _description;
    private UUID _id;

    public actions(actor owner, String name, String desc){
        this._owner = owner;
        this._name = name;
        this._description = desc;
        this._id = UUID.randomUUID();
    }

    public actions(actions example){
        this._owner = example.getOwner();
        this._name = example.getName();
        this._description = example.getDesc();
        this._id = UUID.randomUUID();
    }


    protected void onUse(actor target, Logger logger, Scanner scanner)  throws IOException{

    }

    public void use(actor target, Logger logger, Scanner scanner)  throws IOException{
        onUse(target, logger, scanner);
    }

    public void setOwner(actor owner){this._owner = owner;}

    public actor getOwner(){return this._owner;}

    public String getDesc(){return this._description;}

    public String getName(){ return this._name;}

    public UUID getId(){return this._id;}

    @Override 
    public String toString(){return getName();}
    
}
