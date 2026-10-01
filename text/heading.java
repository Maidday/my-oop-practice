package text;

import java.util.UUID;

public class heading implements Text{
    private String content;
    private int level;
    private UUID id = UUID.randomUUID();

    public heading(String content, int level){
        this.content = content;
        this.level = level;
    }

    public void setLevel(int level){
        this.level = level;
    }
    public int getLevel(){
        return level;
    }

    public String getText(){
        return content;
    }

    public UUID getId(){return id;}

    @Override 
    public String render(){
        return "-".repeat(level) + " " + content;
    }

    @Override 
    public String toString(){
        return render();
    }

    @Override 
    public String indentation() {
        
        return "    ".repeat(level - 1) + content + System.lineSeparator();
    } 
    
}
