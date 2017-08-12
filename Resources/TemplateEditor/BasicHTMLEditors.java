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

    private static String convertColor(String color) {
        switch (color) {
            case "dark_gray":
                return "dimgray";
            default:
                return color.toLowerCase();
        }
    }

    private static void setCSSAttribute(CSSDocument cssDocument, CSSSelector cssSelector, String attributeName, String value) throws Exception {
        CSSElement sectionElement = CSSDocumentHandler.getElement(cssDocument, cssSelector);

        CSSAttribute cssAttribute = sectionElement.getAttribute(attributeName);
        if (cssAttribute == null) {
            sectionElement.addAttribute(new CSSAttribute(attributeName, value));
        }
        else {
            cssAttribute.values.set(0, value);
        }
    }

    /**
     *
     * @param color to be inserted in css document
     * @param sectionName selector name
     * @return editor to edit page
     */
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

    /**
     *
     * @param color to be inserted in css document
     * @param sectionName selector name
     * @return editor to edit page
     */
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

    /**
     *
     * @param color to be inserted in css document
     * @param selectorType type of selector
     * @param sectionName name of a tag, or name of a class
     * @return editor to edit page
     */
    public static PageEditor getTextColorEditor(String color, CSSSelectorTypes selectorType, String sectionName) {
        return template -> {
            final  String colorAttributeName = "color";;

            CSSDocument cssDoc = template.getCSSDoc();

            try {
                setCSSAttribute(cssDoc, new CSSSelector(selectorType, sectionName), colorAttributeName, convertColor(color));
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        };
    }

    /**
     *
     * @param color to be inserted in css document
     * @param sectionName selector name
     * @return editor to edit template
     */
    public static PageEditor getTextColorEditor(String color, String sectionName) {
        return getTextColorEditor(color, CSSSelectorTypes.CLASS, sectionName);
    }

    /**
     *
     * @param color to be inserted in css document
     * @param sectionName selector name
     * @return editor to edit page
     */
    public static PageEditor getBackgroundColorEditor(String color, String sectionName) {
        return template -> {
            final String backgroundAttColorName = "background-color";

            CSSDocument cssDoc = template.getCSSDoc();

            try {
                setCSSAttribute(cssDoc, new CSSSelector(CSSSelectorTypes.CLASS, sectionName), backgroundAttColorName, convertColor(color));
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

    /**
     *
     * @param font to be inserted in css document
     * @param sectionName selector name
     * @return editor to edit page
     */
    public static PageEditor getFontEditor(Font font, String sectionName) {
        return template -> {
            final String fontFamilyAttributeName = "font-family";
            final String fontSizeAttributeName = "font-size";
            final String fontStyleAttributeName = "font-style";
            final String fontWeightAttributeName = "font-weight";

            CSSDocument cssDoc = template.getCSSDoc();

            try {
                CSSElement sectionElement = CSSDocumentHandler.getElement(cssDoc,
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

    /**
     *
     * @param position to be inserted in css document
     * @param sectionName selector name
     * @return editor to edit template
     */
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

    /**
     *
     * @param text to be inserted in html document
     * @param sectionName name of a selector
     * @return editor to edit page
     */
    public static PageEditor getTextEditor(String text, String sectionName) {
        return template -> {
            HTMLDocument htmlDocument = template.getHTMLDoc();

            try {
                TextTag textTag = (TextTag) HTMLDocumentHandler.getTag(htmlDocument, HTMLContainerTags.DIV,
                        Arrays.asList(new TagAttribute("class", sectionName)), 0);
                textTag.setText(text);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        };
    }
}
