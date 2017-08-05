package HTMLHandlerClasses;
/**
 * Created by Konrad on 2017-06-28.
 */

import java.util.ArrayList;
import java.util.List;

import Utils.ParserUtils.ApplyParsing;
import Utils.ParserUtils.Parser;
import javafx.util.Pair;

public class ContainerTag extends HTMLTag {
    private HTMLContainerTags name;
    private List<HTMLTag> nested;

    public ContainerTag(HTMLContainerTags kind) {
        super(new ArrayList<TagAttribute>());
        this.name = kind;
        nested = new ArrayList<HTMLTag>();
    }

    public ContainerTag(HTMLContainerTags kind, List<TagAttribute> attributes) {
        super(attributes);
        this.name = kind;
        nested = new ArrayList<HTMLTag>();
    }

    public ContainerTag(HTMLContainerTags kind, List<TagAttribute> attributes, List<HTMLTag> nestedTags) {
        super(attributes);
        this.name = kind;
        this.nested = nestedTags;
    }

    public void addNestedTag(HTMLTag newTag) {
        nested.add(newTag);
        newTag.setParent(this);
    }

    public void addNestedTag(int pos, HTMLTag newTag) {
        nested.add(pos, newTag);
        newTag.setParent(this);
    }

    public void eraseNestedTag(HTMLTag tagToErase) {
        nested.remove(tagToErase);
    }

    public void eraseNestedTag(int pos) {
        nested.remove(pos);
    }

    public void popNestedTag() {
        nested.remove(nested.size() - 1);
    }

    public int getNumberOfChilds() {
        return nested.size();
    }

    public HTMLTag getNestedTag(int pos) {
        return nested.get(pos);
    }

    public HTMLContainerTags getKind() {
        return name;
    }

    /**
     *
     * @param str
     * @return str with added \t at the beginning of every line
     */
    private String tabulateString(String str) {
        StringBuilder newStr = new StringBuilder();

        newStr.append('\t');
        for (int i = 0; i < str.length(); ++i) {
            newStr.append(str.charAt(i));
            if (str.charAt(i) == '\n') {
                newStr.append('\t');
            }
        }
        return newStr.toString();
    }

    /**
     *
     * @return String representation of a tag
     */
    @Override
    public String toString() {
        StringBuilder stringRep = new StringBuilder();

        //print left marker
        stringRep.append(super.leftTagParenthesis);
        stringRep.append(name.toString());
        stringRep.append(super.attributeListToString());
        stringRep.append(super.rightTagParenthesis);

        //print content
        for (HTMLTag tag : nested) {
            stringRep.append('\n');
            String nestedTagRepr = tag.toString();
            stringRep.append(tabulateString(nestedTagRepr));
        }

        if (nested.size() != 0)
            stringRep.append('\n');

        //print right marker
        stringRep.append(super.leftTagParenthesis);
        stringRep.append(super.tagClosingChar);
        stringRep.append(name.toString());
        stringRep.append(super.rightTagParenthesis);

        return stringRep.toString();
    }


    public static Pair<ContainerTag, Integer> parseFromString(String textRep, int initPos) throws Exception {
        if (textRep.charAt(initPos) != leftTagParenthesis)
            throw new Exception("Error. Expected " + leftTagParenthesis);

        int i = ++initPos;

        i = Parser.omitWhitespaces(textRep, i);

        //get tag name
        Pair<String, Integer> tagNamePair = ContainerTagParser.getTagName(textRep, i);
        HTMLContainerTags tagName = HTMLContainerTags.valueOf(tagNamePair.getKey().toUpperCase());
        i = tagNamePair.getValue();

        Pair<List<TagAttribute>, Integer> parsedAtts = HTMLTag.parseAttributesFromString(textRep, i);

        i = parsedAtts.getValue();
        ++i;

        i = Parser.findFirstCharOccurence(textRep, leftTagParenthesis, i);
        ++i;

        ArrayList<HTMLTag> nestedTags = new ArrayList<>();

        //not empty container
        while (textRep.charAt(i) != tagClosingChar) {
            i = Parser.omitWhitespaces(textRep, i);

            //get nested tag name
            Pair<String, Integer> nestedTagNamePair = ContainerTagParser.getTagName(textRep, i);
            i = nestedTagNamePair.getValue();

            //get proper Parser according to tag type
            Parser parser = (Parser) HTMLTagParserFactory.getInstance().create(nestedTagNamePair.getKey().toUpperCase());

            //go back to leftTagParenthesis to parse tag properly
            while (textRep.charAt(i) != leftTagParenthesis) {
                --i;
            }

            //parse nested tag
            Pair<HTMLTag, Integer> parsed = (Pair<HTMLTag, Integer>) ApplyParsing.apply(parser, textRep, i);
            nestedTags.add(parsed.getKey());
            i = parsed.getValue();
            ++i;

            i = Parser.findFirstCharOccurence(textRep, leftTagParenthesis, i);
            ++i;
        }

        Pair<Boolean, Integer> isClosingCorrect = ContainerTagParser.isClosingCorrect(textRep, tagName.toString(), --i);
        if (!isClosingCorrect.getKey())
            throw new Exception("Bad closing");
        i = isClosingCorrect.getValue();

        ContainerTag parsedContainer = new ContainerTag(tagName, parsedAtts.getKey());
        for (HTMLTag tag : nestedTags) {
            parsedContainer.addNestedTag(tag);
        }

        return new Pair<ContainerTag, Integer>(parsedContainer, i);
    }
}
