package TestClasses.DocumentIOTestClasses;

import Utils.DocumentUtils.DocumentReader;
import Utils.DocumentUtils.DocumentWriter;
import junit.framework.TestCase;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by Konrad on 2017-08-10.
 */
public class DocumentReaderTest extends TestCase {

    public void testReadFromFile() throws Exception {
        String testPath = "path.txt";
        String testText = "some text\n some text";

        DocumentWriter.writeToFile(testText, testPath);
        String readText = DocumentReader.readFromFile(testPath);

        assertEquals(testText, readText);
    }
}