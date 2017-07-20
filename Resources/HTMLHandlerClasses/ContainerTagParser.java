package HTMLHandlerClasses;

/**
 * Created by Konrad on 2017-07-14.
 */

import Utils.ParserUtils.Parser;
import javafx.util.Pair;

public class ContainerTagParser implements Parser {
    public Object parse(String textRep, int pos) throws Exception {
        return ContainerTag.parseFromString(textRep, pos);
    }

    public static Pair<String, Integer> getTagName(String textRep, int pos) {
        StringBuilder tagNameBuilder = new StringBuilder();
        while (!Character.isWhitespace(textRep.charAt(pos)) && textRep.charAt(pos) != '>') {
            tagNameBuilder.append(textRep.charAt(pos));
            ++pos;
        }

        return new Pair<String, Integer>(tagNameBuilder.toString(), pos);
    }

    public static Pair<Boolean, Integer> isClosingCorrect(String textRep, String tagName, int pos) {
        StringBuilder closingBuilder = new StringBuilder();
        while (textRep.charAt(pos) != '>') {
            closingBuilder.append(textRep.charAt(pos));
            ++pos;
        }

        return new Pair<Boolean, Integer>(closingBuilder.toString().equals("</" + tagName), pos);
    }
}
