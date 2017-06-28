/**
 * Created by Konrad on 2017-06-28.
 */

//TODO: add methods enabling to traverse document
//TODO: add methods for adding/removing tags

import java.util.ArrayList;

public class HTMLDocument {
    private HTMLTag rootTag;

    private final String docDeclaration = "<!DOCTYPE html>";

    public HTMLDocument() {
        rootTag = new ContainerTag(HTMLContainerTags.HTML);
    }

    /**
     *
     * @return Returns string representation of a HTML document
     */
    public String toString() {
        StringBuilder stringRep = new StringBuilder();
        stringRep.append(docDeclaration);
        stringRep.append(rootTag.toString());

        return stringRep.toString();
    }
}
