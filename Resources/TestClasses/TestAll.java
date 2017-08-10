package TestClasses;

/**
 * Created by Konrad on 2017-08-10.
 */

import TestClasses.CSSTestClasses.CSSTestSuite;
import TestClasses.HTMLTestClasses.HTMLTestSuite;
import TestClasses.TestContentEditorClasses.ContentEditorTestSuite;
import TestClasses.TestMenuEditorClasses.MenuEditorTestSuite;
import TestClasses.TestTitleEditorClasses.TitleEditorTestSuite;
import junit.framework.TestSuite;

public class TestAll {
    public static void main(String[] args) {
        TestSuite tests = new TestSuite();

        CSSTestSuite.addTests(tests);
        HTMLTestSuite.addTests(tests);
        ContentEditorTestSuite.addTests(tests);
        MenuEditorTestSuite.addTests(tests);
        TitleEditorTestSuite.addTests(tests);

        junit.textui.TestRunner.run(tests);
    }
}
