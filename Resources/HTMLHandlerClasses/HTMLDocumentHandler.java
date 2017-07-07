package HTMLHandlerClasses;
/**
 * Created by Konrad on 2017-06-28.
 */

import javax.swing.text.html.HTML;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.Attributes;

//BUG: cycle reference results with stack overflow

public class HTMLDocumentHandler {
    private HTMLDocumentHandler() {}

    private static HTMLDocumentHandler instance = new HTMLDocumentHandler();

    public static HTMLDocumentHandler getInstance() {
        return instance;
    }

    public void addTag(HTMLDocument doc, HTMLTag newTag, HTMLContainerTags kind)
            throws Exception {

        List<TagAttribute> atts = new ArrayList<TagAttribute>();

        ContainerTag found;
        if (isFound(doc.getRootTag(), kind, atts)) {
            found = doc.getRootTag();
        } else {
            found = findTag(doc.getRootTag(), kind, atts);
        }

        if (found == null )
            throw new Exception("Cannot find such tag");

        found.addNestedTag(newTag);
    }

    public void addTag(HTMLDocument doc, HTMLTag newTag, HTMLContainerTags kind, int pos)
            throws Exception {

        List<TagAttribute> atts = new ArrayList<TagAttribute>();

        ContainerTag found;
        if (isFound(doc.getRootTag(), kind, atts)) {
            found = doc.getRootTag();
        } else {
            found = findTag(doc.getRootTag(), kind, atts);
        }

        if (found == null )
            throw new Exception("Cannot find such tag");

        found.addNestedTag(pos, newTag);
    }

    public void addTag(HTMLDocument doc, HTMLTag newTag, HTMLContainerTags kind, List<TagAttribute> atts, int pos)
            throws Exception {

        ContainerTag found;
        if (isFound(doc.getRootTag(), kind, atts)) {
            found = doc.getRootTag();
        } else {
            found = findTag(doc.getRootTag(), kind, atts);
        }

        if (found == null )
            throw new Exception("Cannot find such tag");

        found.addNestedTag(pos, newTag);
    }

    public void addTag(HTMLDocument doc, HTMLTag newTag, HTMLContainerTags kind, List<TagAttribute> atts)
            throws Exception {

        ContainerTag found;
        if (isFound(doc.getRootTag(), kind, atts)) {
            found = doc.getRootTag();
        } else {
            found = findTag(doc.getRootTag(), kind, atts);
        }

        if (found == null ) {
            throw new Exception("Cannot find such tag");
        }

        found.addNestedTag(newTag);
    }

    public void addTag(HTMLDocument doc, HTMLTag newTag, ContainerTag parent, int pos)
            throws Exception {

        ContainerTag found;
        if (doc.getRootTag() == parent) {
            found = doc.getRootTag();
        } else {
            found = findTag(doc.getRootTag(), parent);
        }

        if (found == null) {
            throw new Exception("Cannot find such tag");
        }

        found.addNestedTag(pos, newTag);
    }

    public void addTag(HTMLDocument doc, HTMLTag newTag, ContainerTag parent)
            throws Exception {

        ContainerTag found;
        if (doc.getRootTag() == parent) {
            found = doc.getRootTag();
        } else {
            found = findTag(doc.getRootTag(), parent);
        }

        if (found == null) {
            throw new Exception("Cannot find such tag");
        }

        found.addNestedTag(newTag);
    }

    public void eraseTag(HTMLDocument doc, HTMLContainerTags kind, List<TagAttribute> atts, int pos)
            throws Exception {

        ContainerTag found;
        if (isFound(doc.getRootTag(), kind, atts)) {
            found = doc.getRootTag();
        } else {
            found = findTag(doc.getRootTag(), kind, atts);
        }

        if (found == null )
            throw new Exception("Cannot find such tag");

        found.eraseNestedTag(pos);
    }

    public void eraseTag(HTMLDocument doc, HTMLContainerTags kind, List<TagAttribute> atts)
            throws Exception {

        ContainerTag found;
        if (isFound(doc.getRootTag(), kind, atts)) {
            found = doc.getRootTag();
        } else {
            found = findTag(doc.getRootTag(), kind, atts);
        }

        if (found == null )
            throw new Exception("Cannot find such tag");

        found.popNestedTag();
    }

    public void eraseTag(HTMLDocument doc, HTMLTag tagToErase)
            throws Exception {

        ContainerTag found;
        if (doc.getRootTag() == tagToErase) {
            found = doc.getRootTag();
        } else {
            found = findTag(doc.getRootTag(), tagToErase);
        }

        if (found == null )
            throw new Exception("Cannot find such tag");

        found.getParent().popNestedTag();
    }

    public HTMLTag getTag(HTMLDocument doc, HTMLContainerTags kind, List<TagAttribute> atts, int pos)
            throws Exception {

        ContainerTag found;
        if (isFound(doc.getRootTag(), kind, atts)) {
            found = doc.getRootTag();
        } else {
            found = findTag(doc.getRootTag(), kind, atts);
        }

        if (found == null )
            throw new Exception("Cannot find such tag");

        return found.getNestedTag(pos);
    }

    static private ContainerTag findTag(ContainerTag parent, HTMLContainerTags kind, List<TagAttribute> atts) {
        for (int i = 0; i < parent.getNumberOfChilds(); ++i) {
            HTMLTag childTag = parent.getNestedTag(i);

            if (childTag.isContainer()) {
                ContainerTag childContainer = (ContainerTag) childTag;
                if (isFound(childContainer, kind, atts)) {
                    return childContainer;
                }
                else {
                    ContainerTag found = findTag(childContainer, kind, atts);
                    if (found != null)
                        return found;
                }
            }
        }

        return null;
    }

    static private ContainerTag findTag(ContainerTag parent, HTMLTag tagToFind) {
        for (int i = 0; i < parent.getNumberOfChilds(); ++i) {
            HTMLTag childTag = parent.getNestedTag(i);

            if (childTag.isContainer()) {
                ContainerTag childContainer = (ContainerTag) childTag;
                if (childContainer == tagToFind) {
                    return childContainer;
                }
                else {
                    ContainerTag found = findTag(childContainer, tagToFind);
                    if (found != null)
                        return found;
                }
            }
        }

        return null;
    }

    static private boolean isFound(ContainerTag tag, HTMLContainerTags kind, List<TagAttribute> atts) {
        return tag.getKind().equals(kind) &&
                tag.hasAttributes(atts);
    }
}
