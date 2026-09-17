package game;

import java.io.IOException;
import java.nio.file.*;

public class Logger {
    private Path _path;
    private boolean writeToConsole = true;

    public Logger(){
    }

    public Logger(Path path){  //for file logging
        this._path = path;
    }

    public Logger(String path){  //for file logging, when you are too lazy to make path object
        this._path = Paths.get(path);
    }

    public boolean getWritingToConsole(){
        return writeToConsole;
    }

    public void setWriteToConsole(boolean toggle){
        writeToConsole = toggle;
    }

    private String callback(String _message){
        return _message;
    }

    public String log(String _message) throws IOException {


        if(writeToConsole){
            System.out.println(_message);
        }

         if(_path != null){
            writeToFile(_message + "\n");
         }

        return callback(_message);
    }

    private void writeToFile(String _message) throws IOException{
       

            Files.writeString(_path, _message, 
                    StandardOpenOption.CREATE, 
                    StandardOpenOption.APPEND);
        
    }
    
}
