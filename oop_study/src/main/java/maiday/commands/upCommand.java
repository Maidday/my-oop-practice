package maiday.commands;

import maiday.game.Logger;
import maiday.text.textDocument;

import java.io.IOException;

public abstract class upCommand {
    public static void up(Logger logger) throws IOException{
        if(!textDocument.getInstance().up()) logger.log("Already at root.");
    }
}