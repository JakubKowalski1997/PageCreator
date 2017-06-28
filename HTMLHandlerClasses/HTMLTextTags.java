/**
 * Created by Konrad on 2017-06-28.
 */

public enum HTMLTextTags {
    H1, H2, H3, H4,
    H5, H6, P, EM,
    STRONG;

    public String toString(HTMLTextTags tag) throws Exception {
        switch (tag) {
            case H1:
                return "h1";
            case H2:
                return "h2";
            case H3:
                return "h3";
            case H4:
                return "h4";
            case H5:
                return "h5";
            case H6:
                return "h6";
            case P:
                return "p";
            case EM:
                return "em";
            case STRONG:
                return "strong";
        }

        throw new Exception("Unknown tag");
    }
}
