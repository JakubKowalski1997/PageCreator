package HTMLHandlerClasses;
/**
 * Created by Konrad on 2017-06-28.
 */

import java.util.ArrayList;
import java.util.List;

import Utils.ParserUtils.Parser;
import javafx.util.Pair;

import javax.swing.text.html.HTML;

public class SelfClosingTag extends HTMLTag {
    private HTMLSelfClosingTags name;

    public SelfClosingTag(HTMLSelfClosingTags kind) {
        super(new ArrayList<TagAttribute>());
        this.name = kind;
    }

    public SelfClosingTag(HTMLSelfClosingTags kind, List<TagAttribute> attributes) {
        super(attributes);
        this.name = kind;
    }

    private boolean hasClosing() {
        switch (this.name) {
            case META:
            case CHARSET:
            case LINK:
                return false;
            default:
                return true;
        }
    }

    /**
     *
     * @return String representation of a tag
     */
    @Override
    public String toString() {
        StringBuilder stringRep = new StringBuilder();
        stringRep.append(super.leftTagParenthesis);
        stringRep.append(name.toString());
        stringRep.append(super.attributeListToString());
        if (hasClosing()) {
            stringRep.append(super.tagClosingChar);
        }
        stringRep.append(super.rightTagParenthesis);

        return stringRep.toString();
    }

    public static Pair<SelfClosingTag, Integer> parseFromString(String textRep, int initPos) throws Exception {
        if (textRep.charAt(initPos) != leftTagParenthesis)
            throw new Exception("Error. " + leftTagParenthesis + " expected");

        int i = ++initPos;

        //omit whitespaces
        i = Parser.omitWhitespaces(textRep, i);

        //get tag name
        Pair<String, Integer> tagNamePair = ContainerTagParser.getTagName(textRep, i);
        HTMLSelfClosingTags tagName = HTMLSelfClosingTags.valueOf(tagNamePair.getKey().toUpperCase());
        i = tagNamePair.getValue();

        Pair<List<TagAttribute>, Integer> parsedAttributes = HTMLTag.parseAttributesFromString(textRep, i);
        i = parsedAttributes.getValue();

        //if position is at / character adjust position to > character
        if (textRep.charAt(i) == tagClosingChar)
            ++i;

        return new Pair<SelfClosingTag, Integer>(new SelfClosingTag(tagName, parsedAttributes.getKey()), i);
    }

    public HTMLSelfClosingTags getKind() {
        return name;
    }

}
