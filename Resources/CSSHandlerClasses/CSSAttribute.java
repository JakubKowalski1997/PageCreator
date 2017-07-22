package CSSHandlerClasses;

/**
 * Created by Konrad on 2017-07-12.
 */

import javafx.util.Pair;
import java.util.List;
import java.util.ArrayList;

public class CSSAttribute {
    public String name;
    public List<String> values;

    private static final Character nameValSep = ':';
    private static final Character terminator = ';';
    private static final Character valuesSep = ',';

    public CSSAttribute(String nme, String val) {
        name = nme;
        values = new ArrayList<>();
        values.add(val);
    }

    public CSSAttribute(String nme, List<String> vals) {
        name = nme;
        values = vals;
    }

    public String toString() {
        StringBuilder strRep = new StringBuilder();
        strRep.append(name);
        strRep.append(' ');
        strRep.append(nameValSep);
        strRep.append(' ');

        for (int i = 0; i < values.size(); ++i) {
            strRep.append(values.get(i));
            if (i != values.size() - 1)
                strRep.append(", ");
        }

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

        ArrayList<String> values = new ArrayList<>();

        //get value from String
        StringBuilder valueBuilder = new StringBuilder();
        while (textRep.charAt(i) != terminator) {
            if (textRep.charAt(i) == valuesSep) {
                //flush buffer
                values.add(valueBuilder.toString());
                valueBuilder.setLength(0);
            }
            else if (!Character.isWhitespace(textRep.charAt(i))) {
                valueBuilder.append(textRep.charAt(i));
            }

            ++i;
        }

        values.add(valueBuilder.toString());

        return new Pair<CSSAttribute, Integer>(new CSSAttribute(name, values), i);
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

        if (!this.name.equals(otherAtt.name) || this.values.size() != otherAtt.values.size())
            return false;

        for (int i = 0; i < this.values.size(); ++i) {
            if (!this.values.get(i).equals(otherAtt.values.get(i)))
                return false;
        }

        return true;
    }
}
