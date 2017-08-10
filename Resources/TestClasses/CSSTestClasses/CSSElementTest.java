package TestClasses.CSSTestClasses;

import CSSHandlerClasses.CSSAttribute;
import CSSHandlerClasses.CSSElement;
import CSSHandlerClasses.CSSSelector;
import CSSHandlerClasses.CSSSelectorTypes;
import javafx.util.Pair;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * Created by Konrad on 2017-08-10.
 */
public class CSSElementTest extends TestCase {

    private ArrayList<CSSElement> getTestElements() throws Exception {
        ArrayList<CSSElement> testElements = new ArrayList<>();

        CSSElement element1 = new CSSElement(new CSSSelector(CSSSelectorTypes.CLASS, "klasa"));
        element1.addAttribute(new CSSAttribute("background-color", "red"));
        testElements.add(element1);

        CSSElement element2 = new CSSElement(new CSSSelector(CSSSelectorTypes.TAG, "p"));
        element2.addAttribute(new CSSAttribute("width", "20px"));
        testElements.add(element2);

        CSSElement element3 = new CSSElement(new CSSSelector(CSSSelectorTypes.ID, "id"));
        element3.addAttribute(new CSSAttribute("height", "20px"));
        testElements.add(element3);

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

    public void testAddAttribute() throws Exception {
        CSSElement testElement = new CSSElement(new CSSSelector(CSSSelectorTypes.CLASS, "container"));

        ArrayList<CSSAttribute> testAttributes = new ArrayList<>();
        Collections.addAll(testAttributes,
                new CSSAttribute("width", "20px"), new CSSAttribute("margin", "0"));

        testElement.addAttribute(testAttributes.get(0));
        testElement.addAttribute(testAttributes.get(1));

        for (int i = 0; i < testAttributes.size(); ++i) {
            assertEquals(testAttributes.get(i), testElement.getAttribute(i));
        }
    }

    public void testEraseAttribute() throws Exception {
        CSSElement testElement = new CSSElement(new CSSSelector(CSSSelectorTypes.CLASS, "container"));

        ArrayList<CSSAttribute> testAttributes = new ArrayList<>();
        Collections.addAll(testAttributes,
                new CSSAttribute("width", "20px"), new CSSAttribute("margin", "0"));

        testElement.addAttribute(new CSSAttribute("color", "blue"));
        testElement.addAttribute(testAttributes.get(0));
        testElement.addAttribute(testAttributes.get(1));

        testElement.eraseAttribute(0);

        for (int i = 0; i < testAttributes.size(); ++i) {
            assertEquals(testAttributes.get(i), testElement.getAttribute(i));
        }
    }

    public void testGetAttribute() throws Exception {
        CSSElement testElement = new CSSElement(new CSSSelector(CSSSelectorTypes.CLASS, "container"));

        CSSAttribute attribute = new CSSAttribute("width", "20px");
        testElement.addAttribute(attribute);

        assertEquals(attribute, testElement.getAttribute(attribute.name));
    }

    public void testToString() throws Exception {
        ArrayList<CSSElement> testElements = getTestElements();
        ArrayList<String> correctReps = getCorrectStringReps();

        for (int i = 0; i < testElements.size(); ++i) {
            assertEquals(testElements.get(i).toString(), correctReps.get(i));
        }
    }

    public void testParseFromString() throws Exception {
        String input = "p, body {\n" +
                "\twidth : 20px;\n" +
                "\theight : 30px;\n" +
                "}";


        ArrayList<CSSAttribute> atts = new ArrayList<>();
        CSSAttribute att1 = new CSSAttribute("width", "20px");
        CSSAttribute att2 = new CSSAttribute("height", "30px");
        atts.add(att1);
        atts.add(att2);

        CSSElement correct = new CSSElement(
                Arrays.asList(new CSSSelector(CSSSelectorTypes.TAG, "p"), new CSSSelector(CSSSelectorTypes.TAG, "body")),
                atts);
        Pair<CSSElement, Integer> parsed = CSSElement.parseFromString(input, 0);

        assertEquals(parsed.getKey(), correct);
    }
}