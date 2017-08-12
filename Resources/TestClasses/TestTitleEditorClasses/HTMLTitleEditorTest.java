package TestClasses.TestTitleEditorClasses;

import CSSHandlerClasses.*;
import HTMLHandlerClasses.*;
import TemplateEditor.PageEditor;
import TemplateEditor.TitleEditor.HTMLTitleEditor;
import TemplateHandlerClasses.PageTemplate;
import TemplateHandlerClasses.TemplateFactory;
import TemplateHandlerClasses.Templates;
import junit.framework.TestCase;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * Created by Konrad on 2017-08-10.
 */
public class HTMLTitleEditorTest extends TestCase {
    public void test() throws Exception {
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

        HTMLDocument htmlDocument = template.getHTMLDoc();

        CSSDocument cssDocument = template.getCSSDoc();

        TextTag title = (TextTag) HTMLDocumentHandler.getTag(htmlDocument, HTMLContainerTags.DIV, Arrays.asList(new TagAttribute("class", "Title")), 0);

        assertEquals(titleText, title.getText());

        CSSElement titleElement = CSSDocumentHandler.getElement(cssDocument, new CSSSelector(CSSSelectorTypes.CLASS, "Title"));

        ArrayList<String> expected = new ArrayList<>();
        Collections.addAll(expected, fontFamily, fontSize, fontStyle, textColor, backgroundColor, position);

        ArrayList<String> elementsNames = new ArrayList<>();
        Collections.addAll(elementsNames, "font-family", "font-size", "font-style", "color", "background-color", "text-align");

        for (int i = 0; i < expected.size(); ++i) {
            assertEquals(expected.get(i), titleElement.getAttribute(elementsNames.get(i)).values.get(0));
        }

    }
}