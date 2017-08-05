package CSSHandlerClasses;

/**
 * Created by Konrad on 2017-07-12.
 */

import java.util.ArrayList;
import javafx.util.Pair;

public class CSSDocument  {
    private ArrayList<CSSElement> elements = new ArrayList<>();

    public CSSDocument() {}

    public ArrayList<CSSElement> getElements() {
        return elements;
    }

    public String toString() {
        StringBuilder stringRep = new StringBuilder();

        for (int i = 0; i < elements.size(); ++i) {
            stringRep.append(elements.get(i).toString());
            if (i != elements.size() - 1)
                stringRep.append("\n\n");
        }

        return stringRep.toString();
    }

    public boolean equals(Object o) {
        // self check
        if (this == o)
            return true;
        // null check
        if (o == null)
            return false;
        // type check and cast
        if (getClass() != o.getClass())
            return false;

        CSSDocument oDoc = (CSSDocument) o;

        if (elements.size() != oDoc.elements.size())
            return false;

        for (int i = 0; i < elements.size(); ++i) {
            if (!elements.get(i).equals(oDoc.elements.get(i)))
                return false;
        }

        return true;
    }

    public static CSSDocument parseFromString(String textRep) throws Exception {
        if (textRep.length() < 3)
            throw new Exception("Too short input string to constitute a css document");

        CSSDocument newDoc = new CSSDocument();
        CSSDocumentHandler handler = CSSDocumentHandler.getInstance();

        int i = 0;

        while (i < textRep.length()) {
            if (Character.isWhitespace(textRep.charAt(i))) {
                ++i;
                continue;
            }

            Pair<CSSElement, Integer> parsedElement = CSSElement.parseFromString(textRep, i);
            i  = parsedElement.getValue();
            ++i;
            handler.addElement(newDoc, parsedElement.getKey());
        }

        return newDoc;
    }
}
