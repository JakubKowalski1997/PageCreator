package TestClasses;

import HTMLHandlerClasses.HTMLSelfClosingTags;
import HTMLHandlerClasses.SelfClosingTag;
import HTMLHandlerClasses.TagAttribute;

import java.util.ArrayList;

/**
 * Created by Konrad on 2017-07-04.
 */
public class TestSelfClosingTag extends Test {

    public TestSelfClosingTag() {
        super("TestSelfClosingTag");
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

        reportResults();
    }
}
