package TestClasses.HTMLTestClasses;

import HTMLHandlerClasses.HTMLSelfClosingTags;
import HTMLHandlerClasses.SelfClosingTag;
import HTMLHandlerClasses.TagAttribute;
import TestClasses.Test;
import javafx.util.Pair;

import javax.swing.text.html.HTML;
import java.util.ArrayList;

/**
 * Created by Konrad on 2017-07-04.
 */
public class TestSelfClosingTag extends Test {

    public TestSelfClosingTag() {
        super("TestSelfClosingTag");
    }

    private void testParsing() {
        ArrayList<String> inputs = new ArrayList<>();
        inputs.add("<img title=\"something\" src=\"something\"/>");
        inputs.add("<meta title=\"something\" src=\"something\">");

        ArrayList<SelfClosingTag> correct = new ArrayList<>();
        ArrayList<TagAttribute> atts1 = new ArrayList<>();
        atts1.add(new TagAttribute("title", "something"));
        atts1.add(new TagAttribute("src", "something"));
        correct.add(new SelfClosingTag(HTMLSelfClosingTags.IMG, atts1));

        ArrayList<TagAttribute> atts2 = new ArrayList<>();
        atts2.add(new TagAttribute("title", "something"));
        atts2.add(new TagAttribute("src", "something"));
        correct.add(new SelfClosingTag(HTMLSelfClosingTags.META, atts2));

        for (int i = 0; i < inputs.size(); ++i) {
            try {
                Pair<SelfClosingTag, Integer> parsed = SelfClosingTag.parseFromString(inputs.get(i), 0);

                if (!correct.get(i).toString().equals(parsed.getKey().toString())) {
                    reportError("Expected: " + correct.get(i).toString() + " Got: " +
                    parsed.getKey().toString());
                }
            }
            catch (Exception e) {
                reportError(e.getMessage());
            }
        }
    }

    public void test() {
        super.test();

        ArrayList<SelfClosingTag> tags =  new ArrayList<SelfClosingTag>();
        tags.add(new SelfClosingTag(HTMLSelfClosingTags.BR));
        ArrayList<TagAttribute> atts = new ArrayList<TagAttribute>();
        atts.add(new TagAttribute("name","test"));
        tags.add(new SelfClosingTag(HTMLSelfClosingTags.BR, atts));

        ArrayList<String> expected = new ArrayList<String>();
        expected.add("<br/>");
        expected.add("<br name=\"test\"/>");

        for (int i = 0; i < tags.size(); ++i) {
            if (!tags.get(i).toString().equals(expected.get(i))) {
                String msg = "Expected: " + expected.get(i) +
                        " Got: " + tags.get(i).toString();
                reportError(msg);
            }
        }

        testParsing();

        reportResults();
    }

    public static void main(String[] args) {
        TestSelfClosingTag test = new TestSelfClosingTag();
        test.test();
    }
}
