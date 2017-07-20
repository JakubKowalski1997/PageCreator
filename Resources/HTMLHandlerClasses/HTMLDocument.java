package HTMLHandlerClasses;

import Utils.ParserUtils.Parser;
import javafx.util.Pair;

/**
 * Created by Konrad on 2017-06-28.
 */

public class HTMLDocument {
    private ContainerTag rootTag;

    private final static String docDeclaration = "<!DOCTYPE html>";

    public HTMLDocument() {
        rootTag = new ContainerTag(HTMLContainerTags.HTML);
    }

    ContainerTag getRootTag() {
        return rootTag;
    }

    public void setRootTag(ContainerTag root) {
        rootTag = root;
    }

    /**
     *
     * @return Returns string representation of a HTML document
     */
    public String toString() {
        StringBuilder stringRep = new StringBuilder();
        stringRep.append(docDeclaration);
        stringRep.append('\n');
        stringRep.append('\n');
        stringRep.append(rootTag.toString());

        return stringRep.toString();
    }

    public static HTMLDocument parseFromString(String textRep) throws Exception {
        final String badDocDeclMsg = "Bad document declaration";

        if (textRep.charAt(0) != '<')
            throw new Exception(badDocDeclMsg);

        int i = 0;

        StringBuilder docDeclBuilder = new StringBuilder();
        for (; i < docDeclaration.length(); ++i) {
            docDeclBuilder.append(textRep.charAt(i));
        }

        if (!docDeclBuilder.toString().equals(docDeclaration)) {
            throw new Exception(badDocDeclMsg);
        }

        i = Parser.omitWhitespaces(textRep, i);

        Pair<ContainerTag, Integer> parsed = ContainerTag.parseFromString(textRep, i);

        HTMLDocument doc = new HTMLDocument();
        doc.setRootTag(parsed.getKey());

        return doc;
    }
}
