package TemplateHandlerClasses;

import CSSHandlerClasses.CSSDocument;
import CSSHandlerClasses.CSSParser;
import HTMLHandlerClasses.HTMLDocument;
import HTMLHandlerClasses.HTMLDocumentParser;
import Utils.DocumentUtils.DocumentReader;
import Utils.DocumentUtils.DocumentWriter;
import Utils.Page;
import Utils.ParserUtils.ApplyParsing;

import java.io.IOException;

/**
 * Created by Konrad on 2017-07-19.
 */
public class PageTemplate extends Page {

    public PageTemplate(String htmlPath, String cssPath) {
        super("index"); //set page name

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

    public void save(String path) throws IOException {
        super.saveHTMLDocument(path, true);
        super.saveCSSDocumnet(path, "main");
    }
}
