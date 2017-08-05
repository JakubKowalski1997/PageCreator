package TemplateHandlerClasses;

import CSSHandlerClasses.CSSDocument;
import CSSHandlerClasses.CSSParser;
import HTMLHandlerClasses.HTMLDocument;
import HTMLHandlerClasses.HTMLDocumentParser;
import Utils.DocumentUtils.DocumentReader;
import Utils.DocumentUtils.DocumentWriter;
import Utils.Page;
import Utils.ParserUtils.ApplyParsing;

import java.io.FileNotFoundException;

/**
 * Created by Konrad on 2017-07-19.
 */
public class PageTemplate extends Page {
    private String htmlPath, cssPath;

    public PageTemplate(String htmlPath, String cssPath) {
        this.htmlPath = htmlPath;
        this.cssPath = cssPath;

        try {
            String htmlFileContent = DocumentReader.readFromFile(htmlPath);
            setHTMLDoc((HTMLDocument) ApplyParsing.apply(new HTMLDocumentParser(), htmlFileContent));

            String cssFileContent = DocumentReader.readFromFile(cssPath);
            setCSSDoc((CSSDocument) ApplyParsing.apply(new CSSParser(), cssFileContent));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getHtmlPath() {
        return htmlPath;
    }

    public String getCssPath() {
        return cssPath;
    }

    public void save(String htmlPath, String cssPath) {
        try {
            DocumentWriter.writeToFile(getHTMLDoc(), htmlPath);
            DocumentWriter.writeToFile(getCSSDoc(), cssPath);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
