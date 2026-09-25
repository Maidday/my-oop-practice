package text;

public class link implements Text{
    private String title;
    private String url;
    private int level;

    public link(String title, String url){
        this.title = title;
        this.url = url;
    }

    public void setLevel(int level){
        this.level = level;
    }
    public int getLevel(){
        return level;
    }


    @Override
    public String render() {
        
        return this.indentation() + "(" + title + ")" + "{" + url + "}";
    }

    @Override 
    public String toString(){
        return render();
    }
    
}
