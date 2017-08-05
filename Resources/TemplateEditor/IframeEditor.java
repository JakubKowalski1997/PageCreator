package TemplateEditor;

import HTMLHandlerClasses.*;
import Utils.Page;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Konrad on 2017-08-05.
 */
public class IframeEditor {
    public static PageEditor getEditor(String src) {
        return page -> {
            HTMLDocument htmlDocument = page.getHTMLDoc();
            HTMLDocumentHandler handler = HTMLDocumentHandler.getInstance();

            try {
                TextTag iframe = (TextTag) handler.getTag(htmlDocument, HTMLContainerTags.DIV, Arrays.asList(new TagAttribute("class", "Content")), 0);
                iframe.addAttribute(new TagAttribute("src", src));
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        };
    }
}
