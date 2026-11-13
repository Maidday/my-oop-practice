package maiday.infra.Data;

import maiday.chara.actor;
import maiday.items.item;
import maiday.text.textDocument;

import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.*;
import maiday.game.Logger;

// Singleton pattern: shares the JSON writer used by the persistence layer.
public class writer {
    

    private final ObjectMapper objectMapper;
    private static final Path actorDIR = Path.of("data/actors");
    private static final Path itemDIR = Path.of("data/items");
    private static final Path docDIR = Path.of("data/docs");
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
    
    public void write(textDocument doc, Logger logger) throws IOException{
        Files.createDirectories(docDIR);
        Path file = docDIR.resolve("document.json");
        objectMapper.writeValue(file.toFile(), doc);
        logger.log("Writing to: " + file.toAbsolutePath());

    }
}
