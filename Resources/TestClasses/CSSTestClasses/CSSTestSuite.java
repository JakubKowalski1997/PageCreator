package TestClasses.CSSTestClasses;

/**
 * Created by Konrad on 2017-08-10.
 */

import junit.framework.TestSuite;

public class CSSTestSuite {

    public static void addTests(TestSuite suite) {
        suite.addTestSuite(CSSAttributeTest.class);
        suite.addTestSuite(CSSDocumentTest.class);
        suite.addTestSuite(CSSElementTest.class);
        suite.addTestSuite(CSSSelectorTest.class);
    }
}
