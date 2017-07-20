package HTMLHandlerClasses;

import Utils.ParserUtils.Parser;

/**
 * Created by Konrad on 2017-07-14.
 */

public class SelfClosingTagParser implements Parser {
    public Object parse(String textRep, int pos) throws Exception {
        return SelfClosingTag.parseFromString(textRep, pos);
    }
}
