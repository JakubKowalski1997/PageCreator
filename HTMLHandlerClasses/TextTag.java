package HTMLHandlerClasses;
/**
 * Created by Konrad on 2017-06-28.
 */

import java.util.ArrayList;
import java.util.List;

public class TextTag extends HTMLTag {
    private HTMLTextTags name;
    private String text;

    public TextTag(HTMLTextTags kind, String text) {
        super(new ArrayList<TagAttribute>());
        this.name = kind;
        this.text = text;
    }

    public TextTag(HTMLTextTags kind, List<TagAttribute> attributes, String text) {
        super(attributes);
        this.name = kind;
        this.text = text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return this.text;
    }

    /**
     *
     * @return String representation of a tag
     */
    @Override
    public String toString() {
        StringBuilder stringRep = new StringBuilder();

        //print left marker
        stringRep.append(leftTagParenthesis);
        stringRep.append(name.toString());
        stringRep.append(super.attributeListToString());
        stringRep.append(super.rightTagParenthesis);

        //print content
        stringRep.append(text);

        //print right marker
        stringRep.append(super.leftTagParenthesis);
        stringRep.append(super.tagClosingChar);
        stringRep.append(name.toString());
        stringRep.append(rightTagParenthesis);
        return stringRep.toString();
    }
}
