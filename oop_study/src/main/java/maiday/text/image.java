package maiday.text;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class image implements Text{
    private String src;
    private String alt;
    private int level;
    private UUID id = UUID.randomUUID();

    public image() {
    }

    @JsonCreator
    public image(@JsonProperty("src") String src,
                @JsonProperty("alt") String alt){
        this.src = src;
        this.alt = alt;
    }

    public void setLevel(int level){
        this.level = level;
    }
    public int getLevel(){
        return level;
    }

    public UUID getId(){return id;}
    public String getAlt(){return alt;}

    @Override 
    public String render(){
        if(src != null) return this.indentation() + src;
        else return this.indentation() + alt;
    }

    @Override 
    public String toString(){
        return render();
    }
    
}
