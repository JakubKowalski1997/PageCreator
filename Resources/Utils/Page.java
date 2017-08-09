package Utils;

import CSSHandlerClasses.CSSDocument;
import HTMLHandlerClasses.HTMLDocument;
import Utils.DocumentUtils.DocumentWriter;

import java.io.IOException;

/**
 * Created by Konrad on 2017-08-05.
 */
public class Page {
    private CSSDocument cssDocument;
    private HTMLDocument htmlDocument;
    private String name;
    private String charset;

    public Page(String name) {
        this.name = name;
        setCharset("utf-8"); //set default charset
        cssDocument = new CSSDocument();
        htmlDocument = new HTMLDocument();
    }

    public Page(String name, HTMLDocument htmlDoc, CSSDocument cssDoc, String charset) {
        this.name = name;
        cssDocument = cssDoc;
        htmlDocument = htmlDoc;
        setCharset(charset);
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public String getCharset() {
        return charset;
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

    public void saveHTMLDocument(String path, boolean isHtm) throws IOException {
        String extension = isHtm ? "htm" : "html";
        DocumentWriter.writeToFile(getHTMLDoc(), path + '/' + getName() + '.' + extension, getCharset());
    }

    public void saveHTMLDocument(String path) throws IOException {
        saveHTMLDocument(path, false);
    }

    public void saveCSSDocumnet(String path, String filename) throws IOException {
        DocumentWriter.writeToFile(getCSSDoc(), path + '/' + filename + ".css", getCharset());
    }
}
