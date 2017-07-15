package TestClasses.CSSTestClasses;

import CSSHandlerClasses.CSSAttribute;
import CSSHandlerClasses.CSSElement;
import CSSHandlerClasses.CSSSelector;
import CSSHandlerClasses.CSSSelectorTypes;
import TestClasses.Test;
import javafx.util.Pair;

import java.util.ArrayList;

/**
 * Created by Konrad on 2017-07-13.
 */
public class TestElement extends Test {

    TestElement() {
        super("TestElement");
    }

    private ArrayList<CSSElement> getTestElements() {
        ArrayList<CSSElement> testElements = new ArrayList<>();
        try {
            CSSElement element1 = new CSSElement(new CSSSelector(CSSSelectorTypes.CLASS, "klasa"));
            element1.addAttribute(new CSSAttribute("background-color", "red"));
            testElements.add(element1);

            CSSElement element2 = new CSSElement(new CSSSelector(CSSSelectorTypes.TAG, "p"));
            element2.addAttribute(new CSSAttribute("width", "20px"));
            testElements.add(element2);

            CSSElement element3 = new CSSElement(new CSSSelector(CSSSelectorTypes.ID, "id"));
            element3.addAttribute(new CSSAttribute("height", "20px"));
            testElements.add(element3);
        }
        catch (Exception e) {
            reportError("Error while creating css selector");
        }

        return testElements;
    }

    private ArrayList<String> getCorrectStringReps() {
        ArrayList<String> correctReps = new ArrayList<>();

        correctReps.add(
                ".klasa {\n" +
                "\tbackground-color : red;\n" +
                "}"
        );

        correctReps.add(
                "p {\n" +
                "\twidth : 20px;\n" +
                "}"
        );

        correctReps.add(
                "#id {\n" +
                "\theight : 20px;\n" +
                "}"
        );

        return correctReps;
    }

    public void test() {
        super.test();

        ArrayList<CSSElement> testElements = getTestElements();
        ArrayList<String> correctReps = getCorrectStringReps();

        for (int i = 0; i < testElements.size(); ++i) {
            if (!testElements.get(i).toString().equals(correctReps.get(i))) {
                reportError("Expected: " + correctReps.get(i) + " Got: " + testElements.get(i).toString());
            }
        }

        testParsing();

        reportResults();
    }

    private void testParsing() {
        String input = "p {\n" +
                "\twidth : 20px;\n" +
                "\theight : 30px;\n" +
                "}";


        ArrayList<CSSAttribute> atts = new ArrayList<>();
        CSSAttribute att1 = new CSSAttribute("width", "20px");
        CSSAttribute att2 = new CSSAttribute("height", "30px");
        atts.add(att1);
        atts.add(att2);

        try {
            CSSElement correct = new CSSElement(new CSSSelector(CSSSelectorTypes.TAG, "p"), atts);
            Pair<CSSElement, Integer> parsed = CSSElement.parseFromString(input, 0);

            if (!correct.equals(parsed.getKey())) {
                reportError("Expected: " + correct.toString() + " Got: " +
                parsed.getKey().toString());
            }
        }
        catch (Exception e) {
            reportError(e.getMessage());
        }
    }

    public static void main(String[] args) {
        TestElement test = new TestElement();
        test.test();
    }
}
