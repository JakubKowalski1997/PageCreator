package TestClasses.DocumentIOTestClasses;

/**
 * Created by Konrad on 2017-08-10.
 */

import junit.framework.TestSuite;

public class DocumentIOTestSuite {
    public static void addTests(TestSuite suite) {
        suite.addTestSuite(DocumentReaderTest.class);
    }
}
