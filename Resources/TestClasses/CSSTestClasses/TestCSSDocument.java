package TestClasses.CSSTestClasses;

/**
 * Created by Konrad on 2017-07-13.
 */

import CSSHandlerClasses.*;
import TestClasses.Test;

import java.util.ArrayList;

public class TestCSSDocument extends Test {
    TestCSSDocument() {
        super("TestCSSDocument");
    }

    public void testParsing() {
        String input = ".class {\n" +
                "\twidth : 20px;\n"+
                "\t}\n" +
                "p {\n" +
                "\theight : 30px;\n" +
                "}";

        ArrayList<CSSAttribute> atts1 = new ArrayList<>();
        atts1.add(new CSSAttribute("width", "20px"));

        ArrayList<CSSAttribute> atts2 = new ArrayList<>();
        atts2.add(new CSSAttribute("height", "30px"));
        try {
            CSSElement element1 = new CSSElement(new CSSSelector(CSSSelectorTypes.CLASS, "class"), atts1);
            CSSElement element2 = new CSSElement(new CSSSelector(CSSSelectorTypes.TAG, "p"), atts2);

            CSSDocument correct = new CSSDocument();
            CSSDocumentHandler handler = CSSDocumentHandler.getInstance();
            handler.addElement(correct, element1);
            handler.addElement(correct, element2);

            CSSDocument parsed = CSSDocument.parseFromString(input);

            if (!correct.equals(parsed)) {
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

        testParsing();

        reportResults();
    }

    public static void main(String[] args) {
        TestCSSDocument test = new TestCSSDocument();
        test.test();
    }
}
