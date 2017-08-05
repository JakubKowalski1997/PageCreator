package TemplateEditor.TitleEditor;

import CSSHandlerClasses.*;
import HTMLHandlerClasses.*;
import TemplateEditor.PageEditor;
import TemplateEditor.BasicHTMLEditors;

import java.awt.Font;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Konrad on 2017-07-21.
 */
public class HTMLTitleEditor {

    private static final String sectionName = "Title";

    public static PageEditor getTextColorEditor(String color) {
            return BasicHTMLEditors.getTextColorEditor(color, sectionName);
    }

    public static PageEditor getBackgroundColorEditor(String color) {
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
