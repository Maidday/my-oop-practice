package maiday.commands;

import maiday.game.Logger;
import maiday.text.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public abstract class addTextCommand {
    public static void add(ArrayList<String> commandArguments, HashMap<String, String> commandOptions, Scanner scanner, Logger logger) throws IOException{
        String kind = commandArguments.size() > 1 ? commandArguments.get(1).toLowerCase() : "";
        String type = commandArguments.size() > 2 ? commandArguments.get(2).toLowerCase() : "";
        if(kind.isEmpty()){
            logger.log("container or leaf?");
            kind = scanner.nextLine().toLowerCase();
        }
        if(type.isEmpty()){
            logger.log("What type?");
            type = scanner.nextLine().toLowerCase();
        }
        if(kind.equals("container")){
            if(!type.equals("heading") && !type.equals("alphatext") && !type.equals("container")){
                throw new IllegalArgumentException("Container type must be heading or alphaText.");
            }
            logger.log("Enter heading for the new container.");
            textDocument.getInstance().addContainer(scanner.nextLine());
            return;
        }
        if(!kind.equals("leaf")) throw new IllegalArgumentException("Use add <container|leaf> <type>.");

        switch(type){
            case "heading":
            case "h":
                logger.log("Enter heading text.");
                textDocument.getInstance().addLeaf(new heading(scanner.nextLine(), textDocument.getInstance().getCurrent().getLevel() + 1));
                break;
            case "paragraph":
            case "p":
                logger.log("Enter paragraph text.");
                textDocument.getInstance().addLeaf(new paragraph(scanner.nextLine()));
                break;
            case "image":
            case "img":
                logger.log("Enter image source.");
                String src = scanner.nextLine();
                logger.log("Enter image alt text.");
                textDocument.getInstance().addLeaf(new image(src, scanner.nextLine()));
                break;
            case "link":
            case "url":
                logger.log("Enter link title.");
                String title = scanner.nextLine();
                logger.log("Enter link URL.");
                textDocument.getInstance().addLeaf(new link(title, scanner.nextLine()));
                break;
            default:
                throw new IllegalArgumentException("Unknown text leaf type: " + type);
        }
    }
}