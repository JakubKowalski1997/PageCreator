package TestClasses.CSSTestClasses;

import CSSHandlerClasses.*;
import junit.framework.TestCase;

import java.util.ArrayList;

/**
 * Created by Konrad on 2017-08-10.
 */
public class CSSDocumentTest extends TestCase {

    public void testToString() throws Exception {
        CSSDocument cssDocument = new CSSDocument();

        CSSElement element1 = new CSSElement(new CSSSelector(CSSSelectorTypes.CLASS, "container"));
        element1.addAttribute(new CSSAttribute("width", "20%"));

        CSSElement element2 = new CSSElement((new CSSSelector(CSSSelectorTypes.TAG, "p")));
        element2.addAttribute(new CSSAttribute("margin", "0px"));

        CSSDocumentHandler.addElement(cssDocument, element1);
        CSSDocumentHandler.addElement(cssDocument, element2);

        String correctOutput = ".container {\n" +
                "\twidth : 20%;\n" +
                "}\n" +
                "\n" +
                "p {\n" +
                "\tmargin : 0px;\n" +
                "}";

        assertEquals(correctOutput, cssDocument.toString());
    }

    public void testParseFromString() throws Exception {
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
        CSSElement element1 = new CSSElement(new CSSSelector(CSSSelectorTypes.CLASS, "class"), atts1);
        CSSElement element2 = new CSSElement(new CSSSelector(CSSSelectorTypes.TAG, "p"), atts2);

        CSSDocument correct = new CSSDocument();
        CSSDocumentHandler.addElement(correct, element1);
        CSSDocumentHandler.addElement(correct, element2);

        CSSDocument parsed = CSSDocument.parseFromString(input);

        assertEquals(parsed, correct);
    }
}