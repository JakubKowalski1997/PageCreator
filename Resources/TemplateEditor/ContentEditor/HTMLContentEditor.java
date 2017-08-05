package TemplateEditor.ContentEditor;

/**
 * Created by Konrad on 2017-08-05.
 */

import CSSHandlerClasses.*;
import HTMLHandlerClasses.*;
import TemplateEditor.PageEditor;
import TemplateEditor.BasicHTMLEditors;

import javax.xml.soap.Text;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Arrays;

public class HTMLContentEditor {

    static final String sectionName = "Content";

    public static PageEditor getHTMLContentEditor(String charset, String text) {
        return page -> {
            HTMLDocumentHandler htmlHandler = HTMLDocumentHandler.getInstance();
            CSSDocumentHandler cssHandler = CSSDocumentHandler.getInstance();

            ContainerTag headTag = new ContainerTag(HTMLContainerTags.HEAD);
            headTag.addNestedTag(new SelfClosingTag(HTMLSelfClosingTags.META, Arrays.asList(new TagAttribute("charset", charset))));
            try {
            htmlHandler.addTag(page.getHTMLDoc(), headTag, HTMLContainerTags.HTML);
            htmlHandler.addTag(page.getHTMLDoc(), new ContainerTag(HTMLContainerTags.BODY), HTMLContainerTags.HTML);
            htmlHandler.addTag(page.getHTMLDoc(), new TextTag(HTMLTextTags.P, Arrays.asList(new TagAttribute("class", "Content")), text), HTMLContainerTags.BODY);
                cssHandler.addElement(page.getCSSDoc(), new CSSElement(new CSSSelector(CSSSelectorTypes.CLASS, "Content")));
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        };
    }

    public static PageEditor getSubPageStyleEditor() {
        return page -> {
            HTMLDocument htmlDocument = page.getHTMLDoc();
            HTMLDocumentHandler htmlHandler = HTMLDocumentHandler.getInstance();
            CSSDocument cssDocument = page.getCSSDoc();
            TextTag styleTag = new TextTag(HTMLTextTags.STYLE, '\n' + cssDocument.toString() + '\n');
            try {
                htmlHandler.addTag(htmlDocument, styleTag, HTMLContainerTags.HEAD);
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
