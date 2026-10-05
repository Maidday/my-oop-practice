package maiday.commands;

import maiday.game.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public abstract class intefaceMode {
    public static String getInterfaceMode(ArrayList<String> arguments, Logger logger, Scanner scanner) throws IOException{
        if(!arguments.get(0).toLowerCase().equals("text") || !arguments.get(0).toLowerCase().equals("chars")){
                logger.log("Interface mode unspecified. Choose interface mode: text or chars?");
                String answer = scanner.nextLine();
                if(answer.toLowerCase().equals("text") || answer.toLowerCase().equals("chars")) {
                    arguments.add(0, answer);
                    return arguments.get(0);
                }
                else throw new Error("Incorrect interface mode.");
                
            }
            else return arguments.get(0);
    }
}
