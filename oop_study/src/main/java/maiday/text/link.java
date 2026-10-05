package maiday.text;

import java.util.UUID;

public class link implements Text{
    private String title;
    private String url;
    private int level;
    private UUID id = UUID.randomUUID();

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

    public UUID getId(){return id;}
    public String getTitle(){return title;}


    @Override
    public String render() {
        
        return this.indentation() + "(" + title + ")" + "{" + url + "}";
    }

    @Override 
    public String toString(){
        return render();
    }
    
}
