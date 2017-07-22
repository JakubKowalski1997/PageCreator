package TemplateEditor.TitleEditor;

import CSSHandlerClasses.*;
import HTMLHandlerClasses.*;
import TemplateEditor.TemplateEditor;

import java.awt.Font;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * Created by Konrad on 2017-07-21.
 */
public class HTMLTitleEditor {

    private static final String sectionName = "Title";

    public static TemplateEditor getTextColorEditor(String color) {
        return template -> {
            final  String colorAttributeName = "color";

            TemplateEditor.setCSSAttribute(template, sectionName, new CSSAttribute(colorAttributeName, color));
        };

    }

    public static TemplateEditor getBackgroundColorEditor(String color) {
        return template -> {
            final String backgroundAttColorName = "background-color";

            TemplateEditor.setCSSAttribute(template, sectionName, new CSSAttribute(backgroundAttColorName, color));
        };
    }

    public static TemplateEditor getFontEditor(Font font) {
        return template -> {
            final String fontFamilyAttributeName = "font-family";
            final String fontSizeAttributeName = "font-size";

            ArrayList<CSSAttribute> attributes = new ArrayList<>();
            Collections.addAll(attributes, new CSSAttribute(fontFamilyAttributeName, font.getFamily().toLowerCase()),
                    new CSSAttribute(fontSizeAttributeName, Integer.toString(font.getSize()) + "px"));

            TemplateEditor.setCSSAttributes(template, sectionName, attributes);
        };
    }

    public static TemplateEditor getPositionEditor(String position) {
        return template -> {
            final String positionAttributeName = "position";

            TemplateEditor.setCSSAttribute(template, sectionName, new CSSAttribute(positionAttributeName, position));
        };
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
