package Utils;

import CSSHandlerClasses.CSSDocument;
import HTMLHandlerClasses.HTMLDocument;
import Utils.DocumentUtils.DocumentWriter;

import java.io.FileNotFoundException;

/**
 * Created by Konrad on 2017-08-05.
 */
public class Page {
    private CSSDocument cssDocument;
    private HTMLDocument htmlDocument;
    private String name;


    public Page(String name) {
        this.name = name;
    }

    public Page(String name, HTMLDocument htmlDoc, CSSDocument cssDoc) {
        this.name = name;
        cssDocument = cssDoc;
        htmlDocument = htmlDoc;
    }

    public String getName() {
        return name;
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

    public void saveHTMLDocument(String path, boolean isHtm) throws FileNotFoundException {
        String extension = isHtm ? "htm" : "html";
        DocumentWriter.writeToFile(getHTMLDoc(), path + '/' + getName() + '.' + extension);
    }

    public void saveHTMLDocument(String path) throws FileNotFoundException {
        saveHTMLDocument(path, false);
    }

    public void saveCSSDocumnet(String path, String filename) throws FileNotFoundException {
        DocumentWriter.writeToFile(getCSSDoc(), path + '/' + filename + ".css");
    }
}
