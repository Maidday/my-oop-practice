package maiday.text;

import java.util.ArrayList;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class textDocument {
    private alphaText root;
    private alphaText current;

    @JsonIgnore
    private IdentityHashMap<alphaText, alphaText> parents;

    public textDocument() {
        this.root = new alphaText();
        this.current = root;
        this.parents = new IdentityHashMap<>();
    }

    @JsonCreator
    public textDocument(@JsonProperty("root") alphaText root,
                       @JsonProperty("current") alphaText current) {
        this.root = root != null ? root : new alphaText();
        this.current = current != null ? current : this.root;
        this.parents = new IdentityHashMap<>();
    }

    private static textDocument INSTANCE = new textDocument();

    public static textDocument getInstance(){return INSTANCE;}
    public void setInstance(textDocument saved){
        INSTANCE = saved;
    }


    public alphaText getRoot(){return root;}
    public alphaText getCurrent(){return current;}

    public void addContainer(String name){
        alphaText container = new alphaText();
        container.setName(name);
        container.setLevel(current.getLevel() + 1);
        current.add(container);
        parents.put(container, current);
    }

    public void addLeaf(Text element){
        current.add(element);
    }

    public String pwd(){
        ArrayList<String> path = new ArrayList<>();
        alphaText cursor = current;
        while(cursor != root){
            path.add(0, cursor.getName());
            cursor = parents.get(cursor);
            if(cursor == null) return "/";
        }
        return "/" + String.join("/", path);
    }

    public boolean up(){
        if(current == root) return false;
        current = parents.get(current);
        return true;
    }

    public boolean cd(String path){
        if(path == null || path.isBlank()) return false;
        if(path.startsWith("/")) current = root;
        String[] parts = path.split("/");
        for(String part : parts){
            if(part.isBlank() || part.equals(".")) continue;
            if(part.equals("..")){
                if(!up()) return false;
                continue;
            }
            alphaText next = null;
            for(Text child : current.getChildren()){
                if(child instanceof alphaText && ((alphaText) child).getName().equals(part)){
                    next = (alphaText) child;
                    break;
                }
            }
            if(next == null) return false;
            current = next;
        }
        return true;
    }

    public boolean cd(UUID id){
        alphaText found = findContainer(root, id);
        if(found == null) return false;
        current = found;
        return true;
    }

    private alphaText findContainer(alphaText container, UUID id){
        if(container.getId().equals(id)) return container;
        for(Text child : container.getChildren()){
            if(child instanceof alphaText){
                alphaText found = findContainer((alphaText) child, id);
                if(found != null) return found;
            }
        }
        return null;
    }

    public boolean remove(String name){
        for(Text child : current.getChildren()){
            if(label(child).equalsIgnoreCase(name)){
                if(child instanceof alphaText) parents.remove((alphaText) child);
                current.remove(child);
                return true;
            }
        }
        return false;
    }

    public boolean removeCurrent(){
        if(current == root) return false;
        alphaText removed = current;
        current = parents.remove(removed);
        current.remove(removed);
        return true;
    }

    public String label(Text element){
        if(element instanceof alphaText) return ((alphaText) element).getName();
        if(element instanceof heading) return ((heading) element).getText();
        if(element instanceof paragraph) return ((paragraph) element).getText();
        if(element instanceof image) return ((image) element).getAlt();
        if(element instanceof link) return ((link) element).getTitle();
        return element.render().trim();
    }

    public String print(alphaText container, boolean withIds){
        StringBuilder output = new StringBuilder();
        for(Text child : container.getChildren()) printElement(child, output, withIds, 0);
        return output.toString();
    }

    public String printCurrent(boolean withIds){
        if(current == root) return print(root, withIds);
        StringBuilder output = new StringBuilder();
        printElement(current, output, withIds, 0);
        return output.toString();
    }

    private void printElement(Text element, StringBuilder output, boolean withIds, int depth){
        output.append("    ".repeat(depth));
        if(element instanceof alphaText){
            alphaText container = (alphaText) element;
            output.append(container.getName());
            if(withIds) output.append(" [").append(container.getId()).append("]");
            output.append(System.lineSeparator());
            for(Text child : container.getChildren()) printElement(child, output, withIds, depth + 1);
        }
        else{
            output.append(element.render().trim());
            if(withIds) output.append(" [").append(element.getId()).append("]");
            output.append(System.lineSeparator());
        }
    }

    public List<Text> getChildren(){return current.getChildren();}
}