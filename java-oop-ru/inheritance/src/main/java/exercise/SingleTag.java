package exercise;

import java.awt.*;
import java.util.Map;

// BEGIN
public class SingleTag extends Tag {

    public SingleTag(String nameOfTag, Map<String, String> attributesOfTag) {

        super(nameOfTag, attributesOfTag);
    }

    @Override
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
        return result;
    }
}
// END
