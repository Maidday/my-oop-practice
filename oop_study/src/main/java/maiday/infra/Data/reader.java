package maiday.infra.Data;

import java.nio.file.Path;
import maiday.chara.actor;
import maiday.chara.hero;
import maiday.items.item;

import tools.jackson.databind.ObjectMapper;

public class reader {
    private final ObjectMapper objectMapper;

    private reader(){
        objectMapper = new ObjectMapper();
    }

    private static final reader INSTANCE = new reader();

    public static reader get_instance(){return  INSTANCE;}

    public hero readHero(Path file){
        hero rActor = objectMapper.readValue(file.toFile(), hero.class);
        return rActor;
    }
    public item readItem(Path file){
        item rItem = objectMapper.readValue(file.toFile(), item.class);
        return rItem;
    }
}
