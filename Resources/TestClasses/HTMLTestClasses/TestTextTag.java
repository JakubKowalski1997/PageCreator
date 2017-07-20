package TestClasses.HTMLTestClasses;

/**
 * Created by Konrad on 2017-07-04.
 */

import HTMLHandlerClasses.*;
import TestClasses.Test;
import javafx.util.Pair;

import java.util.ArrayList;

public class TestTextTag extends Test {

    public TestTextTag() {
        super("TestTextTag");
    }

    private void testParsing() {
        String input = "<p class=\"class\"> text </p>";

        ArrayList<TagAttribute> atts = new ArrayList<>();
        atts.add(new TagAttribute("class", "class"));
        TextTag correct = new TextTag(HTMLTextTags.P, atts, " text ");

        try {
            Pair<TextTag, Integer> parsed = TextTag.parseFromString(input, 0);
            if (!correct.toString().equals(parsed.getKey().toString())) {
                reportError("Expected: " + correct.toString() + " Got: " +
                parsed.getKey().toString());
            }
        }
        catch (Exception e) {
            reportError(e.getMessage());
        }
    }

    public void test() {
        super.test();

        TextTag tag = new TextTag(HTMLTextTags.P, "Test text");

        String expectedRepr = "<p>Test text</p>";

        if (!tag.toString().equals(expectedRepr)) {
            String msg = "Expected: " + expectedRepr +
                    "Got: " + tag.toString();
            reportError(msg);
        }

        testParsing();

        reportResults();
    }

    public static void main(String[] args) {
        TestTextTag test = new TestTextTag();
        test.test();
    }
}
