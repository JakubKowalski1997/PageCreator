package TemplateEditor;

import CSSHandlerClasses.*;
import HTMLHandlerClasses.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Konrad on 2017-08-04.
 */
public class BasicHTMLEditors {

    private static String buildRgbColor(Color color) {
        StringBuilder valueBuilder = new StringBuilder();
        valueBuilder.append("rgb(");
        valueBuilder.append(Integer.toString(color.getRed()));
        valueBuilder.append(", ");
        valueBuilder.append(Integer.toString(color.getGreen()));
        valueBuilder.append(", ");
        valueBuilder.append(Integer.toString(color.getBlue()));
        valueBuilder.append(")");

        return valueBuilder.toString();
    }

    private static void setCSSAttribute(CSSDocument cssDocument, CSSSelector cssSelector, String attributeName, String value) throws Exception {
        CSSDocumentHandler handler = CSSDocumentHandler.getInstance();

        CSSElement sectionElement = handler.getElement(cssDocument, cssSelector);

        CSSAttribute cssAttribute = sectionElement.getAttribute(attributeName);
        if (cssAttribute == null) {
            sectionElement.addAttribute(new CSSAttribute(attributeName, value));
        }
        else {
            cssAttribute.values.set(0, value);
        }
    }

    public static PageEditor getTextColorEditor(Color color, String sectionName) {
        return template -> {
            final String colorAttributeName = "color";

            String value = buildRgbColor(color);

            CSSDocument cssDoc = template.getCSSDoc();

            try {
                setCSSAttribute(cssDoc, new CSSSelector(CSSSelectorTypes.CLASS, sectionName), colorAttributeName, value);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        };
    }

    public static PageEditor getBackgroundColorEditor(Color color, String sectionName) {
        return template -> {
            final String backgroundColorAttributeName = "background-color";

            String value = buildRgbColor(color);

            CSSDocument cssDoc = template.getCSSDoc();

            try {
                setCSSAttribute(cssDoc, new CSSSelector(CSSSelectorTypes.CLASS, sectionName), backgroundColorAttributeName, value);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        };
    }

    public static PageEditor getTextColorEditor(String color, CSSSelectorTypes selectorType, String sectionName) {
        return template -> {
            final  String colorAttributeName = "color";

            CSSDocument cssDoc = template.getCSSDoc();

            try {
                setCSSAttribute(cssDoc, new CSSSelector(selectorType, sectionName), colorAttributeName, color.toLowerCase());
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        };
    }

    public static PageEditor getTextColorEditor(String color, String sectionName) {
        return getTextColorEditor(color, CSSSelectorTypes.CLASS, sectionName);
    }

    public static PageEditor getBackgroundColorEditor(String color, String sectionName) {
        return template -> {
            final String backgroundAttColorName = "background-color";

            CSSDocument cssDoc = template.getCSSDoc();

            try {
                setCSSAttribute(cssDoc, new CSSSelector(CSSSelectorTypes.CLASS, sectionName), backgroundAttColorName, color.toLowerCase());
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

    public static PageEditor getFontEditor(Font font, String sectionName) {
        return template -> {
            final String fontFamilyAttributeName = "font-family";
            final String fontSizeAttributeName = "font-size";
            final String fontStyleAttributeName = "font-style";
            final String fontWeightAttributeName = "font-weight";

            CSSDocument cssDoc = template.getCSSDoc();
            CSSDocumentHandler handler = CSSDocumentHandler.getInstance();


            try {
                CSSElement sectionElement = handler.getElement(cssDoc,
                        new CSSSelector(CSSSelectorTypes.CLASS, sectionName));

                /**
                 * Setting font-family
                 */
                CSSAttribute familyAttribute = sectionElement.getAttribute(fontFamilyAttributeName);
                if (familyAttribute == null) {
                    sectionElement.addAttribute(new CSSAttribute(fontFamilyAttributeName, font.getFamily().toLowerCase()));
                }
                else {
                    familyAttribute.values.set(0, font.getFamily().toLowerCase());
                }

                /**
                 * Setting size attribute
                 */
                CSSAttribute sizeAttribute = sectionElement.getAttribute(fontSizeAttributeName);
                if (sizeAttribute == null) {
                    sectionElement.addAttribute(new CSSAttribute(fontSizeAttributeName, Integer.toString(font.getSize()) + "px"));
                }
                else {
                    sizeAttribute.values.set(0, Integer.toString(font.getSize()) + "px");
                }

                /**
                 * setting font weight
                 */
                CSSAttribute weightAttribute = sectionElement.getAttribute(fontWeightAttributeName);
                if (isBold(font.getStyle())) {
                    if (weightAttribute == null) {
                        sectionElement.addAttribute(new CSSAttribute(fontWeightAttributeName, "bold"));
                    }
                    else {
                        weightAttribute.values.set(0, "bold");
                    }
                }
                else if (weightAttribute != null){
                    sectionElement.eraseAttribute(sectionElement.getAttribute(fontWeightAttributeName));
                }

                /**
                 * Setting font style
                 */
                CSSAttribute fontStyleAttribute = sectionElement.getAttribute(fontStyleAttributeName);
                if (isItalic(font.getStyle()) || isPlain(font.getStyle())) {
                    if (fontStyleAttribute == null) {
                        sectionElement.addAttribute(new CSSAttribute(fontStyleAttributeName, getFontStyleValues(font.getStyle())));
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

    public static PageEditor getPositionEditor(String position, String sectionName) {
        return template -> {
            final String positionAttributeName = "text-align";

            CSSDocument cssDoc = template.getCSSDoc();

            try {
                setCSSAttribute(cssDoc, new CSSSelector(CSSSelectorTypes.CLASS, sectionName), positionAttributeName, position);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        };
    }

    public static PageEditor getTextEditor(String text, String sectionName) {
        return template -> {
            HTMLDocument htmlDocument = template.getHTMLDoc();
            HTMLDocumentHandler handler = HTMLDocumentHandler.getInstance();

            try {
                TextTag textTag = (TextTag) handler.getTag(htmlDocument, HTMLContainerTags.DIV,
                        Arrays.asList(new TagAttribute("class", sectionName)), 0);
                textTag.setText(text);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        };
    }
}
