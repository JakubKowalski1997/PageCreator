package Utils;

import CSSHandlerClasses.CSSDocument;
import HTMLHandlerClasses.HTMLDocument;

/**
 * Created by Konrad on 2017-08-05.
 */
public class Page {
    private CSSDocument cssDocument;
    private HTMLDocument htmlDocument;

    public Page() {}

    public Page(HTMLDocument htmlDoc, CSSDocument cssDoc) {
        cssDocument = cssDoc;
        htmlDocument = htmlDoc;
    }

    public CSSDocument getCSSDoc() {
       return cssDocument;
   }

    public HTMLDocument getHTMLDoc() {
       return htmlDocument;
   }

    public void setCSSDoc(CSSDocument cssDoc) {
       cssDocument = cssDoc;
   }

    public void setHTMLDoc(HTMLDocument htmlDoc) {
       htmlDocument = htmlDoc;
    }
}
