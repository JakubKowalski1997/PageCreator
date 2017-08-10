package TestClasses.HTMLTestClasses;

import HTMLHandlerClasses.TagAttribute;
import javafx.util.Pair;
import junit.framework.TestCase;

/**
 * Created by Konrad on 2017-08-10.
 */
public class TagAttributeTest extends TestCase {

    public void testParseFromString() throws Exception {
        String input = "name=\"value\"";

        TagAttribute correct = new TagAttribute("name", "value");

        Pair<TagAttribute, Integer> parsed = TagAttribute.parseFromString(input, 0);

        assertEquals(correct.toString(), parsed.getKey().toString());
    }
}