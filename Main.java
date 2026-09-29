import chara.*;
import items.*;
import records.parserResult;
import fight.fight;

import java.io.IOException;
import java.util.*;
import game.*;
import commands.*;


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

        

        

        try{
            String InterfaceMode = intefaceMode.getInterfaceMode(arguments, logger, scanner);
            while(true){
                


            }
            


        }
        catch(Exception e){
            logger.log("Caught an exception: " + e.toString());
            e.printStackTrace();
        }
        catch(Error e){
            logger.log("Caught an error: " + e.toString());
            e.printStackTrace();
        }

        finally{
            logger.log("Reached end of execution");
            
        }

        }
}