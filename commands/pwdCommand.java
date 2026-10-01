package commands;

import game.Logger;
import text.textDocument;

import java.io.IOException;

public abstract class pwdCommand {
    public static void pwd(Logger logger) throws IOException{
        logger.log(textDocument.getInstance().pwd());
    }
}