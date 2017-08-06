package TestClasses.CSSTestClasses;

import TestClasses.Test;
import CSSHandlerClasses.*;
import javafx.util.Pair;

/**
 * Created by Konrad on 2017-07-13.
 */
public class TestSelector extends Test {

    TestSelector() {
        super("TestSelector");
    }

    private void testParsing() {
        String input = "p {";

        try {
            CSSSelector correct = new CSSSelector(CSSSelectorTypes.TAG, "p");
            Pair<CSSSelector, Integer> parsed = CSSSelector.parseFromString(input, 0);

            if (!correct.equals(parsed.getKey())) {
                reportError("Expected: " + correct.toString() + " Got: " +
                parsed.toString());
            }
        }
        catch (Exception e) {
            reportError(e.getMessage());
        }

    }

    public void test() {
        super.test();

        String tagName = "p";

        try {
            CSSSelector selector = new CSSSelector(CSSSelectorTypes.TAG, tagName);
        }
        catch (Exception e) {
            reportError(tagName + " is a correct tag name");
        }

        String badTag = "bla";

        try {
            CSSSelector selector = new CSSSelector(CSSSelectorTypes.TAG, badTag);
            reportError(badTag + "is not a correct tag name");
        }
        catch (Exception e) {

        }


        testParsing();

        reportResults();
    }

    public static void main(String[] args) {
        TestSelector test = new TestSelector();
        test.test();
    }

}
