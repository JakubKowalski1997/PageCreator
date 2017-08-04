package TemplateEditor.TitleEditor;

import CSSHandlerClasses.*;
import HTMLHandlerClasses.*;
import TemplateEditor.TemplateEditor;
import TemplateEditor.BasicHTMLEditors;

import java.awt.Font;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Konrad on 2017-07-21.
 */
public class HTMLTitleEditor {

    private static final String sectionName = "Title";

    public static TemplateEditor getTextColorEditor(String color) {
            return BasicHTMLEditors.getTextColorEditor(color, sectionName);
    }

    public static TemplateEditor getBackgroundColorEditor(String color) {
        return BasicHTMLEditors.getBackgroundColorEditor(color, sectionName);
    }

    public static TemplateEditor getFontEditor(Font font) {
        return BasicHTMLEditors.getFontEditor(font, sectionName);
    }

    public static TemplateEditor getPositionEditor(String position) {
        return BasicHTMLEditors.getPositionEditor(position, sectionName);
    }

    public static TemplateEditor getTextEditor(String text) {
        return template -> {
            HTMLDocument htmlDocument = template.getHTMLDoc();
            HTMLDocumentHandler handler = HTMLDocumentHandler.getInstance();

            try {
                TextTag titleTag = (TextTag) handler.getTag(htmlDocument, HTMLContainerTags.DIV,
                        Arrays.asList(new TagAttribute("class", "Title")), 0);
                titleTag.setText(text);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        };
    }
}
