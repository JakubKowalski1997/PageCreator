package CSSHandlerClasses;

/**
 * Created by Konrad on 2017-07-12.
 */

import java.util.List;
import java.util.ArrayList;
import javafx.util.Pair;

public class CSSElement {
    CSSSelector selector;
    List<CSSAttribute> atts;

    private static final Character leftAttSep = '{';
    private static final Character rightAttSep = '}';

    public CSSElement(CSSSelector selector) {
        this.selector = selector;
        atts = new ArrayList<>();
    }

    public CSSElement(CSSSelector selector, List<CSSAttribute> attributes) {
        this.selector = selector;
        this.atts = attributes;
    }

    public void addAttribute(CSSAttribute attribute) {
        atts.add(attribute);
    }

    public void eraseAttribute(int pos) {
        atts.remove(pos);
    }

    public void eraseAttribute(CSSAttribute attribute) {
        atts.remove(attribute);
    }

    public void popAttribute() {
        atts.remove(atts.size() - 1);
    }

    public CSSAttribute getAttribute(int pos) {
        return atts.get(pos);
    }

    public CSSAttribute getAttribute(String attributeName) {
        for (CSSAttribute attribute : atts) {
            if (attribute.name.equals(attributeName))
                return attribute;
        }

        return null;
    }

    @Override
    public String toString() {
        StringBuilder stringRep = new StringBuilder();

        stringRep.append(selector.toString());
        stringRep.append(" ");
        stringRep.append(leftAttSep);

        stringRep.append('\n');

        for (CSSAttribute attribute : atts) {
            stringRep.append('\t');
            stringRep.append(attribute.toString());
            stringRep.append('\n');
        }

        stringRep.append(rightAttSep);

        return stringRep.toString();
    }

    @Override
    public boolean equals(Object o ) {
        // self check
        if (this == o)
            return true;
        // null check
        if (o == null)
            return false;
        // type check and cast
        if (getClass() != o.getClass())
            return false;

        CSSElement oElement = (CSSElement) o;

        if (!selector.equals(oElement.selector)) {
            return false;
        }

        if (atts.size() != oElement.atts.size()) {
            return false;
        }

        for (int i = 0; i < atts.size(); ++i) {
            if (!atts.get(i).equals(oElement.atts.get(i))) {
                return false;
            }
        }

        return true;
    }

    public static Pair<CSSElement, Integer> parseFromString(String textRep, int initPos) throws Exception {
        if (textRep.length() < 3)
            throw new Exception("To short input string");

        Pair<CSSSelector, Integer> parsedSelector = CSSSelector.parseFromString(textRep, initPos);

        int i = parsedSelector.getValue();
        ++i;

        ArrayList<CSSAttribute> attributes = new ArrayList<>();

        while (textRep.charAt(i) != rightAttSep) {
            if (Character.isWhitespace(textRep.charAt(i))) {
                ++i;
                continue;
            }
            else {
                Pair<CSSAttribute, Integer> parsedAttribute = CSSAttribute.parseFromString(textRep, i);
                attributes.add(parsedAttribute.getKey());
                i = parsedAttribute.getValue();
                ++i;
            }
        }

        return new Pair<CSSElement, Integer>(new CSSElement(parsedSelector.getKey(), attributes), i);
    }
}
