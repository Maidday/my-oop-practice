package action;

import java.io.IOException;
import java.util.Scanner;

import chara.*;
import game.*;
import fight.*;

public abstract class actions {

    protected actor _owner;
    protected String _name;
    protected String _description;

    public actions(actor owner, String name, String desc){
        this._owner = owner;
        this._name = name;
        this._description = desc;
    }

    protected void onUse(actor target, fight combat, Logger logger, Scanner scanner)  throws IOException{

    }

    public void use(actor target, fight combat, Logger logger, Scanner scanner)  throws IOException{
        onUse(target, combat, logger, scanner);
    }

    public actor getOwner(){return this._owner;}

    public String getDesc(){return this._description;}

    public String getName(){ return this._name;}

    @Override 
    public String toString(){return getName();}
    
}
