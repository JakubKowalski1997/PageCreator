package Utils;

/**
 * Created by Konrad on 2017-07-14.
 */
public class EnumUtils {
    public static <T extends Enum<T>> boolean isStringEnumVal(Enum<T> enumeration, String val) {
        try {
            T value = Enum.valueOf(enumeration.getDeclaringClass(), val.toUpperCase());
            return true;
        }
        catch (IllegalArgumentException e) {
            return false;
        }
    }

}
