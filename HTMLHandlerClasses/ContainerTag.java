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
    }

    public ContainerTag(HTMLContainerTags kind, List<TagAttribute> attributes) {
        super(attributes);
        this.name = kind;
    }

    public ContainerTag(HTMLContainerTags kind, List<TagAttribute> attributes, List<HTMLTag> nestedTags) {
        super(attributes);
        this.name = kind;
        this.nested = nestedTags;
    }

    public void addNestedTag(HTMLTag newTag) {
        nested.add(newTag);
    }

    public void eraseNestedTag(HTMLTag tagToErase) {
        nested.remove(tagToErase);
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
        try {
            stringRep.append(name.toString(name));
        }
        catch (Exception e) {
            System.out.println(e.getCause());
        }
        stringRep.append(super.attributeListToString());
        stringRep.append(super.rightTagParenthesis);


        stringRep.append('\n');
        //print content
        for (HTMLTag tag : nested) {
            stringRep.append('\t' + tag.toString());
        }
        stringRep.append('\n');

        //print right marker
        stringRep.append(super.leftTagParenthesis);
        stringRep.append(super.tagClosingChar);
        try {
            stringRep.append(name.toString(name));
        }
        catch (Exception e) {
            System.out.println(e.getCause());
        }
        stringRep.append(super.rightTagParenthesis);

        return stringRep.toString();
    }
}
