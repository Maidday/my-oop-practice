package items;

import chara.*;

public abstract class item {
    private String name;
    protected String desc;


    public item(String name){
        this.name = name;
    }

    public item(String name, String desc){
        this.name = name;
        this.desc = desc;
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
    
    public void onUse(actor target, int mod){
        
    }

    
}
