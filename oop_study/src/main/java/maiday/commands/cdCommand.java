package maiday.commands;

import maiday.game.Logger;
import maiday.text.textDocument;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public abstract class cdCommand {
    public static void cd(ArrayList<String> commandArguments, HashMap<String, String> commandOptions, Logger logger) throws IOException{
        textDocument document = textDocument.getInstance();
        boolean changed = false;
        if(commandOptions.containsKey("--id") || commandOptions.containsKey("-i")){
            String id = commandOptions.getOrDefault("--id", commandOptions.get("-i"));
            changed = document.cd(UUID.fromString(id));
        }
        else if(commandArguments.size() > 1){
            changed = document.cd(String.join(" ", commandArguments.subList(1, commandArguments.size())));
        }
        if(!changed) logger.log("Container not found.");
    }
}