package exercise;

import java.util.Map;
import java.util.List;

// BEGIN
public class PairedTag extends Tag {

    private String bodyOfTag;
    private List<Tag> tagsInside;

    public PairedTag(String nameOfTag, Map<String, String> attributesOfTag, String bodyOfTag, List<Tag> tagsInside) {
        super(nameOfTag, attributesOfTag);
        this.setBodyOfTag(bodyOfTag);
        this.setTagsInside(tagsInside);
    }


    public String getBodyOfTag() {
        return bodyOfTag;
    }

    public void setBodyOfTag(String bodyOfTag) {
        this.bodyOfTag = bodyOfTag;
    }

    public List<Tag> getTagsInside() {
        return tagsInside;
    }

    public void setTagsInside(List<Tag> tagsInside) {
        this.tagsInside = tagsInside;
    }

    public String toString() {
        String result = "<" + this.getNameOfTag();
        Map<String, String> attributesOfTag = this.getAttributesOfTag();
        if (attributesOfTag != null) {
            for (Map.Entry<String, String> currentAttributeAntValue : attributesOfTag.entrySet()) {
                String nameOfAttribute = currentAttributeAntValue.getKey();
                String valueOfAttribute = currentAttributeAntValue.getValue();
                result += " " + nameOfAttribute + "=\"" + valueOfAttribute + "\"";
            }
        }
        result += ">";
        if (bodyOfTag != null) {
            result += bodyOfTag;
        }
        if (tagsInside != null) {
            for (Tag element : tagsInside) {
                result += element.toString();
            }
        }
        result += "</" + this.getNameOfTag() + ">";
        return result;
    }
}
// END
