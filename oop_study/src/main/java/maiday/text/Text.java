package maiday.text;

import java.util.UUID;

public interface Text{

    String render(); //render to plain text
    int getLevel();
    void setLevel(int level);
    UUID getId();

    default String indentation(){ //ind for "indentation"
            return "    ".repeat(getLevel());
    }

}

