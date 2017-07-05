package HTMLHandlerClasses;
/**
 * Created by Konrad on 2017-06-28.
 */

/**
 * Structure representing attribute of html tag
 */
public class TagAttribute {
    public String name;
    public String value;

    public TagAttribute(String nme, String val) {
        this.name = nme;
        this.value = val;
    }
}
