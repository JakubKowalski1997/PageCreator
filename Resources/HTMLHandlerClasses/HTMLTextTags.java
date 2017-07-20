package HTMLHandlerClasses;
/**
 * Created by Konrad on 2017-06-28.
 */

public enum HTMLTextTags {
    H1, H2, H3, H4,
    H5, H6, P, EM, TITLE;

    public String toString() {
        return super.toString().toLowerCase();
    }
}
