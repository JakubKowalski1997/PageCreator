package HTMLHandlerClasses;
/**
 * Created by Konrad on 2017-06-28.
 */

import Utils.ParserUtils.Parser;
import javafx.util.Pair;

/**
 * Structure representing attribute of html tag
 */
public class TagAttribute {
    public String name;
    public String value;

    private static final Character nameValueSeparator = '=';
    private static final Character valueSeparator = '\"';

    public TagAttribute(String nme, String val) {
        this.name = nme;
        this.value = val;
    }

    public String toString() {
        StringBuilder stringRep = new StringBuilder();
        stringRep.append(name);
        stringRep.append("=");
        stringRep.append("\"");
        stringRep.append(value);
        stringRep.append("\"");

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

        TagAttribute oAttribute = (TagAttribute) o;

        return name.equals(oAttribute.name) && value.equals(oAttribute.value);
    }

    public static Pair<TagAttribute, Integer> parseFromString(String textRep, int initPos) throws Exception {
        int i = initPos;

        String name, value;

        StringBuilder nameBuilder = new StringBuilder();
        while (textRep.charAt(i) != nameValueSeparator) {
            if (Character.isWhitespace(textRep.charAt(i))) {
                ++i;
                continue;
            }

            nameBuilder.append(textRep.charAt(i));
            ++i;
        }

        ++i;

        name = nameBuilder.toString();

        i = Parser.omitWhitespaces(textRep, i);

        if (textRep.charAt(i) != valueSeparator)
            throw new Exception("Error. Expected \" before value");

        ++i;

        StringBuilder valueBuilder = new StringBuilder();
        while (textRep.charAt(i) != valueSeparator) {
            valueBuilder.append(textRep.charAt(i));
            ++i;
        }
        value = valueBuilder.toString();


        return new Pair<TagAttribute, Integer>(new TagAttribute(name, value), i);
    }
}
