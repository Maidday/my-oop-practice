package maiday.chara;

import com.fasterxml.jackson.annotation.JsonCreator;

public class zhegan extends actor{

    @JsonCreator
    public zhegan(){
        super("Zhegan", 8, 0, 2, false);
        this.desc = "Blind underground carnivores. They resemble black furry frogs with glowing fangs. Usually, they hunt in pairs.";
    }

}
