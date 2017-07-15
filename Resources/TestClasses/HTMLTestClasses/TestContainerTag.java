package TestClasses.HTMLTestClasses;

/**
 * Created by Konrad on 2017-07-04.
 */

import HTMLHandlerClasses.*;
import TestClasses.Test;

import java.util.ArrayList;

import Utils.EnumUtils;
import javafx.util.Pair;

public class TestContainerTag extends Test {
    public TestContainerTag() {
        super("TestContainerTag");
    }

    public ArrayList<ContainerTag> getTestTags() {
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

    public ArrayList<String> getExpectedRepresentations() {
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


    private void testParsing() {
        String input =  "<body>\n" +
                "\t<div>\n" +
                "\t\t<p>text</p>\n" +
                "\t</div>\n" +
                "</body>";

        ContainerTag body = new ContainerTag(HTMLContainerTags.BODY);
        ContainerTag div = new ContainerTag(HTMLContainerTags.DIV);
        TextTag text = new TextTag(HTMLTextTags.P, "text");
        div.addNestedTag(text);
        body.addNestedTag(div);

        try {
            Pair<ContainerTag, Integer> parsed = ContainerTag.parseFromString(input,0);

            if (!body.toString().equals(parsed.getKey().toString())) {
                reportError("Expected: " + body.toString() + " Got: " +
                parsed.getKey().toString());
            }
        }
        catch (Exception e) {
            reportError(e.getMessage());
        }
    }

    public void test() {
        super.test();

        ArrayList<ContainerTag> tags = getTestTags();
        ArrayList<String> expected = getExpectedRepresentations();

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
        TestContainerTag test = new TestContainerTag();
        test.test();
    }
}
