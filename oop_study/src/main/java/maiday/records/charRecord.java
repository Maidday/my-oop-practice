package maiday.records;

import java.util.ArrayList;
import maiday.action.actions;

// Data Transfer Object for character data received from the web API.
public record charRecord(String name, String description, ArrayList<actions> skillTalents, int rarity) {
    

}
