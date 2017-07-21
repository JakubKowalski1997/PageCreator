package TemplateEditor.TitleEditor;

import CSSHandlerClasses.*;
import HTMLHandlerClasses.*;
import TemplateEditor.TemplateEditor;
import TemplateEditor.Utils.Color;
import TemplateEditor.Utils.Position;
import TemplateEditor.Utils.Font;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * Created by Konrad on 2017-07-21.
 */
public class HTMLTitleEditor {

    private static HTMLTitleEditor instance = new HTMLTitleEditor();

    public static  HTMLTitleEditor getInstance() {
        return instance;
    }

    private static final String sectionName = "Title";

    public TemplateEditor getTextColorEditor(Color color) {
        return template -> {
            final  String colorAttributeName = "color";

            TemplateEditor.setCSSAttribute(template, sectionName, new CSSAttribute(colorAttributeName, color.toString()));
        };

    }

    public TemplateEditor getBackgroundColorEditor(Color color) {
        return template -> {
            final String backgroundAttColorName = "background-color";

            TemplateEditor.setCSSAttribute(template, sectionName, new CSSAttribute(backgroundAttColorName, color.toString()));
        };
    }

    public TemplateEditor getFontEditor(Font font) {
        return template -> {
            final String fontFamilyAttributeName = "font-family";
            final String fontSizeAttributeName = "font-size";

            ArrayList<CSSAttribute> attributes = new ArrayList<>();
            Collections.addAll(attributes, new CSSAttribute(fontFamilyAttributeName, font.family.toString()),
                    new CSSAttribute(fontSizeAttributeName, Integer.toString(font.size) + "px"));

            TemplateEditor.setCSSAttributes(template, sectionName, attributes);
        };
    }

    public TemplateEditor getPositionEditor(Position position) {
        return template -> {
            final String positionAttributeName = "position";

            TemplateEditor.setCSSAttribute(template, sectionName, new CSSAttribute(positionAttributeName, position.toString()));
        };
    }

    public TemplateEditor getTextEditor(String text) {
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
