package CSSHandlerClasses;

/**
 * Created by Konrad on 2017-07-12.
 */

import HTMLHandlerClasses.HTMLContainerTags;
import HTMLHandlerClasses.HTMLSelfClosingTags;
import HTMLHandlerClasses.HTMLTextTags;
import javafx.util.Pair;


import Utils.EnumUtils;

public class CSSSelector {
    CSSSelectorTypes selectorType;
    String name;

    private static final Character classPrefix = '.';
    private static final Character idPrefix = '#';

    public CSSSelector(CSSSelectorTypes type, String name) throws Exception {
        if (type.equals(CSSSelectorTypes.TAG)) {
            if (!isHTMLTag(name)) {
                throw new Exception("Given selector name is not a tag");
            }
        }

        this.selectorType = type;
        this.name = name;
    }

    public String toString() {
        StringBuilder stringRep = new StringBuilder();

        switch (selectorType) {
            case CLASS:
                stringRep.append(classPrefix);
                break;
            case ID:
                stringRep.append(idPrefix);
                break;
            default:
                break;
        }

        stringRep.append(name);
        return stringRep.toString();
    }

    public boolean equals(Object o) {
        // self check
        if (this == o)
            return true;
        // null check
        if (o == null)
            return false;
        // type check and cast
        if (getClass() != o.getClass())
            return false;

        CSSSelector oSelector = (CSSSelector) o;

        return selectorType.equals(oSelector.selectorType) && name.equals(oSelector.name);
    }

    public static Pair<CSSSelector, Integer> parseFromString(String textRep, int initPos) throws Exception {
        if(textRep.length() - initPos < 1)
            throw new Exception("Bad input string");

        CSSSelectorTypes type;


        int i = initPos;

        if (textRep.charAt(initPos) == classPrefix) {
            type = CSSSelectorTypes.CLASS;
            ++i;
        }
        else if (textRep.charAt(initPos) == idPrefix) {
            type = CSSSelectorTypes.ID;
            ++i;
        }
        else {
            type = CSSSelectorTypes.TAG;
        }


        StringBuilder builder = new StringBuilder();
        while (textRep.charAt(i) != '{' && textRep.charAt(i) != ',') {
            if (!Character.isWhitespace(textRep.charAt(i))) {
                builder.append(textRep.charAt(i));
            }

            ++i;
        }

        return new Pair<CSSSelector, Integer>(new CSSSelector(type, builder.toString()), i);
    }

    private boolean isHTMLTag(String name) {
        if (EnumUtils.isStringEnumVal(HTMLContainerTags.BODY, name)) {
            return true;
        }
        else if (EnumUtils.isStringEnumVal(HTMLTextTags.EM, name)) {
            return true;
        }
        else if (EnumUtils.isStringEnumVal(HTMLSelfClosingTags.IMG, name)) {
            return true;
        }

        return false;
    }

}
