package TestClasses.HTMLTestClasses;

/**
 * Created by Konrad on 2017-07-20.
 */

import HTMLHandlerClasses.HTMLDocument;
import TestClasses.Test;

public class TestHTMLDocument extends Test {

    TestHTMLDocument() {
        super("TestHTMLDocument");
    }

    private void testParsing() {
        String input = "<!DOCTYPE html>\n" +
                "<html>\n" +
                "    <body>\n" +
                "        <div class = \"rodzic\">\n" +
                "        \n" +
                "            <div class = \"naglowek\">Witaj</div>\n" +
                "        \n" +
                "            <div class = \"dziecko1\">DZIECKO1</div><div class = \"dziecko2\">DZIECKO2</div><br />\n" +
                "            \n" +
                "            <div class = \"dolny\">PODPIS</div>\n" +
                "        </div>\n" +
                "    </body>\n" +
                "</html>";

        String expected = "<!DOCTYPE html>\n" +
                "\n" +
                "<html>\n" +
                "\t<body>\n" +
                "\t\t<div class=\"rodzic\">\n" +
                "\t\t\t<div class=\"naglowek\"></div>\n" +
                "\t\t\t<div class=\"dziecko1\"></div>\n" +
                "\t\t\t<div class=\"dziecko2\"></div>\n" +
                "\t\t\t<br/>\n" +
                "\t\t\t<div class=\"dolny\"></div>\n" +
                "\t\t</div>\n" +
                "\t</body>\n" +
                "</html>";

        try {
            HTMLDocument parsed = HTMLDocument.parseFromString(input);

            if (!parsed.toString().equals(expected)) {
                reportError("Expected: \n" + expected + " Got: \n" + parsed.toString());
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
        TestHTMLDocument test = new TestHTMLDocument();
        test.test();
    }
}
