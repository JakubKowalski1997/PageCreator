package TestClasses.CSSTestClasses;

import CSSHandlerClasses.CSSAttribute;
import javafx.util.Pair;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Created by Konrad on 2017-08-10.
 */
public class CSSAttributeTest extends TestCase {

    public void testToString() throws Exception {
        CSSAttribute testAttribute = new CSSAttribute("width", "20px");
        String correctStringRep = "width : 20px;";
        assertEquals(testAttribute.toString(), correctStringRep);
    }

    public void testParseFromString() throws Exception {
        String input = "width : 20px;";
        CSSAttribute correctAtt = new CSSAttribute("width", "20px");
        Pair<CSSAttribute, Integer> parsed = CSSAttribute.parseFromString(input, 0);

        assertEquals(parsed.getKey(), correctAtt);
    }

    public static TestSuite suite() {
        return new TestSuite(CSSAttributeTest.class);
    }
}