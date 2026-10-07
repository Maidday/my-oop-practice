package maiday.items;

import java.io.IOException;

import maiday.chara.*;
import maiday.game.*;

public class equipment extends item{
        private String slot;   //where does it go
        
        public equipment(String name, String slot, int stat, String desc){
            super(name, stat, desc);
            this.slot = slot;
        }

        public equipment(String name, String slot){
            super(name);
            this.slot = slot;
        }

        public equipment(String name, String slot, String desc){
            super(name, desc);
            this.slot = slot;
            
        }

        

        public void equip(actor target){
            modStats(target, 1);
        }

         public void dequip(actor target){
            modStats(target, -1);
        }

        public String getSlot(){
            return slot;
        }

        @Override 
        public void onUse(actor target, int mod, Logger logger) throws IOException{
            target.equip(this, logger);
        }
        


}
