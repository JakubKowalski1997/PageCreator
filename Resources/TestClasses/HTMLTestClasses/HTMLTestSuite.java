package TestClasses.HTMLTestClasses;

import HTMLHandlerClasses.ContainerTag;
import HTMLHandlerClasses.SelfClosingTag;
import junit.framework.TestSuite;

/**
 * Created by Konrad on 2017-08-10.
 */

import junit.framework.TestSuite;

public class HTMLTestSuite {
    public static void addTests(TestSuite suite) {
        suite.addTestSuite(ContainerTagTest.class);
        suite.addTestSuite(HTMLDocumentHandlerTest.class);
        suite.addTestSuite(HTMLDocumentTest.class);
        suite.addTestSuite(HTMLTagTest.class);
        suite.addTestSuite(SelfClosingTagTest.class);
        suite.addTestSuite(TagAttributeTest.class);
        suite.addTestSuite(TextTagTest.class);
    }
}
