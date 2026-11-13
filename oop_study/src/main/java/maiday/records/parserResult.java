package maiday.records;
import java.util.*;

// Data Transfer Object carrying parsed positional arguments and options.
public record parserResult(ArrayList<String> arguments, HashMap<String,String> options){
    
}