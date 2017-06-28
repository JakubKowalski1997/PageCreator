/**
 * Created by Konrad on 2017-06-28.
 */
public enum HTMLContainerTags {
    DIV, OL, UL, LI,
    HEAD, HTML, BODY,
    TABLE, TR, TD, TH;

    public String toString(HTMLContainerTags tag) throws Exception {
        switch (tag) {
            case DIV:
                return "div";
            case OL:
                return "ol";
            case UL:
                return "ul";
            case LI:
                return "li";
            case HEAD:
                return "head";
            case HTML:
                return "html";
            case BODY:
                return "body";
            case TABLE:
                return "table";
            case TR:
                return "tr";
            case TD:
                return "td";
            case TH:
                return "th";
        }

        throw new Exception("Unknown tag");
    }
}
