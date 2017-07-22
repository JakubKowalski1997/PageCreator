package TestClasses.CSSTestClasses;

import CSSHandlerClasses.CSSAttribute;
import TestClasses.Test;
import javafx.util.Pair;

import java.util.Arrays;

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

        String input2 = "font-style : bold, italic;";

        CSSAttribute correctAtt2 = new CSSAttribute("font-style", Arrays.asList("bold", "italic"));

        try {
            Pair<CSSAttribute, Integer> parsed = CSSAttribute.parseFromString(input2, 0);
            if (!correctAtt2.toString().equals(parsed.getKey().toString())) {
                reportError("Expected: " + correctAtt2.toString() + " Got: " +
                        parsed.getKey().toString());
            }
        }
        catch (Exception e) {
            reportError(e.getMessage());
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
