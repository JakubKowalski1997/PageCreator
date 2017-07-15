package CSSHandlerClasses;

/**
 * Created by Konrad on 2017-07-12.
 */

import javafx.util.Pair;

public class CSSAttribute {
    public String name;
    public String value;

    private static final Character nameValSep = ':';
    private static final Character terminator = ';';

    public CSSAttribute(String nme, String val) {
        name = nme;
        value = val;
    }

    public String toString() {
        StringBuilder strRep = new StringBuilder();
        strRep.append(name);
        strRep.append(' ');
        strRep.append(nameValSep);
        strRep.append(' ');
        strRep.append(value);
        strRep.append(terminator);
        return strRep.toString();
    }

    public static Pair<CSSAttribute, Integer> parseFromString(String textRep, int initPos) throws Exception {
        if (textRep.length() - initPos < 4)
            throw new Exception("Bad input string");

        String name, value;

        int i = initPos;

        //get name from String
        StringBuilder nameBuilder = new StringBuilder();
        while (textRep.charAt(i) != nameValSep) {
            if (!Character.isWhitespace(textRep.charAt(i))) {
                nameBuilder.append(textRep.charAt(i));
            }
            ++i;
        }

        ++i;

        name = nameBuilder.toString();

        //get value from String
        StringBuilder valueBuilder = new StringBuilder();
        while (textRep.charAt(i) != terminator) {
            if (!Character.isWhitespace(textRep.charAt(i))) {
                valueBuilder.append(textRep.charAt(i));
            }

            ++i;
        }
        value = valueBuilder.toString();


        return new Pair<CSSAttribute, Integer>(new CSSAttribute(name, value), i);
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

        CSSAttribute otherAtt = (CSSAttribute) o;

        return this.name.equals(otherAtt.name) && this.value.equals(otherAtt.value);
    }
}
