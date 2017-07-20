package HTMLHandlerClasses;

/**
 * Created by Konrad on 2017-06-28.
 */
public enum HTMLContainerTags {
    DIV, OL, UL, LI,
    HEAD, HTML, BODY,
    TABLE, TR, TD, TH;

    public String toString() {
        return super.toString().toLowerCase();
    }
}
