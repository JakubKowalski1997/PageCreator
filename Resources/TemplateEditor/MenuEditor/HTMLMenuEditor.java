package TemplateEditor.MenuEditor;

/**
 * Created by Konrad on 2017-08-04.
 */

import HTMLHandlerClasses.*;
import TemplateEditor.TemplateEditor;
import TemplateHandlerClasses.Templates;

import TemplateEditor.BasicHTMLEditors;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.awt.Font;

public class HTMLMenuEditor {
    static final String sectionName = "Menu";

    public static TemplateEditor getOptionEditor(List<String> options, Templates kind) {
        if (kind.equals(Templates.SECOND)) {
            return template -> {
                HTMLDocument htmlDocument = template.getHTMLDoc();
                HTMLDocumentHandler handler =  HTMLDocumentHandler.getInstance();
                ArrayList<HTMLTag> newTags = new ArrayList<>();

                for (String option : options) {
                    ContainerTag td = new ContainerTag(HTMLContainerTags.TD);
                    td.addNestedTag(new TextTag(HTMLTextTags.A, option));
                    newTags.add(td);
                }

                try {
                    ContainerTag optionsTable = (ContainerTag) handler.getTag(htmlDocument, HTMLContainerTags.DIV, Arrays.asList(new TagAttribute("class", sectionName)), 0);
                    ContainerTag tr = new ContainerTag(HTMLContainerTags.TR);

                    for (HTMLTag newTag : newTags) {
                        tr.addNestedTag(newTag);
                    }

                    optionsTable.addNestedTag(tr);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            };
        }
        else {
            return template -> {
                HTMLDocument htmlDocument = template.getHTMLDoc();
                HTMLDocumentHandler handler =  HTMLDocumentHandler.getInstance();
                ArrayList<HTMLTag> newTags = new ArrayList<>();

                for (String option : options) {
                    ContainerTag tr = new ContainerTag(HTMLContainerTags.TR);
                    ContainerTag td = new ContainerTag(HTMLContainerTags.TD);
                    td.addNestedTag(new TextTag(HTMLTextTags.A, option));
                    tr.addNestedTag(td);
                    newTags.add(tr);
                }

                try {
                    ContainerTag optionsTable = (ContainerTag) handler.getTag(htmlDocument, HTMLContainerTags.DIV, Arrays.asList(new TagAttribute("class", sectionName)), 0);
                    for (HTMLTag newTag : newTags) {
                        optionsTable.addNestedTag(newTag);
                    }
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            };
        }
    }

    public static TemplateEditor getBackgroundColorEditor(String color) {
        return BasicHTMLEditors.getBackgroundColorEditor(color, sectionName);
    }

    public static TemplateEditor getTextColorEditor(String color) {
        return BasicHTMLEditors.getTextColorEditor(color, sectionName);
    }

    public static TemplateEditor getPositionEditor(String position) {
        return BasicHTMLEditors.getPositionEditor(position, sectionName);
    }

    public static TemplateEditor getFontEditor(Font font) {
        return BasicHTMLEditors.getFontEditor(font, sectionName);
    }
}
