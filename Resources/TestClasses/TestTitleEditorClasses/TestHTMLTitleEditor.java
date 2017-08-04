package TestClasses.TestTitleEditorClasses;

import TemplateEditor.TemplateEditor;
import TemplateEditor.TitleEditor.HTMLTitleEditor;
import java.awt.Font;
import TemplateHandlerClasses.PageTemplate;
import TemplateHandlerClasses.TemplateFactory;
import TemplateHandlerClasses.Templates;
import TestClasses.Test;

import java.util.ArrayList;

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

        ArrayList<TemplateEditor> editors = new ArrayList<>();

        editors.add(HTMLTitleEditor.getPositionEditor("center"));
        editors.add(HTMLTitleEditor.getTextColorEditor("blue"));
        editors.add(HTMLTitleEditor.getBackgroundColorEditor("green"));
        editors.add(HTMLTitleEditor.getFontEditor(new Font("arial", Font.PLAIN, 20)));
        editors.add(HTMLTitleEditor.getTextEditor("Text"));

        for (TemplateEditor editor : editors) {
            editor.edit(template);
        }

        String correctHTMLDoc = "<!DOCTYPE html>\n" +
                "\n" +
                "<html>\n" +
                "\t<body>\n" +
                "\t\t<div class=\"Title\">\n" +
                "\t\t\t<p class=\"TitleText\">Text</p>\n" +
                "\t\t</div>\n" +
                "\t\t<div class=\"Menu\">\n" +
                "\t\t\t<table class=\"fillParent\"></table>\n" +
                "\t\t</div>\n" +
                "\t\t<div class=\"dziecko2\"></div>\n" +
                "\t\t<br/>\n" +
                "\t\t<div class=\"dolny\"></div>\n" +
                "\t</body>\n" +
                "</html>";

        String correctCSSDoc = "html {\n" +
                "\tmargin : 0px;\n" +
                "\theight : 100%;\n" +
                "}\n" +
                "\n" +
                ".fillParent {\n" +
                "\twidth : 100%;\n" +
                "\theight : 100%;\n" +
                "}\n" +
                "\n" +
                "body {\n" +
                "\tmargin : 0px;\n" +
                "\theight : 100%;\n" +
                "\tmin-width : 640px;\n" +
                "\tmax-width : 1820px;\n" +
                "}\n" +
                "\n" +
                ".Title {\n" +
                "\tmargin : 0px;\n" +
                "\twidth : 100%;\n" +
                "\ttext-align : center;\n" +
                "\tcolor : blue;\n" +
                "\tbackground-color : green;\n" +
                "\tfont-family : arial;\n" +
                "\tfont-size : 20px;\n" +
                "\tfont-style : plain;\n" +
                "}\n" +
                "\n" +
                ".TitleText {\n" +
                "\tmargin : 0px;\n" +
                "}\n" +
                "\n" +
                ".Menu {\n" +
                "\theight : 80%;\n" +
                "\twidth : 25%;\n" +
                "\tfloat : left;\n" +
                "}\n" +
                "\n" +
                ".dziecko2 {\n" +
                "\twidth : 75%;\n" +
                "\tfloat : left;\n" +
                "}\n" +
                "\n" +
                ".dolny {\n" +
                "\twidth : 100%;\n" +
                "}\n\n";

        if (!template.getCSSDoc().toString().equals(correctCSSDoc) ||
                !template.getHTMLDoc().toString().equals(correctHTMLDoc)) {
            reportError("Expected: \n" + correctCSSDoc + '\n' + correctHTMLDoc +
                            "\nGot: \n" + template.getCSSDoc().toString() + '\n' + template.getHTMLDoc().toString());
        }

        reportResults();
    }

    public static void main(String[] args) {
        TestHTMLTitleEditor test = new TestHTMLTitleEditor();
        test.test();
    }
}
