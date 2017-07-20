package Utils.DocumentUtils;

/**
 * Created by Konrad on 2017-07-12.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class DocumentReader {
    public static String readFromFile(String path) throws FileNotFoundException {
        File file = new File(path);
        Scanner in = new Scanner(file);

        StringBuilder readString = new StringBuilder();

        while (true) {
            readString.append(in.nextLine());
            if (!in.hasNextLine())
                break;
            else
                readString.append('\n');
        }

        in.close();
        return readString.toString();
    }
}
