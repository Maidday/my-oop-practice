package text;


public interface Text{

    String render(); //render to plain text
    int getLevel();
    void setLevel(int level);

    default String indentation(){ //ind for "indentation"
            return "    ".repeat(getLevel());
    }

}

