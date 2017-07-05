package HTMLHandlerClasses;
/**
 * Created by Konrad on 2017-06-28.
 */

import javax.swing.text.html.HTML;
import java.util.List;
import java.util.ArrayList;

public abstract class HTMLTag {
    private ArrayList<TagAttribute> atts;
    private ContainerTag parent = null;

    protected final char leftTagParenthesis = '<';
    protected final char rightTagParenthesis = '>';
    protected final char tagClosingChar = '/';

    public HTMLTag(List<TagAttribute> attributes) {
        this.atts = (ArrayList<TagAttribute>) attributes;
    }

    protected String attributeListToString() {
        StringBuilder list = new StringBuilder();
        for (TagAttribute attribute : atts) {
            list.append(" " + attribute.name + "=" + attribute.value);
        }

        return list.toString();
    }

    public boolean hasAttributes(List<TagAttribute> atts) {
        for (int i = 0; i < this.atts.size(); ++i) {
            if (!this.atts.get(i).equals(atts.get(i))) {
                return false;
            }
        }

        return true;
    }

    public void addAttribute(int pos, TagAttribute attribute) {
        atts.add(pos, attribute);
    }

    public void addAttribute(TagAttribute attribute) {
        atts.add(attribute);
    }

    public boolean isContainer() {
        return this.getClass().getName().equals("HTMLHandlerClasses.ContainerTag");
    }

    public ContainerTag getParent() {
        return parent;
    }

    public void setParent(ContainerTag parentTag) {
        parent = parentTag;
    }

    public abstract String toString();
}
