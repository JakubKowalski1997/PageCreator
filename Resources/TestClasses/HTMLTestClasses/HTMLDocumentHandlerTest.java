package TestClasses.HTMLTestClasses;

import HTMLHandlerClasses.*;
import junit.framework.TestCase;

import java.util.ArrayList;

/**
 * Created by Konrad on 2017-08-10.
 */
public class HTMLDocumentHandlerTest extends TestCase {

    public void test1() {
        HTMLDocument doc = new HTMLDocument();

        HTMLTag headTag = new ContainerTag(HTMLContainerTags.HEAD);
        HTMLTag bodyTag = new ContainerTag(HTMLContainerTags.BODY);
        HTMLTag divTag = new ContainerTag(HTMLContainerTags.DIV);

        try {
            HTMLDocumentHandler.addTag(doc, headTag, HTMLContainerTags.HTML, new ArrayList<TagAttribute>());
            //System.out.println("tak");
            HTMLDocumentHandler.addTag(doc, bodyTag, HTMLContainerTags.HEAD, new ArrayList<TagAttribute>());
            HTMLDocumentHandler.addTag(doc, divTag, (ContainerTag) bodyTag);
        }
        catch(Exception e) {
            System.out.println(e.getCause());
        }

        String expectedRep = "<!DOCTYPE html>\n" +
                "\n" +
                "<html>\n" +
                "\t<head>\n" +
                "\t\t<body>\n" +
                "\t\t\t<div></div>\n" +
                "\t\t</body>\n" +
                "\t</head>\n" +
                "</html>";

        String gotRep = doc.toString();

        assertEquals(expectedRep, gotRep);
    }

    public void test2() {
        HTMLDocument doc = new HTMLDocument();

        HTMLTag headTag = new ContainerTag(HTMLContainerTags.HEAD);
        HTMLTag bodyTag = new ContainerTag(HTMLContainerTags.BODY);
        HTMLTag divTag = new ContainerTag(HTMLContainerTags.DIV);

        try {
            HTMLDocumentHandler.addTag(doc, headTag, HTMLContainerTags.HTML, new ArrayList<TagAttribute>());
            //System.out.println("tak");
            HTMLDocumentHandler.addTag(doc, bodyTag, HTMLContainerTags.HEAD, new ArrayList<TagAttribute>());
            HTMLDocumentHandler.addTag(doc, divTag, (ContainerTag) bodyTag);
            HTMLDocumentHandler.eraseTag(doc, bodyTag);
        }
        catch(Exception e) {
            System.out.println(e.getCause());
        }

        String expectedRep = "<!DOCTYPE html>\n" +
                "\n" +
                "<html>\n" +
                "\t<head>" +
                "</head>\n" +
                "</html>";

        String gotRep = doc.toString();

        assertEquals(expectedRep, gotRep);
    }
}