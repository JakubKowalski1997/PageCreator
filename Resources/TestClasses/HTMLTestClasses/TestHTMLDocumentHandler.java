package TestClasses.HTMLTestClasses;

import HTMLHandlerClasses.*;
import TestClasses.Test;

import java.util.ArrayList;


/**
 * Created by Konrad on 2017-07-05.
 */
public class TestHTMLDocumentHandler extends Test {

    public TestHTMLDocumentHandler() {
        super("TestHTMLDocumentHandler");
    }

    private void test1() {
        HTMLDocument doc = new HTMLDocument();
        HTMLDocumentHandler docHandler = HTMLDocumentHandler.getInstance();

        HTMLTag headTag = new ContainerTag(HTMLContainerTags.HEAD);
        HTMLTag bodyTag = new ContainerTag(HTMLContainerTags.BODY);
        HTMLTag divTag = new ContainerTag(HTMLContainerTags.DIV);

        try {
            docHandler.addTag(doc, headTag, HTMLContainerTags.HTML, new ArrayList<TagAttribute>());
            //System.out.println("tak");
            docHandler.addTag(doc, bodyTag, HTMLContainerTags.HEAD, new ArrayList<TagAttribute>());
            docHandler.addTag(doc, divTag, (ContainerTag) bodyTag);
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

        if (!gotRep.equals(expectedRep)) {
            reportError("Expected: " + expectedRep +
            " Got: " + gotRep);
        }
    }

    private void test2() {
        HTMLDocument doc = new HTMLDocument();
        HTMLDocumentHandler docHandler = HTMLDocumentHandler.getInstance();

        HTMLTag headTag = new ContainerTag(HTMLContainerTags.HEAD);
        HTMLTag bodyTag = new ContainerTag(HTMLContainerTags.BODY);
        HTMLTag divTag = new ContainerTag(HTMLContainerTags.DIV);

        try {
            docHandler.addTag(doc, headTag, HTMLContainerTags.HTML, new ArrayList<TagAttribute>());
            //System.out.println("tak");
            docHandler.addTag(doc, bodyTag, HTMLContainerTags.HEAD, new ArrayList<TagAttribute>());
            docHandler.addTag(doc, divTag, (ContainerTag) bodyTag);
            docHandler.eraseTag(doc, bodyTag);
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

        if (!gotRep.equals(expectedRep)) {
            reportError("Expected: " + expectedRep +
                    " Got: " + gotRep);
        }
    }

    public void test() {
        super.test();

        test1();
        test2();

        reportResults();
    }

    public static void main(String[] args) {
        TestHTMLDocumentHandler test = new TestHTMLDocumentHandler();
        test.test();
    }
}
