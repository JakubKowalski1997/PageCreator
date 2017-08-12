package TemplateEditor;

import HTMLHandlerClasses.*;
import Utils.Page;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Konrad on 2017-08-05.
 */
public class IframeEditor {
    /**
     *
     * @param src to be add as a src attribute to iframe
     * @return editor to edit page
     */
    public static PageEditor getEditor(String src) {
        return page -> {
            HTMLDocument htmlDocument = page.getHTMLDoc();

            try {
                TextTag iframe = (TextTag) HTMLDocumentHandler.getTag(htmlDocument, HTMLContainerTags.DIV, Arrays.asList(new TagAttribute("class", "Content")), 0);
                iframe.addAttribute(new TagAttribute("src", src));
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        };
    }
}
