package TemplateHandlerClasses;

/**
 * Created by Konrad on 2017-07-20.
 */

import HTMLHandlerClasses.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TemplateHandler {
    private static TemplateHandler instance = new TemplateHandler();

    public static TemplateHandler getInstance() {
        return instance;
    }

    public TemplateHandler() {}

    private ContainerTag headSection;
    private PageTemplate pageTemplate;

    public void setHeadSection(ContainerTag head) {
        headSection = head;
    }

    private void setCharset() {
        try {
            for (int i = 0; i < headSection.getNumberOfChilds(); ++i) {
                HTMLTag nested = headSection.getNestedTag(i);
                List<TagAttribute> attributes = nested.getAttributes();

                for (TagAttribute attribute : attributes) {
                    if (attribute.name.equals("charset"))
                        pageTemplate.setCharset(attribute.value);
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setPageTemplate(PageTemplate template) {
        pageTemplate = template;

        /**
         * Just for debugging
         */
        if (headSection == null)
            System.out.println("Error. Null pointer in headSection");

        addCSSLink();
        setCharset();

        HTMLDocumentHandler handler = HTMLDocumentHandler.getInstance();
        HTMLDocument templateDoc = template.getHTMLDoc();
        try {
            handler.addTag(templateDoc, headSection, HTMLContainerTags.HTML, 0);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public PageTemplate getPageTemplate() {
        return pageTemplate;
    }

    public void addCSSLink() {
        ArrayList<TagAttribute> attributes = new ArrayList<>();
        Collections.addAll(attributes, new TagAttribute("rel", "stylesheet"),
                new TagAttribute("href", "main.css"));

        SelfClosingTag linkTag = new SelfClosingTag(HTMLSelfClosingTags.LINK, attributes);
        headSection.addNestedTag(linkTag);
    }
}
