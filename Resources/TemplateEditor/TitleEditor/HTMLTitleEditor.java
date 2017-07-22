package TemplateEditor.TitleEditor;

import CSSHandlerClasses.*;
import HTMLHandlerClasses.*;
import TemplateEditor.TemplateEditor;

import java.awt.Font;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Konrad on 2017-07-21.
 */
public class HTMLTitleEditor {

    private static final String sectionName = "Title";

    public static TemplateEditor getTextColorEditor(String color) {
        return template -> {
            final  String colorAttributeName = "color";

            CSSDocument cssDoc = template.getCSSDoc();
            CSSDocumentHandler handler = CSSDocumentHandler.getInstance();

            try {
                CSSElement titleElement = handler.getElement(cssDoc,
                        new CSSSelector(CSSSelectorTypes.CLASS, sectionName));

                CSSAttribute cssAttribute = titleElement.getAttribute(colorAttributeName);
                if (cssAttribute == null) {
                    titleElement.addAttribute(new CSSAttribute(colorAttributeName, color));
                }
                else {
                    cssAttribute.values.set(0, color);
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        };

    }

    public static TemplateEditor getBackgroundColorEditor(String color) {
        return template -> {
            final String backgroundAttColorName = "background-color";

            CSSDocument cssDoc = template.getCSSDoc();
            CSSDocumentHandler handler = CSSDocumentHandler.getInstance();

            try {
                CSSElement titleElement = handler.getElement(cssDoc,
                        new CSSSelector(CSSSelectorTypes.CLASS, sectionName));

                CSSAttribute cssAttribute = titleElement.getAttribute(backgroundAttColorName);
                if (cssAttribute == null) {
                    titleElement.addAttribute(new CSSAttribute(backgroundAttColorName, color));
                }
                else {
                    cssAttribute.values.set(0, color);
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        };
    }

    private static ArrayList<String> getFontStyleValues(int fontStyle) {
        ArrayList<String> values = new ArrayList<>();
        switch (fontStyle) {
            case 0:
                values.add("plain");
                break;
            case 1:
                values.add("bold");
                break;
            case 2:
                values.add("italic");
                break;
            case 3:
                values.add("bold");
                values.add("italic");
        }

        return values;
    }

    public static TemplateEditor getFontEditor(Font font) {
        return template -> {
            final String fontFamilyAttributeName = "font-family";
            final String fontSizeAttributeName = "font-size";
            final String fontStyleAttributeName = "font-style";

            CSSDocument cssDoc = template.getCSSDoc();
            CSSDocumentHandler handler = CSSDocumentHandler.getInstance();


            try {
                CSSElement titleElement = handler.getElement(cssDoc,
                        new CSSSelector(CSSSelectorTypes.CLASS, sectionName));

                /**
                 * Setting font-family
                 */
                CSSAttribute familyAttribute = titleElement.getAttribute(fontFamilyAttributeName);
                if (familyAttribute == null) {
                    titleElement.addAttribute(new CSSAttribute(fontFamilyAttributeName, font.getFamily().toLowerCase()));
                }
                else {
                    familyAttribute.values.set(0, font.getFamily().toLowerCase());
                }

                /**
                 * Setting size attribute
                 */
                CSSAttribute sizeAttribute = titleElement.getAttribute(fontSizeAttributeName);
                if (sizeAttribute == null) {
                    titleElement.addAttribute(new CSSAttribute(fontSizeAttributeName, Integer.toString(font.getSize()) + "px"));
                }
                else {
                    sizeAttribute.values.set(0, Integer.toString(font.getSize()) + "px");
                }

                /**
                 * Setting font style
                 */
                CSSAttribute fontStyleAttribute = titleElement.getAttribute(fontStyleAttributeName);
                if (fontStyleAttribute == null) {
                    titleElement.addAttribute(new CSSAttribute(fontStyleAttributeName, getFontStyleValues(font.getStyle())));
                }
                else {
                    fontStyleAttribute.values = getFontStyleValues(font.getStyle());
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        };
    }

    public static TemplateEditor getPositionEditor(String position) {
        return template -> {
            final String positionAttributeName = "position";

            CSSDocument cssDoc = template.getCSSDoc();
            CSSDocumentHandler handler = CSSDocumentHandler.getInstance();

            try {
                CSSElement titleElement = handler.getElement(cssDoc,
                        new CSSSelector(CSSSelectorTypes.CLASS, sectionName));

                CSSAttribute posAttribute = titleElement.getAttribute(positionAttributeName);
                if (posAttribute == null) {
                    titleElement.addAttribute(new CSSAttribute(positionAttributeName, position));
                }
                else {
                    posAttribute.values.set(0, position);
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
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
