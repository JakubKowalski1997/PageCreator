package TestClasses.HTMLTestClasses;

/**
 * Created by Konrad on 2017-07-14.
 */

import HTMLHandlerClasses.TagAttribute;
import TestClasses.Test;
import javafx.util.Pair;

public class TestTagAttribute extends Test {
    TestTagAttribute() {
        super("TestTagAttribute");
    }

    private void testParsing() {
        String input = "name=\"value\"";

        TagAttribute correct = new TagAttribute("name", "value");

        try {
            Pair<TagAttribute, Integer> parsed = TagAttribute.parseFromString(input, 0);

            if (!correct.equals(parsed.getKey())) {
                reportError("Expected: " + correct.toString() +
                " Got: " + parsed.getKey());
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
        TestTagAttribute test = new TestTagAttribute();
        test.test();
    }
}
