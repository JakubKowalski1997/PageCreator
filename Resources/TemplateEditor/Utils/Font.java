package TemplateEditor.Utils;

/**
 * Created by Konrad on 2017-07-21.
 */
public class Font {
    public enum Family {
        ARIAL, TIMES_NEW_ROMAN;

        public String toString() {
            switch (this) {
                case TIMES_NEW_ROMAN:
                    return "Times New Roman";

                default:
                    return super.toString().toLowerCase();
            }
        }
    }

    public Font(Family family, int size) {
        this.family = family;
        this.size = size;
    }

    public Family family = Family.ARIAL;
    public int size = 0;
}
