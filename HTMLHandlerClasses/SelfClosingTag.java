/**
 * Created by Konrad on 2017-06-28.
 */

import java.util.List;

public class SelfClosingTag extends HTMLTag {
    private HTMLSelfClosingTags name;

    public SelfClosingTag(HTMLSelfClosingTags kind, List<TagAttribute> attributes) {
        super(attributes);
        this.name = kind;
    }

    /**
     *
     * @return String representation of a tag
     */
    @Override
    public String toString() {
        StringBuilder stringRep = new StringBuilder();
        stringRep.append(super.leftTagParenthesis);
        try {
        stringRep.append(name.toString(name));
        }
        catch (Exception e) {
            System.out.println(e.getCause());
        }
        stringRep.append(super.attributeListToString());
        stringRep.append(super.tagClosingChar);
        stringRep.append(super.rightTagParenthesis);

        return stringRep.toString();
    }
}
