package HTMLHandlerClasses;
/**
 * Created by Konrad on 2017-06-28.
 */

import java.util.ArrayList;
import java.util.List;

public class ContainerTag extends HTMLTag {
    private HTMLContainerTags name;
    private List<HTMLTag> nested;

    public ContainerTag(HTMLContainerTags kind) {
        super(new ArrayList<TagAttribute>());
        this.name = kind;
        nested = new ArrayList<HTMLTag>();
    }

    public ContainerTag(HTMLContainerTags kind, List<TagAttribute> attributes) {
        super(attributes);
        this.name = kind;
        nested = new ArrayList<HTMLTag>();
    }

    public ContainerTag(HTMLContainerTags kind, List<TagAttribute> attributes, List<HTMLTag> nestedTags) {
        super(attributes);
        this.name = kind;
        this.nested = nestedTags;
    }

    void addNestedTag(HTMLTag newTag) {
        nested.add(newTag);
        newTag.setParent(this);
    }

    void addNestedTag(int pos, HTMLTag newTag) {
        nested.add(pos, newTag);
        newTag.setParent(this);
    }

    void eraseNestedTag(HTMLTag tagToErase) {
        nested.remove(tagToErase);
    }

    void eraseNestedTag(int pos) {
        nested.remove(pos);
    }

    void popNestedTag() {
        nested.remove(nested.size() - 1);
    }

    public int getNumberOfChilds() {
        return nested.size();
    }

    HTMLTag getNestedTag(int pos) {
        return nested.get(pos);
    }

    public HTMLContainerTags getKind() {
        return name;
    }

    /**
     *
     * @param str
     * @return str with added \t at the beginning of every line
     */
    private String tabulateString(String str) {
        StringBuilder newStr = new StringBuilder();

        newStr.append('\t');
        for (int i = 0; i < str.length(); ++i) {
            newStr.append(str.charAt(i));
            if (str.charAt(i) == '\n') {
                newStr.append('\t');
            }
        }
        return newStr.toString();
    }

    /**
     *
     * @return String representation of a tag
     */
    @Override
    public String toString() {
        StringBuilder stringRep = new StringBuilder();

        //print left marker
        stringRep.append(super.leftTagParenthesis);
        stringRep.append(name.toString());
        stringRep.append(super.attributeListToString());
        stringRep.append(super.rightTagParenthesis);

        //print content
        for (HTMLTag tag : nested) {
            stringRep.append('\n');
            String nestedTagRepr = tag.toString();
            stringRep.append(tabulateString(nestedTagRepr));
        }

        if (nested.size() != 0)
            stringRep.append('\n');

        //print right marker
        stringRep.append(super.leftTagParenthesis);
        stringRep.append(super.tagClosingChar);
        stringRep.append(name.toString());
        stringRep.append(super.rightTagParenthesis);

        return stringRep.toString();
    }

}
