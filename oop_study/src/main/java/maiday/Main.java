package maiday;
import maiday.chara.*;
import maiday.items.*;
import maiday.fight.fight;
import maiday.records.parserResult;
import maiday.infra.Data.writer;

import java.io.IOException;
import java.util.*;
import maiday.game.*;
import maiday.commands.*;


public class Main{
    public static void main(String[] args) throws IOException{
        Scanner scanner = new Scanner(System.in);
        Logger logger = new Logger("./logs/logfile.txt");
        logger.log("Started logging");

        if(!(args.length > 0)) {
            args = new String[1];
            args[0] = "";
        }

        parserResult parseArgs = parser.parse(args);
        ArrayList<String> arguments = parseArgs.arguments();
        HashMap<String, String> options = parseArgs.options();

        

        String InterfaceMode = intefaceMode.getInterfaceMode(arguments, logger, scanner);

        while(true){
            try{
            
            
                commandListener.getInstance().listener(arguments, options, scanner, logger, InterfaceMode);
            
            


            }
            catch(Exception e){
                logger.log("Caught an exception: " + e.toString());
                e.printStackTrace();
            }
            catch(Error e){
                logger.log("Caught an error: " + e.toString());
                e.printStackTrace();
            }
            
    }
        
            
}

public static void exit(Logger logger) throws IOException{
    logger.log("reached end of execution.");
    System.exit(0);
}

}