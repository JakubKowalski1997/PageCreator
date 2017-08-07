package TestClasses.TestTitleEditorClasses;

import TemplateEditor.PageEditor;
import TemplateEditor.TitleEditor.HTMLTitleEditor;
import java.awt.Font;
import TemplateHandlerClasses.PageTemplate;
import TemplateHandlerClasses.TemplateFactory;
import TemplateHandlerClasses.Templates;
import TestClasses.Test;
import HTMLHandlerClasses.*;
import CSSHandlerClasses.*;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Konrad on 2017-07-21.
 */
public class TestHTMLTitleEditor extends Test {
    TestHTMLTitleEditor() {
        super("TestHTMLTitleEditor");
    }

    public void test() {
        super.test();

        PageTemplate template = TemplateFactory.getInstance().create(Templates.FIRST);

        ArrayList<PageEditor> editors = new ArrayList<>();


        final String titleText = "Text";
        final String position = "center";
        final String textColor = "blue";
        final String backgroundColor = "green";
        final String fontFamily = "arial";
        final String fontStyle = "plain";
        final String fontSize = "20px";

        editors.add(HTMLTitleEditor.getPositionEditor(position));
        editors.add(HTMLTitleEditor.getTextColorEditor(textColor));
        editors.add(HTMLTitleEditor.getBackgroundColorEditor(backgroundColor));
        editors.add(HTMLTitleEditor.getFontEditor(new Font(fontFamily, Font.PLAIN, 20)));
        editors.add(HTMLTitleEditor.getTextEditor(titleText));

        for (PageEditor editor : editors) {
            editor.edit(template);
        }

        HTMLDocumentHandler htmlHandler = HTMLDocumentHandler.getInstance();
        HTMLDocument htmlDocument = template.getHTMLDoc();

        CSSDocumentHandler cssDocumentHandler = CSSDocumentHandler.getInstance();
        CSSDocument cssDocument = template.getCSSDoc();

        try {
            TextTag title = (TextTag) htmlHandler.getTag(htmlDocument, HTMLContainerTags.DIV, Arrays.asList(new TagAttribute("class", "Title")), 0);

            if (!title.getText().equals(titleText)) {
                reportError("Expected title: " + titleText + " Got: " + title.getText());
            }

            CSSElement titleElement = cssDocumentHandler.getElement(cssDocument, new CSSSelector(CSSSelectorTypes.CLASS, "Title"));

            if (!titleElement.getAttribute("font-family").values.get(0).equals(fontFamily)) {
                reportError("Expected font family: " + fontFamily + " Got: " + titleElement.getAttribute("font-family").values.get(0));
            }

            if (!titleElement.getAttribute("font-size").values.get(0).equals(fontSize)) {
                reportError("Expected font size: " + fontSize + " Got: " + titleElement.getAttribute("font-size").values.get(0));
            }

            if (!titleElement.getAttribute("font-style").values.get(0).equals(fontStyle)) {
                reportError("Expected font style: " + fontStyle + " Got: " + titleElement.getAttribute("font-style").values.get(0));
            }

            if (!titleElement.getAttribute("color").values.get(0).equals(textColor)) {
                reportError("Expected font color: " + textColor + " Got: " + titleElement.getAttribute("color").values.get(0));
            }

            if (!titleElement.getAttribute("background-color").values.get(0).equals(backgroundColor)) {
                reportError("Expected background color: " + backgroundColor + " Got: " + titleElement.getAttribute("background-color").values.get(0));
            }

            if (!titleElement.getAttribute("text-align").values.get(0).equals(position)) {
                reportError("Expected text position: " + backgroundColor + " Got: " + titleElement.getAttribute("background-color").values.get(0));
            }

        }
        catch (Exception e) {
            reportError(e.getMessage());
        }

        reportResults();
    }

    public static void main(String[] args) {
        TestHTMLTitleEditor test = new TestHTMLTitleEditor();
        test.test();
    }
}
