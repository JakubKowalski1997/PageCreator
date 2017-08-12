package TemplateEditor.ContentEditor;

/**
 * Created by Konrad on 2017-08-05.
 */

import CSSHandlerClasses.*;
import HTMLHandlerClasses.*;
import TemplateEditor.PageEditor;
import TemplateEditor.BasicHTMLEditors;

import java.awt.Color;
import java.awt.Font;

import java.util.Arrays;

public class HTMLContentEditor {

    static final String sectionName = "Content";

    /**
     * While creating subpage this editor must be used first
     * @param charset to be added to head section
     * @param text  text to be inserted into html document
     * @return editor that creates subpage
     */
    public static PageEditor getHTMLContentEditor(String charset, String text) {
        return page -> {

            ContainerTag headTag = new ContainerTag(HTMLContainerTags.HEAD);
            headTag.addNestedTag(new SelfClosingTag(HTMLSelfClosingTags.META, Arrays.asList(new TagAttribute("charset", charset))));
            try {
                HTMLDocumentHandler.addTag(page.getHTMLDoc(), headTag, HTMLContainerTags.HTML);
                HTMLDocumentHandler.addTag(page.getHTMLDoc(), new ContainerTag(HTMLContainerTags.BODY), HTMLContainerTags.HTML);
                HTMLDocumentHandler.addTag(page.getHTMLDoc(), new TextTag(HTMLTextTags.P, Arrays.asList(new TagAttribute("class", "Content")), text), HTMLContainerTags.BODY);

                CSSElement htmlBodyContentElement = new CSSElement(
                        Arrays.asList(
                                new CSSSelector(CSSSelectorTypes.TAG, "html"),
                                new CSSSelector(CSSSelectorTypes.TAG, "body"),
                                new CSSSelector(CSSSelectorTypes.CLASS, "Content")
                        ),
                        Arrays.asList(
                                new CSSAttribute("margin", "0px"),
                                new CSSAttribute("height", "100%")
                        )
                );
                CSSDocumentHandler.addElement(page.getCSSDoc(), htmlBodyContentElement);
                CSSDocumentHandler.addElement(page.getCSSDoc(), new CSSElement(new CSSSelector(CSSSelectorTypes.CLASS, "Content")));
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        };
    }

    /**
     *
     * @return editor that converts css document to style tag and inserts it into html document
     */
    public static PageEditor getSubPageStyleEditor() {
        return page -> {
            HTMLDocument htmlDocument = page.getHTMLDoc();
            CSSDocument cssDocument = page.getCSSDoc();
            TextTag styleTag = new TextTag(HTMLTextTags.STYLE, '\n' + cssDocument.toString() + '\n');
            try {
                HTMLDocumentHandler.addTag(htmlDocument, styleTag, HTMLContainerTags.HEAD);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        };
    }

    public static PageEditor getTextColorEditor(Color color) {
        return BasicHTMLEditors.getTextColorEditor(color, sectionName);
    }

    public static PageEditor getBackgroundColorEditor(Color color) {
        return BasicHTMLEditors.getBackgroundColorEditor(color, sectionName);
    }

    public static PageEditor getFontEditor(Font font) {
        return BasicHTMLEditors.getFontEditor(font, sectionName);
    }

    public static PageEditor getPositionEditor(String position) {
        return BasicHTMLEditors.getPositionEditor(position, sectionName);
    }

    public static PageEditor getTextEditor(String text) {
        return BasicHTMLEditors.getTextEditor(text, sectionName);
    }
}
