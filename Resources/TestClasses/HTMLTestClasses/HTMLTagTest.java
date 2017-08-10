package TestClasses.HTMLTestClasses;

import HTMLHandlerClasses.HTMLTag;
import HTMLHandlerClasses.TagAttribute;
import javafx.util.Pair;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Konrad on 2017-08-10.
 */
public class HTMLTagTest extends TestCase {

    private void compareAttributes(List<TagAttribute> list1, List<TagAttribute> list2) {
        for (int i = 0; i < list1.size(); ++i) {
            assertEquals(list1.get(i), list2.get(i));
        }
    }


    public void testParseAttributesFromString() throws Exception {
        ArrayList<String> inputs = new ArrayList<>();
        inputs.add("name=\"value\" content=\"value\" />");
        inputs.add("name=\"value\" content=\"value\" >");

        ArrayList<TagAttribute> correct = new ArrayList<>();
        correct.add(new TagAttribute("name", "value"));
        correct.add(new TagAttribute("content", "value"));

        for (int i = 0; i < inputs.size(); ++i) {
            Pair<List<TagAttribute>, Integer> parsed =
                    HTMLTag.parseAttributesFromString(inputs.get(i), 0);

            compareAttributes(correct, parsed.getKey());
        }
    }
}