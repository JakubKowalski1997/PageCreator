/**
 * Created by Konrad on 2017-06-28.
 */

import java.util.List;
import java.util.ArrayList;

public abstract class HTMLTag {
    private ArrayList<TagAttribute> atts;
    private String text;

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

    public abstract String toString();
}
