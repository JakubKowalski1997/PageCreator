package TemplateHandlerClasses;

import CSSHandlerClasses.CSSDocument;
import CSSHandlerClasses.CSSParser;
import HTMLHandlerClasses.HTMLDocument;
import HTMLHandlerClasses.HTMLDocumentParser;
import Utils.DocumentUtils.DocumentReader;
import Utils.DocumentUtils.DocumentWriter;
import Utils.ParserUtils.ApplyParsing;

import java.io.FileNotFoundException;

/**
 * Created by Konrad on 2017-07-19.
 */
public class PageTemplate {
    private HTMLDocument htmlDoc;
    private CSSDocument cssDoc;

    private String htmlPath, cssPath;

    public PageTemplate(String htmlPath, String cssPath) {
        this.htmlPath = htmlPath;
        this.cssPath = cssPath;

        try {
            String htmlFileContent = DocumentReader.readFromFile(htmlPath);
            htmlDoc = (HTMLDocument) ApplyParsing.apply(new HTMLDocumentParser(), htmlFileContent);

            String cssFileContent = DocumentReader.readFromFile(cssPath);
            cssDoc = (CSSDocument) ApplyParsing.apply(new CSSParser(), cssFileContent);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public HTMLDocument getHTMLDoc() {
        return htmlDoc;
    }
    public CSSDocument getCSSDoc() {
        return cssDoc;
    }

    public String getHtmlPath() {
        return htmlPath;
    }

    public String getCssPath() {
        return cssPath;
    }

    public void save(String htmlPath, String cssPath) {
        try {
            DocumentWriter.writeToFile(htmlDoc, htmlPath);
            DocumentWriter.writeToFile(cssDoc, cssPath);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
