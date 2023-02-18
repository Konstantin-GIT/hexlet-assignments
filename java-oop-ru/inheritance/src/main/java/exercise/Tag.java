package exercise;

import java.util.Map;

// BEGIN
public class Tag {

    private String nameOfTag;
    private  Map<String, String> attributesOfTag;

    public Tag(String nameOfTag, Map<String, String> attributesOfTag) {
        this.setNameOfTag(nameOfTag);
        this.setAttributesOfTag(attributesOfTag);
    }


    public String getNameOfTag() {
        return nameOfTag;
    }

    public void setNameOfTag(String nameOfTag) {
        this.nameOfTag = nameOfTag;
    }

    public Map<String, String> getAttributesOfTag() {
        return attributesOfTag;
    }

    public void setAttributesOfTag(Map<String, String> attributesOfTag) {
        this.attributesOfTag = attributesOfTag;
    }
}
// END
