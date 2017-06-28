/**
 * Created by Konrad on 2017-06-28.
 */
public enum HTMLSelfClosingTags {
    BR, IMG;

    public String toString(HTMLSelfClosingTags tag) throws Exception {
        switch (tag) {
            case BR:
                return "br";
            case IMG:
                return "img";
        }

        throw new Exception("Unknown tag");
    }
}
