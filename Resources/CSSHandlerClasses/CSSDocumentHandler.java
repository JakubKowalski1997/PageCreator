package CSSHandlerClasses;

import javax.swing.text.html.CSS;
import java.util.ArrayList;

/**
 * Created by Konrad on 2017-07-13.
 */
public class CSSDocumentHandler {

    private static CSSDocumentHandler instance = new CSSDocumentHandler();

    public static CSSDocumentHandler getInstance() {
        return instance;
    }

    public void addElement(CSSDocument doc, CSSElement elem) {
        doc.getElements().add(elem);
    }

    public void eraseElement(CSSDocument doc, int pos) {
        doc.getElements().remove(pos);
    }

    public void eraseElement(CSSDocument doc, CSSElement elemToErase) {
        doc.getElements().remove(elemToErase);
    }

    public void popElement(CSSDocument doc) {
        doc.getElements().remove(doc.getElements().size() - 1);
    }

    public CSSElement getElement(CSSDocument doc, int pos) {
        return doc.getElements().get(pos);
    }

    public CSSElement getElement(CSSDocument doc, CSSSelector selector) throws Exception {
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
