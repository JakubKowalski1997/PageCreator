package TestClasses.TestContentEditorClasses;

/**
 * Created by Konrad on 2017-08-10.
 */

import junit.framework.TestSuite;

public class ContentEditorTestSuite {
    public static void addTests(TestSuite suite) {
        suite.addTestSuite(HTMLContentEditorTest.class);
    }
}
