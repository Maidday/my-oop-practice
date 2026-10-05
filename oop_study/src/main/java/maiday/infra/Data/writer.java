package maiday.infra.Data;

import maiday.chara.actor;
import maiday.items.item;

import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.*;
import maiday.game.Logger;

public class writer {
    

    private final ObjectMapper objectMapper;
    private static final Path actorDIR = Path.of("data/actors");
    private static final Path itemDIR = Path.of("data/items");
    private writer(){
        this.objectMapper = new ObjectMapper();
        
    }

    private static final writer INSTANCE = new writer();

    public static writer get_instance(){return  INSTANCE;}

    public void write(actor actor, Logger logger) throws IOException{
        Files.createDirectories(actorDIR);
        Path file = actorDIR.resolve(actor.getName()+".json");
        objectMapper.writeValue(file.toFile(), actor);
        logger.log("Writing to: " + file.toAbsolutePath());

    }

    public void write(item item, Logger logger) throws IOException{
        Files.createDirectories(itemDIR);
        Path file = itemDIR.resolve(item.getName()+".json");
        objectMapper.writeValue(file.toFile(), item);
        logger.log("Writing to: " + file.toAbsolutePath());

    }
    
}
