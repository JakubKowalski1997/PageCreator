import java.util.*;

/**
 * Created by Wiktor Łazarski on 27.06.2017.
 */
public class FormValidator {
    private static FormValidator ourInstance = new FormValidator();

    public static FormValidator getInstance() {
        return ourInstance;
    }

    private FormValidator() {}

    public static boolean validate(Map<HeadAttrib, String> data){
        /*
        * TODO:
        * Valid data
        * if textfield is not null
        * if name and surname doesn't contain digits*/
        return true;
    }
}
