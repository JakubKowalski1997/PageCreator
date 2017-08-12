package CSSHandlerClasses;

import java.util.ArrayList;

/**
 * Created by Konrad on 2017-07-13.
 */
public class CSSDocumentHandler {

    static public void addElement(CSSDocument doc, CSSElement elem) {
        doc.getElements().add(elem);
    }

    static public void eraseElement(CSSDocument doc, int pos) {
        doc.getElements().remove(pos);
    }

    static public void eraseElement(CSSDocument doc, CSSElement elemToErase) {
        doc.getElements().remove(elemToErase);
    }

    static public void popElement(CSSDocument doc) {
        doc.getElements().remove(doc.getElements().size() - 1);
    }

    static  public CSSElement getElement(CSSDocument doc, int pos) {
        return doc.getElements().get(pos);
    }

    static public CSSElement getElement(CSSDocument doc, CSSSelector selector) throws Exception {
        ArrayList<CSSElement> elements = doc.getElements();

        for (CSSElement element : elements) {
            if (element.selectors.size() == 1) {
                if (element.selectors.get(0).equals(selector)) {
                    return element;
                }
            }
        }

        throw new Exception("Element not found");
    }
}
