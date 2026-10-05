package maiday.text;

import java.util.UUID;

public class paragraph implements Text{
    private String content;
    private int level;
    private UUID id = UUID.randomUUID();

    public paragraph(String content) {this.content = content;} //constructor

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
