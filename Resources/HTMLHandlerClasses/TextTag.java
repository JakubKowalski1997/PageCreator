package HTMLHandlerClasses;
/**
 * Created by Konrad on 2017-06-28.
 */

import java.util.ArrayList;
import java.util.List;

import Utils.ParserUtils.Parser;
import javafx.util.Pair;

public class TextTag extends HTMLTag {
    private HTMLTextTags name;
    private String text;

    public TextTag(HTMLTextTags kind, String text) {
        super(new ArrayList<TagAttribute>());
        this.name = kind;
        this.text = text;
    }

    public TextTag(HTMLTextTags kind, List<TagAttribute> attributes, String text) {
        super(attributes);
        this.name = kind;
        this.text = text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return this.text;
    }

    /**
     * @return String representation of a tag
     */
    @Override
    public String toString() {
        StringBuilder stringRep = new StringBuilder();

        //print left marker
        stringRep.append(leftTagParenthesis);
        stringRep.append(name.toString());
        stringRep.append(super.attributeListToString());
        stringRep.append(super.rightTagParenthesis);

        //print content
        stringRep.append(text);

        //print right marker
        stringRep.append(super.leftTagParenthesis);
        stringRep.append(super.tagClosingChar);
        stringRep.append(name.toString());
        stringRep.append(rightTagParenthesis);
        return stringRep.toString();
    }

    public static Pair<TextTag, Integer> parseFromString(String textRep, int initPos) throws Exception {
        if (textRep.charAt(initPos) != leftTagParenthesis)
            throw new Exception("Error. Expected " + leftTagParenthesis);

        int i = ++initPos;

        //omit whitespaces
        i = Parser.omitWhitespaces(textRep, i);

        //get tag name
        Pair<String, Integer> tagNamePair = ContainerTagParser.getTagName(textRep, i);
        HTMLTextTags tagName = HTMLTextTags.valueOf(tagNamePair.getKey().toUpperCase());
        i = tagNamePair.getValue();

        Pair<List<TagAttribute>, Integer> parsed = HTMLTag.parseAttributesFromString(textRep, i);

        i = parsed.getValue();
        ++i;

        StringBuilder contentBuilder = new StringBuilder();
        while (textRep.charAt(i) != leftTagParenthesis) {
            contentBuilder.append(textRep.charAt(i));
            ++i;
        }

        String text = contentBuilder.toString();

        Pair<Boolean, Integer> isClosingCorrect = ContainerTagParser.isClosingCorrect(textRep, tagName.toString(), i);
        if (!isClosingCorrect.getKey())
            throw new Exception("Bad closing");
        i = isClosingCorrect.getValue();

        return new Pair<TextTag, Integer>(new TextTag(tagName, parsed.getKey(), text), i);
    }

    public HTMLTextTags getKind() {
        return name;
    }

}
