package CSSHandlerClasses;

/**
 * Created by Konrad on 2017-07-14.
 */

import Utils.ParserUtils.Parser;

public class CSSParser implements Parser {
    public CSSDocument parse(String textRep, int pos) throws Exception {
        return CSSDocument.parseFromString(textRep);
    }
}
