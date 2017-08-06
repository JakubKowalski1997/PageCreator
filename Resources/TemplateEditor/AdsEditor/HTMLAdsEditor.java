package TemplateEditor.AdsEditor;

import TemplateEditor.BasicHTMLEditors;
import TemplateEditor.PageEditor;
import Utils.Page;

import java.awt.Color;
import java.awt.Font;

/**
 * Created by Konrad on 2017-08-06.
 */
public class HTMLAdsEditor {
    static final String sectionName = "Ads";

    public static PageEditor getTextColorEditor(Color color) {
        return BasicHTMLEditors.getTextColorEditor(color, sectionName);
    }

    public static PageEditor getBackgroundColorEditor(Color color) {
        return BasicHTMLEditors.getBackgroundColorEditor(color, sectionName);
    }

    public static PageEditor getPositionEditor(String position) {
        return BasicHTMLEditors.getPositionEditor(position, sectionName);
    }

    public static PageEditor getFontEditor(Font font) {
        return BasicHTMLEditors.getFontEditor(font, sectionName);
    }

    public static PageEditor getTextEditor(String text) {
        return BasicHTMLEditors.getTextEditor(text, sectionName);
    }
}
