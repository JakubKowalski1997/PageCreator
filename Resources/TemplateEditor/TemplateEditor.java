package TemplateEditor;

import CSSHandlerClasses.*;
import TemplateHandlerClasses.PageTemplate;

import java.util.ArrayList;

/**
 * Created by Konrad on 2017-07-21.
 */
public interface TemplateEditor {
    void edit(PageTemplate template);

    static void setCSSAttribute(PageTemplate template, String sectionName, CSSAttribute newAttribute) {
            CSSDocument cssDoc = template.getCSSDoc();
            CSSDocumentHandler handler = CSSDocumentHandler.getInstance();

            try {
                CSSElement titleElement = handler.getElement(cssDoc,
                        new CSSSelector(CSSSelectorTypes.CLASS, sectionName));

                CSSAttribute colorAttribute = titleElement.getAttribute(newAttribute.name);
                if (colorAttribute != null) {
                    colorAttribute.value = newAttribute.value;
                }
                else {
                    titleElement.addAttribute(new CSSAttribute(newAttribute.name, newAttribute.value));
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
    }

    static void setCSSAttributes(PageTemplate template, String sectionName, ArrayList<CSSAttribute> newAttributes) {
        CSSDocument cssDoc = template.getCSSDoc();
        CSSDocumentHandler handler = CSSDocumentHandler.getInstance();

        try {
            CSSElement titleElement = handler.getElement(cssDoc,
                    new CSSSelector(CSSSelectorTypes.CLASS, sectionName));

            for (CSSAttribute newAttribute : newAttributes) {
                CSSAttribute colorAttribute = titleElement.getAttribute(newAttribute.name);
                if (colorAttribute != null) {
                    colorAttribute.value = newAttribute.value;
                } else {
                    titleElement.addAttribute(new CSSAttribute(newAttribute.name, newAttribute.value));
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
