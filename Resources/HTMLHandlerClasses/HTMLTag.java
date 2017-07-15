package HTMLHandlerClasses;
/**
 * Created by Konrad on 2017-06-28.
 */

import java.util.List;
import java.util.ArrayList;
import javafx.util.Pair;

public abstract class HTMLTag {
    private ArrayList<TagAttribute> atts;
    private ContainerTag parent = null;

    protected static final char leftTagParenthesis = '<';
    protected static final char rightTagParenthesis = '>';
    protected static final char tagClosingChar = '/';


    public HTMLTag(List<TagAttribute> attributes) {
        this.atts = (ArrayList<TagAttribute>) attributes;
    }

    protected String attributeListToString() {
        StringBuilder list = new StringBuilder();
        for (TagAttribute attribute : atts) {
            list.append(" ");
            list.append(attribute.toString());
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

    public static Pair<List<TagAttribute>, Integer> parseAttributesFromString(String textRep, int initPos) throws Exception {
        final Character terminator1 = '/';
        final Character terminator2 = '>';

        ArrayList<TagAttribute> tagAttributes = new ArrayList<>();

        int i = initPos;
        while (textRep.charAt(i) != terminator1 &&
                textRep.charAt(i) != terminator2) {
            if (Character.isWhitespace(textRep.charAt(i))) {
                ++i;
                continue;
            }

            Pair<TagAttribute, Integer> parsed = TagAttribute.parseFromString(textRep, i);
            tagAttributes.add(parsed.getKey());
            i = parsed.getValue();
            ++i;
        }

        return new Pair<List<TagAttribute>, Integer>(tagAttributes, i);
    }

    public List<TagAttribute> getAttributes() {
        return atts;
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
