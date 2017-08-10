package TestClasses.TestMenuEditorClasses;

import HTMLHandlerClasses.*;
import TemplateEditor.MenuEditor.HTMLMenuEditor;
import TemplateEditor.PageEditor;
import TemplateHandlerClasses.PageTemplate;
import TemplateHandlerClasses.TemplateFactory;
import TemplateHandlerClasses.Templates;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * Created by Konrad on 2017-08-10.
 */
public class HTMLMenuEditorTest extends TestCase {
    public void testFirstTemplate() throws Exception {
        PageTemplate template = TemplateFactory.getInstance().create(Templates.FIRST);

        ArrayList<String> options = new ArrayList<>();
        Collections.addAll(options, "Opcja1", "Opcja2");

        ArrayList<PageEditor> editors = new ArrayList<>();
        Collections.addAll(editors, HTMLMenuEditor.getOptionEditor(options, Templates.FIRST));

        for (PageEditor editor : editors) {
            editor.edit(template);
        }

        final String expectedFirstOptionText = "Opcja1";
        final String expectedSecondOptionText = "Opcja2";

        HTMLDocumentHandler handler = HTMLDocumentHandler.getInstance();
        HTMLDocument document = template.getHTMLDoc();

        ContainerTag table = (ContainerTag) handler.getTag(document, HTMLContainerTags.DIV, Arrays.asList(new TagAttribute("class", "Menu")), 0);
        ContainerTag firstRow = (ContainerTag) table.getNestedTag(0);
        ContainerTag firstOption = (ContainerTag) firstRow.getNestedTag(0);

        ContainerTag secondRow = (ContainerTag) table.getNestedTag(1);
        ContainerTag secondOption = (ContainerTag) secondRow.getNestedTag(0);

        String firstOptionText = ((TextTag) firstOption.getNestedTag(0)).getText();
        String secondOptionText = ((TextTag) secondOption.getNestedTag(0)).getText();


        assertEquals(expectedFirstOptionText, firstOptionText);
        assertEquals(expectedSecondOptionText, secondOptionText);

    }

    public void testSecondTemplate() throws Exception {
        PageTemplate template = TemplateFactory.getInstance().create(Templates.SECOND);


        ArrayList<String> options = new ArrayList<>();
        Collections.addAll(options, "Opcja1", "Opcja2");

        ArrayList<PageEditor> editors = new ArrayList<>();
        Collections.addAll(editors, HTMLMenuEditor.getOptionEditor(options, Templates.SECOND));

        for (PageEditor editor : editors) {
            editor.edit(template);
        }

        final String expectedFirstOptionText = "Opcja1";
        final String expectedSecondOptionText = "Opcja2";

        HTMLDocumentHandler handler = HTMLDocumentHandler.getInstance();
        HTMLDocument document = template.getHTMLDoc();

        ContainerTag table = (ContainerTag) handler.getTag(document, HTMLContainerTags.DIV, Arrays.asList(new TagAttribute("class", "Menu")), 0);
        ContainerTag row = (ContainerTag) table.getNestedTag(0);
        ContainerTag firstOption = (ContainerTag) row.getNestedTag(0);
        ContainerTag secondOption = (ContainerTag) row.getNestedTag(1);

        String firstOptionText = ((TextTag) firstOption.getNestedTag(0)).getText();
        String secondOptionText = ((TextTag) secondOption.getNestedTag(0)).getText();

        assertEquals(expectedFirstOptionText, firstOptionText);
        assertEquals(expectedSecondOptionText, secondOptionText);

    }
}