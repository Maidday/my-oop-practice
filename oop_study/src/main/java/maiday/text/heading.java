package maiday.text;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

// Leaf component in the Text Composite tree.
public class heading implements Text{
    private String content;
    private int level;
    private UUID id = UUID.randomUUID();

    public heading() {
    }

    @JsonCreator
    public heading(@JsonProperty("content") String content,
                   @JsonProperty("level") int level){
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
