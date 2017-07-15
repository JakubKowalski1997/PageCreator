package Utils.ParserUtils;

/**
 * Created by Konrad on 2017-07-14.
 */
public class ApplyParsing {
    public static Object apply(Parser parser, String textRep, int pos) throws Exception {
        return parser.parse(textRep, pos);
    }

    public static Object apply(Parser parser, String textRep) throws Exception {
        return apply(parser, textRep, 0);
    }
}
