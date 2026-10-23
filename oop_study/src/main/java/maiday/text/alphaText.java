package maiday.text;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class alphaText implements Text{

    private ArrayList<Text> children = new ArrayList<>();
    private int level = 0;
    private String name = "";
    private UUID id = UUID.randomUUID();


    public void addNew(){

    }

    public void add(Text element) {
        element.setLevel(level);
        children.add(element);
    }

    public void add(Text element, int index) {
        element.setLevel(level);
        children.add(index, element);
    }

    public void remove(Text element) {
        children.remove(element);
    }

    public void removeAt(int index) {
        children.remove(index);
    }

    public void move(int fromIndex, int toIndex) {
        Text e = children.remove(fromIndex);
        children.add(toIndex, e);
    }

    public List<Text> getChildren() {
        return Collections.unmodifiableList(children);
    }

    public void setName(String name){this.name = name;}
    public String getName(){return name;}
    public UUID getId(){return id;}

    public void UP(){
        this.level++;
    }

    public void DOWN(){
        if(this.level == 0) {
            System.out.println("Already at root");
            return;
        }
        else this.level --;
    }

    public void setLevel(int level){
        this.level = level;
    }
    public int getLevel(){
        return level;
    }

    public int level() {return level;}

    //render

    @Override
    public String render() {
        StringBuilder builder = new StringBuilder();
        for (Text e : children) {
            builder.append(e.indentation()).append(e.render()).append(System.lineSeparator());
        }
        return builder.toString();
    }

    @Override
    public String toString() {
        return render();
    }

    @Override
    public String indentation() {
        StringBuilder builder = new StringBuilder();
        for (Text e : children) {
            builder.append(e.indentation());
        }
        return builder.toString();
    }

    public String getHeading() {
        return indentation();
    }
    
}
