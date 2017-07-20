package TestClasses.HTMLTestClasses;

/**
 * Created by Konrad on 2017-07-14.
 */

import HTMLHandlerClasses.HTMLTag;
import HTMLHandlerClasses.TagAttribute;
import TestClasses.Test;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class TestHTMLTag extends Test {
    TestHTMLTag() {
        super("TestHTMLTag");
    }

    private boolean compareAttributes(List<TagAttribute> list1, List<TagAttribute> list2) {
        if (list1.size() != list2.size())
            return false;

        for (int i = 0; i < list1.size(); ++i) {
            if (!list1.get(i).equals(list2.get(i)))
                return false;
        }

        return true;
    }

    private void testParsingAttributes() {
        ArrayList<String> inputs = new ArrayList<>();
        inputs.add("name=\"value\" content=\"value\" />");
        inputs.add("name=\"value\" content=\"value\" >");

        ArrayList<TagAttribute> correct = new ArrayList<>();
        correct.add(new TagAttribute("name", "value"));
        correct.add(new TagAttribute("content", "value"));

        for (int i = 0; i < inputs.size(); ++i) {
            try {
                Pair<List<TagAttribute>, Integer> parsed =
                        HTMLTag.parseAttributesFromString(inputs.get(i), 0);

                if (!compareAttributes(correct, parsed.getKey())) {
                    reportError("Expected: " + correct.toString() +
                    " Got: " + parsed.getKey().toString());
                }
            }
            catch (Exception e) {
                reportError(e.getMessage());
            }
        }
    }

    public void test() {
        super.test();

        testParsingAttributes();

        reportResults();
    }

    public static void main(String[] args) {
        TestHTMLTag test = new TestHTMLTag();
        test.test();
    }
}

