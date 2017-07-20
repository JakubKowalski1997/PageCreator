package HTMLHandlerClasses;

/**
 * Created by Konrad on 2017-07-14.
 */

public class SelfClosingTagParser {
    public Object parse(String textRep, int pos) throws Exception {
        return SelfClosingTag.parseFromString(textRep, pos);
    }
}
