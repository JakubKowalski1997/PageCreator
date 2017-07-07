package HTMLHandlerClasses;
/**
 * Created by Konrad on 2017-06-28.
 */

import java.util.ArrayList;
import java.util.List;

public class SelfClosingTag extends HTMLTag {
    private HTMLSelfClosingTags name;

    public SelfClosingTag(HTMLSelfClosingTags kind) {
        super(new ArrayList<TagAttribute>());
        this.name = kind;
    }

    public SelfClosingTag(HTMLSelfClosingTags kind, List<TagAttribute> attributes) {
        super(attributes);
        this.name = kind;
    }

    private boolean hasClosing() {
        switch (this.name) {
            case META:
            case CHARSET:
            case LINK:
                return true;
            default:
                return false;
        }
    }

    /**
     *
     * @return String representation of a tag
     */
    @Override
    public String toString() {
        StringBuilder stringRep = new StringBuilder();
        stringRep.append(super.leftTagParenthesis);
        stringRep.append(name.toString());
        stringRep.append(super.attributeListToString());
        if (hasClosing()) {
            stringRep.append(super.tagClosingChar);
        }
        stringRep.append(super.rightTagParenthesis);

        return stringRep.toString();
    }

    public HTMLSelfClosingTags getKind() {
        return name;
    }

}
