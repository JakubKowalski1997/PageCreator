package HTMLHandlerClasses;
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
}
