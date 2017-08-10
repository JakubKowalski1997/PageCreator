package TestClasses.CSSTestClasses;

import CSSHandlerClasses.CSSSelector;
import CSSHandlerClasses.CSSSelectorTypes;
import javafx.util.Pair;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Konrad on 2017-08-10.
 */
public class CSSSelectorTest extends TestCase {

    public void testToString() throws Exception {

        ArrayList<Boolean> expected = new ArrayList<>();
        Collections.addAll(expected, false, true);

        ArrayList<Boolean> got = new ArrayList<>();
        Collections.addAll(got, false, false);

        String tagName = "p";
        try {
            CSSSelector selector = new CSSSelector(CSSSelectorTypes.TAG, tagName);
        }
        catch (Exception e) {
            got.set(0, true);
        }

        String badTag = "bla";
        try {
            CSSSelector selector = new CSSSelector(CSSSelectorTypes.TAG, badTag);
        }
        catch (Exception e) {
            got.set(1, true);
        }

        for (int i = 0; i < expected.size(); ++i) {
            assertEquals(expected.get(i), got.get(i));
        }
    }

    public void testParseFromString() throws Exception {
        String input = "p {";

        CSSSelector correct = new CSSSelector(CSSSelectorTypes.TAG, "p");
        Pair<CSSSelector, Integer> parsed = CSSSelector.parseFromString(input, 0);

        assertEquals(correct, parsed.getKey());
    }
}