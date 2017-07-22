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
                "\t\t<div class=\"Parent\">\n" +
                "\t\t\t<div class=\"Title\">\n" +
                "\t\t\t\t<p>Text</p>\n" +
                "\t\t\t</div>\n" +
                "\t\t\t<div class=\"dziecko1\"></div>\n" +
                "\t\t\t<div class=\"dziecko2\"></div>\n" +
                "\t\t\t<br/>\n" +
                "\t\t\t<div class=\"dolny\"></div>\n" +
                "\t\t</div>\n" +
                "\t</body>\n" +
                "</html>";

        String correctCSSDoc = "body {\n" +
                "\tmin-width : 640px;\n" +
                "\tmax-width : 1820px;\n" +
                "}\n" +
                "\n" +
                ".Title {\n" +
                "\twidth : 100%;\n" +
                "\tposition : center;\n" +
                "\tcolor : blue;\n" +
                "\tbackground-color : green;\n" +
                "\tfont-family : arial;\n" +
                "\tfont-size : 20px;\n" +
                "\tfont-style : plain;\n" +
                "}\n" +
                "\n" +
                ".dziecko1 {\n" +
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
