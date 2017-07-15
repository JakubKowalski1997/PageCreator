package CSSHandlerClasses;

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

    public void getElement(CSSDocument doc, int pos) {
        doc.getElements().get(pos);
    }
}
