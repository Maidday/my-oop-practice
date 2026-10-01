package commands;

import game.Logger;
import text.textDocument;

import java.io.IOException;

public abstract class printCommand {
    public static void print(boolean whole, boolean withIds, Logger logger) throws IOException{
        textDocument document = textDocument.getInstance();
        logger.log(whole ? document.print(document.getRoot(), withIds) : document.printCurrent(withIds));
    }
}