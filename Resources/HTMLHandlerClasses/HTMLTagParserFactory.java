package HTMLHandlerClasses;

/**
 * Created by Konrad on 2017-07-14.
 */

import Utils.ParserUtils.ParserFactory;
import Utils.EnumUtils;

public class HTMLTagParserFactory implements ParserFactory {

    private static HTMLTagParserFactory instance = new HTMLTagParserFactory();

    public static HTMLTagParserFactory getInstance() {
        return instance;
    }

    public Object create(String id) {
        if (EnumUtils.isStringEnumVal(HTMLContainerTags.BODY, id)) {
            return new ContainerTagParser();
        }
        else if (EnumUtils.isStringEnumVal(HTMLTextTags.P, id)) {
            return new TextTagParser();
        }
        else if (EnumUtils.isStringEnumVal(HTMLSelfClosingTags.IMG, id)) {
            return new SelfClosingTagParser();
        }

        return null;
    }
}
