package maiday.chara;

import com.fasterxml.jackson.annotation.JsonCreator;

public class bob extends actor{

    @JsonCreator
    public bob(){
        super("Bob", 99, 99, 0, false);
    }
}
