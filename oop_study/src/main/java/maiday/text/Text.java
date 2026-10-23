package maiday.text;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
    @JsonSubTypes.Type(value = alphaText.class, name = "alphaText"),
    @JsonSubTypes.Type(value = heading.class, name = "heading"),
    @JsonSubTypes.Type(value = paragraph.class, name = "paragraph"),
    @JsonSubTypes.Type(value = image.class, name = "image"),
    @JsonSubTypes.Type(value = link.class, name = "link")
})
public interface Text{  //this interface is a prototype for every other class of maiday.text package

    String render(); //render to plain text
    int getLevel();
    void setLevel(int level);
    UUID getId();

    default String indentation(){ //ind for "indentation"
            return "    ".repeat(getLevel());
    }

}

