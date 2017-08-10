package TestClasses.HTMLTestClasses;

import HTMLHandlerClasses.HTMLSelfClosingTags;
import HTMLHandlerClasses.SelfClosingTag;
import HTMLHandlerClasses.TagAttribute;
import javafx.util.Pair;
import junit.framework.TestCase;

import java.util.ArrayList;

/**
 * Created by Konrad on 2017-08-10.
 */
public class SelfClosingTagTest extends TestCase {

    public void testToString() throws Exception {
        ArrayList<SelfClosingTag> tags = new ArrayList<SelfClosingTag>();
        tags.add(new SelfClosingTag(HTMLSelfClosingTags.BR));
        ArrayList<TagAttribute> atts = new ArrayList<TagAttribute>();
        atts.add(new TagAttribute("name", "test"));
        tags.add(new SelfClosingTag(HTMLSelfClosingTags.BR, atts));

        ArrayList<String> expected = new ArrayList<String>();
        expected.add("<br/>");
        expected.add("<br name=\"test\"/>");

        for (int i = 0; i < tags.size(); ++i) {
            assertEquals(tags.get(i).toString(), expected.get(i));
        }
    }

    public void testParseFromString() throws Exception {
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
            Pair<SelfClosingTag, Integer> parsed = SelfClosingTag.parseFromString(inputs.get(i), 0);
            assertEquals(correct.get(i).toString(), parsed.getKey().toString());
        }
    }
}