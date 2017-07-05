package TestClasses;

/**
 * Created by Konrad on 2017-07-04.
 */

import HTMLHandlerClasses.*;

import java.util.ArrayList;

public class TestTextTag extends Test {

    public TestTextTag() {
        super("TestTextTag");
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

        reportResults();
    }


}
