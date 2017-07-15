package Utils.DocumentUtils; /**
 * Created by Konrad on 2017-07-12.
 */

import java.io.PrintWriter;
import java.io.FileNotFoundException;

/**
 *  Class which performs writing objects, which can be represented as string, to file
 */
public class DocumentWriter {

    /**
     * @param o object to be written
     * @param path represents path of output file
     * @throws FileNotFoundException
     */
    public static void writeToFile(Object o, String path) throws FileNotFoundException {
        PrintWriter outFile = new PrintWriter(path);

        outFile.print(o.toString());

        outFile.close();
    }
}
