package MVCFormEditor;

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

    public boolean validate(ArrayList<String> data){

        //check if fields are filled
        for (String input : data) {
            if(input.isEmpty())
                return false;
        }

        //check if name and surname doesn't contain digits
        for(int i = 0; i < 2; i++){
            for(char c : data.get(i).toCharArray()){
                if(Character.isDigit(c))
                    return false;
            }
        }

        return true;
    }
}
