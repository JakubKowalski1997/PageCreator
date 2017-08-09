package Utils.DocumentUtils; /**
 * Created by Konrad on 2017-07-12.
 */

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 *  Class which performs writing objects, which can be represented as string, to file
 */
public class DocumentWriter {

    private static final Map<String, Charset> charsetsMap = new HashMap<>();
    static {
        charsetsMap.put("utf-8", StandardCharsets.UTF_8);
        charsetsMap.put("utf-16", StandardCharsets.UTF_16);
        charsetsMap.put("utf-16be", StandardCharsets.UTF_16BE);
        charsetsMap.put("uft-16le", StandardCharsets.UTF_16LE);
        charsetsMap.put("us-ascii", StandardCharsets.US_ASCII);
    }

    public static void writeToFile(Object o, String path, String charset) throws FileNotFoundException {
        try {
            PrintWriter outFile = new PrintWriter(new OutputStreamWriter(new FileOutputStream(path), charsetsMap.get(charset)));
            outFile.print(o.toString());
            outFile.close();
        }
        catch (FileNotFoundException e) {
            throw e;
        }
    }

    /**
     * @param o object to be written
     * @param path represents path of output file
     * @throws FileNotFoundException
     */
    public static void writeToFile(Object o, String path) throws FileNotFoundException {
        writeToFile(o, path, "utf-8");
    }
}
