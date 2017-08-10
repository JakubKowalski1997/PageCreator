package TestClasses.TestContentEditorClasses;

import CSSHandlerClasses.CSSDocument;
import HTMLHandlerClasses.HTMLDocument;
import TemplateEditor.ContentEditor.HTMLContentEditor;
import TemplateEditor.PageEditor;
import Utils.Page;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Konrad on 2017-08-10.
 */
public class HTMLContentEditorTest extends TestCase {
    public void test() {
        ArrayList<PageEditor> editors = new ArrayList<>();
        Collections.addAll(editors,
                HTMLContentEditor.getHTMLContentEditor("utf-8", "text"),
                HTMLContentEditor.getPositionEditor("center"),
                HTMLContentEditor.getSubPageStyleEditor());

        Page page = new Page("page", new HTMLDocument(), new CSSDocument(), "utf-8");

        for (PageEditor editor : editors) {
            editor.edit(page);
        }

        String expectedRep = "<!DOCTYPE html>\n" +
                "\n" +
                "<html>\n" +
                "\t<head>\n" +
                "\t\t<meta charset=\"utf-8\">\n" +
                "\t\t<style>\n" +
                "\t\thtml, body, .Content {\n" +
                "\t\t\tmargin : 0px;\n" +
                "\t\t\theight : 100%;\n" +
                "\t\t}\n" +
                "\t\t\n" +
                "\t\t.Content {\n" +
                "\t\t\ttext-align : center;\n" +
                "\t\t}\n" +
                "\t\t</style>\n" +
                "\t</head>\n" +
                "\t<body>\n" +
                "\t\t<p class=\"Content\">text</p>\n" +
                "\t</body>\n" +
                "</html>";

        assertEquals(expectedRep, page.getHTMLDoc().toString());
    }
}