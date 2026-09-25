package text;

public class image implements Text{
    private String src;
    private String alt;
    private int level;

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
