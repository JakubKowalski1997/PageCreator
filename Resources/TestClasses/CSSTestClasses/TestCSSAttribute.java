package TestClasses.CSSTestClasses;

import CSSHandlerClasses.CSSAttribute;
import TestClasses.Test;
import javafx.util.Pair;

/**
 * Created by Konrad on 2017-07-13.
 */
public class TestCSSAttribute extends Test {
    TestCSSAttribute() {
        super("TestCSSAttribute");
    }

    private void testParsing() {
        String input = "width : 20px;";

        CSSAttribute correctAtt = new CSSAttribute("width", "20px");

        try {
            Pair<CSSAttribute, Integer> parsed = CSSAttribute.parseFromString(input, 0);
            if (!correctAtt.equals(parsed.getKey())) {
                reportError("Expected: " + correctAtt.toString() + " Got: " +
                        CSSAttribute.parseFromString(input, 0).toString());
            }
        }
        catch (Exception e) {
            reportError("Bad input string");
        }

    }

    public void test() {
        super.test();

        testParsing();

        reportResults();
    }

    public static void main(String[] args) {
        TestCSSAttribute test = new TestCSSAttribute();
        test.test();
    }
}
