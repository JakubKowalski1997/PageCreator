package TemplateHandlerClasses;

import CSSHandlerClasses.CSSDocument;
import CSSHandlerClasses.CSSParser;
import HTMLHandlerClasses.HTMLDocument;
import HTMLHandlerClasses.HTMLDocumentParser;
import Utils.DocumentUtils.DocumentReader;
import Utils.Page;
import Utils.ParserUtils.ApplyParsing;

import javax.swing.*;
import java.io.IOException;

/**
 * Created by Konrad on 2017-07-19.
 */
public class PageTemplate extends Page {

    public PageTemplate(String htmlPath, String cssPath) {
        super("index"); //set page name

        while (true) {
            try {
                String htmlFileContent = DocumentReader.readFromFile(htmlPath);
                setHTMLDoc((HTMLDocument) ApplyParsing.apply(new HTMLDocumentParser(), htmlFileContent));

                String cssFileContent = DocumentReader.readFromFile(cssPath);
                setCSSDoc((CSSDocument) ApplyParsing.apply(new CSSParser(), cssFileContent));

                //no exception was thrown so template was read successfully
                break;
            } catch (Exception e) {
                int confirm = JOptionPane.showConfirmDialog(null, "Error. Cannot read template\nEnsure that you have proper files in Resources/Templates \n" +
                                "and click ok to continue, cancel if you want to quit",
                        "ERROR", JOptionPane.OK_CANCEL_OPTION);

                if (confirm == JOptionPane.CANCEL_OPTION) {
                    System.exit(1);
                }
            }
        }
    }

    public void save(String path) throws IOException {
        super.saveHTMLDocument(path, true);
        super.saveCSSDocumnet(path, "main");
    }
}
