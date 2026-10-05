package maiday.commands;

import maiday.game.Logger;
import maiday.text.textDocument;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public abstract class rmCommand {
    public static void rm(ArrayList<String> commandArguments, Scanner scanner, Logger logger) throws IOException{
        textDocument document = textDocument.getInstance();
        String target = commandArguments.size() > 1 ? String.join(" ", commandArguments.subList(1, commandArguments.size())) : "";
        logger.log("Remove " + (target.isEmpty() ? document.pwd() : target) + "? (y/n)");
        String answer = scanner.nextLine();
        if(!answer.equalsIgnoreCase("y") && !answer.equalsIgnoreCase("yes")) return;
        boolean removed = target.isEmpty() ? document.removeCurrent() : document.remove(target);
        if(!removed) logger.log("Element not found or cannot remove document root.");
    }
}