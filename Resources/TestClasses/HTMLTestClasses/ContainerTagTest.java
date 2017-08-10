package TestClasses.HTMLTestClasses;

import HTMLHandlerClasses.*;
import javafx.util.Pair;
import junit.framework.TestCase;

import java.util.ArrayList;

/**
 * Created by Konrad on 2017-08-10.
 */
public class ContainerTagTest extends TestCase {

    private ArrayList<ContainerTag> getTestTags() {
        ArrayList<ContainerTag> tags = new ArrayList<ContainerTag>();
        tags.add(new ContainerTag(HTMLContainerTags.HTML));
        ContainerTag body = new ContainerTag(HTMLContainerTags.BODY);
        ContainerTag div = new ContainerTag(HTMLContainerTags.DIV);
        TextTag text = new TextTag(HTMLTextTags.P, "text");
        div.addNestedTag(text);
        body.addNestedTag(div);
        tags.add(body);
        return tags;
    }

    private ArrayList<String> getExpectedRepresentations() {
        ArrayList<String> expected = new ArrayList<String>();
        expected.add("<html></html>");
        expected.add(
                "<body>\n" +
                        "\t<div>\n" +
                        "\t\t<p>text</p>\n" +
                        "\t</div>\n" +
                        "</body>"
        );
        return expected;
    }

    public void testToString() throws Exception {
        ArrayList<ContainerTag> tags = getTestTags();
        ArrayList<String> expectedReps = getExpectedRepresentations();

        for (int i = 0; i < tags.size(); ++i) {
            assertEquals(expectedReps.get(i), tags.get(i).toString());
        }
    }

    public void testParseFromString() throws Exception {
        String input = "<body>\n" +
                "\t<div>\n" +
                "\t\t<p>text</p>\n" +
                "\t</div>\n" +
                "</body>";

        ContainerTag body = new ContainerTag(HTMLContainerTags.BODY);
        ContainerTag div = new ContainerTag(HTMLContainerTags.DIV);
        TextTag text = new TextTag(HTMLTextTags.P, "text");
        div.addNestedTag(text);
        body.addNestedTag(div);

        Pair<ContainerTag, Integer> parsed = ContainerTag.parseFromString(input, 0);

        assertEquals(body.toString(), parsed.getKey().toString());
    }
}