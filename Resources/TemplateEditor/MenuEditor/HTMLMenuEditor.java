package TemplateEditor.MenuEditor;

/**
 * Created by Konrad on 2017-08-04.
 */

import CSSHandlerClasses.CSSSelectorTypes;
import HTMLHandlerClasses.*;
import TemplateEditor.PageEditor;
import TemplateHandlerClasses.Templates;

import TemplateEditor.BasicHTMLEditors;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Arrays;
import java.awt.Font;

public class HTMLMenuEditor {
    static final String sectionName = "Menu";

    public static PageEditor getOptionEditor(List<String> options, Templates kind) {
        if (kind.equals(Templates.SECOND)) {
            return template -> {
                HTMLDocument htmlDocument = template.getHTMLDoc();
                HTMLDocumentHandler handler =  HTMLDocumentHandler.getInstance();
                ArrayList<HTMLTag> newTags = new ArrayList<>();

                for (String option : options) {
                    ContainerTag td = new ContainerTag(HTMLContainerTags.TD);
                    ArrayList<TagAttribute> attributes = new ArrayList<>();
                    Collections.addAll(attributes, new TagAttribute("href", option + ".html"), new TagAttribute("target", "iframe"));
                    td.addNestedTag(new TextTag(HTMLTextTags.A, attributes, option));
                    newTags.add(td);
                }

                try {
                    handler.eraseTag(htmlDocument, HTMLContainerTags.DIV, Arrays.asList(new TagAttribute("class", sectionName)), 0);
                    ContainerTag optionsTable = new ContainerTag(HTMLContainerTags.TABLE);
                    ContainerTag tr = new ContainerTag(HTMLContainerTags.TR);

                    for (HTMLTag newTag : newTags) {
                        tr.addNestedTag(newTag);
                    }

                    optionsTable.addNestedTag(tr);
                    handler.addTag(htmlDocument, optionsTable, HTMLContainerTags.DIV, Arrays.asList(new TagAttribute("class", sectionName)));
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
                    ArrayList<TagAttribute> attributes = new ArrayList<>();
                    Collections.addAll(attributes, new TagAttribute("href", option + ".html"), new TagAttribute("target", "iframe"));
                    td.addNestedTag(new TextTag(HTMLTextTags.A, attributes, option));
                    tr.addNestedTag(td);
                    newTags.add(tr);
                }

                try {
                    handler.eraseTag(htmlDocument, HTMLContainerTags.DIV, Arrays.asList(new TagAttribute("class", sectionName)), 0);
                    ContainerTag optionsTable = new ContainerTag(HTMLContainerTags.TABLE, Arrays.asList(new TagAttribute("class", "fillParent")));
                    for (HTMLTag newTag : newTags) {
                        optionsTable.addNestedTag(newTag);
                    }

                    handler.addTag(htmlDocument, optionsTable, HTMLContainerTags.DIV, Arrays.asList(new TagAttribute("class", sectionName)));
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            };
        }
    }

    public static PageEditor getBackgroundColorEditor(String color) {
        return BasicHTMLEditors.getBackgroundColorEditor(color, sectionName);
    }

    public static PageEditor getTextColorEditor(String color) {
        return BasicHTMLEditors.getTextColorEditor(color, CSSSelectorTypes.TAG, HTMLTextTags.A.toString());
    }

    public static PageEditor getPositionEditor(String position) {
        return BasicHTMLEditors.getPositionEditor(position, sectionName);
    }

    public static PageEditor getFontEditor(Font font) {
        return BasicHTMLEditors.getFontEditor(font, sectionName);
    }
}
