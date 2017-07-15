package TestClasses.DocumentIOTestClasses;

import TestClasses.Test;
import Utils.DocumentUtils.*;

import java.io.FileNotFoundException;

/**
 * Created by Konrad on 2017-07-15.
 */
public class TestIO extends Test {

    TestIO() {
        super("TestDocumentIO");
    }

    public void test() {
        super.test();

        String testPath = "path.txt";
        String testText = "some text\n some text";

        try {
            DocumentWriter.writeToFile(testText, testPath);
        }
        catch (FileNotFoundException e) {
            reportError(e.getMessage());
        }

        try {
            String readText = DocumentReader.readFromFile(testPath);

            if (!readText.equals(testText)) {
                reportError("Expected: " + testText + " Got: " + readText);
            }
        }
        catch (FileNotFoundException e) {
            reportError(e.getMessage());
        }

        reportResults();
    }

    public static void main(String[] args) {
        TestIO test = new TestIO();
        test.test();
    }
}
