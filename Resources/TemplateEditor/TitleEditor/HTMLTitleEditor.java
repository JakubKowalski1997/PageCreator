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

    private static String getFontStyleValues(int fontStyle) {
        ArrayList<String> values = new ArrayList<>();
        switch (fontStyle) {
            case 0:
                return "plain";
            case 3:
            case 2:
                return "italic";
        }

        return null;
    }

    private static boolean isBold(int fontStyle) {
        return fontStyle == 1 || fontStyle == 3;
    }

    private static boolean isItalic(int fontStyle) {
        return fontStyle == 2 || fontStyle == 3;
    }

    private static boolean isPlain(int fontStyle) {
        return fontStyle == 0;
    }

    public static TemplateEditor getFontEditor(Font font) {
        return template -> {
            final String fontFamilyAttributeName = "font-family";
            final String fontSizeAttributeName = "font-size";
            final String fontStyleAttributeName = "font-style";
            final String fontWeightAttributeName = "font-weight";

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
                 * setting font weight
                 */
                CSSAttribute weightAttribute = titleElement.getAttribute(fontWeightAttributeName);
                if (isBold(font.getStyle())) {
                    if (weightAttribute == null) {
                        titleElement.addAttribute(new CSSAttribute(fontWeightAttributeName, "bold"));
                    }
                    else {
                       weightAttribute.values.set(0, "bold");
                    }
                }
                else if (weightAttribute != null){
                    titleElement.eraseAttribute(titleElement.getAttribute(fontWeightAttributeName));
                }

                /**
                 * Setting font style
                 */
                CSSAttribute fontStyleAttribute = titleElement.getAttribute(fontStyleAttributeName);
                if (isItalic(font.getStyle()) || isPlain(font.getStyle())) {
                    if (fontStyleAttribute == null) {
                        titleElement.addAttribute(new CSSAttribute(fontStyleAttributeName, getFontStyleValues(font.getStyle())));
                    } else {
                        fontStyleAttribute.values.set(0, getFontStyleValues(font.getStyle()));
                    }
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        };
    }

    public static TemplateEditor getPositionEditor(String position) {
        return template -> {
            final String positionAttributeName = "text-align";

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
