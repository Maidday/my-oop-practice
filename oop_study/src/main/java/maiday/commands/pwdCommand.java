package maiday.commands;

import maiday.game.Logger;
import maiday.text.textDocument;

import java.io.IOException;

public abstract class pwdCommand {
    public static void pwd(Logger logger) throws IOException{
        logger.log(textDocument.getInstance().pwd());
    }
}