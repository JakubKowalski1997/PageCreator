package TestClasses.HTMLTestClasses;

import HTMLHandlerClasses.HTMLDocument;
import junit.framework.TestCase;

/**
 * Created by Konrad on 2017-08-10.
 */
public class HTMLDocumentTest extends TestCase {

    public void testParseFromString() throws Exception {
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


        HTMLDocument parsed = HTMLDocument.parseFromString(input);

        assertEquals(expected, parsed.toString());
    }
}