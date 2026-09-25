import java.util.*;

public class parser {

    public static parserResult parser(String[] args){
        var arguments = new ArrayList<String>();
        var options = new HashMap<String, String>();
        String option = "";

        for(String arg : args){
            if(arg.startsWith("--") || arg.startsWith("-")) option = arg;
            else if(!option.isEmpty()){
                options.put(option, arg);
                option = "";
            }
            else arguments.add(arg);
        }
        parserResult ret = new parserResult(arguments, options);

        return ret;

    }
    
}


