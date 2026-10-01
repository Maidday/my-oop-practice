package text;

import java.util.UUID;

public class image implements Text{
    private String src;
    private String alt;
    private int level;
    private UUID id = UUID.randomUUID();

    public image(String src){
        this.src = src;
        this.alt = "image is supposed to be there";
    }
    
    public image(String src, String alt){
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
