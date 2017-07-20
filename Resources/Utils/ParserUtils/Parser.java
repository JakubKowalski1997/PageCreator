package Utils.ParserUtils;

/**
 * Created by Konrad on 2017-07-14.
 */
public interface Parser {
    Object parse(String rep, int pos) throws Exception;

    static int omitWhitespaces(String textRep, int pos) {
        while (Character.isWhitespace(textRep.charAt(pos))) {
            ++pos;
        }

        return pos;
    }

    static int findFirstCharOccurence(String textRep, Character character, int pos) {
        while (textRep.charAt(pos) != character) {
            ++pos;
        }

        return pos;
    }
}
