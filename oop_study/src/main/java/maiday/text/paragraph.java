package maiday.text;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

// Leaf component in the Text Composite tree.
public class paragraph implements Text{
    private String content;
    private int level;
    private UUID id = UUID.randomUUID();

    public paragraph() {
    }

    @JsonCreator
    public paragraph(@JsonProperty("content") String content) {this.content = content;} //constructor

    public String getText(){return content;}
    public void setText(String content){this.content = content;}
    public UUID getId(){return id;}


    public void setLevel(int level){
        this.level = level;
    }
    public int getLevel(){
        return level;
    }

    @Override
    public String render() {
        
        return (this.content + this.indentation());
    }

    @Override 
    public String toString(){
        return render();
    }
}
