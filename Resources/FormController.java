import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Wiktor Łazarski on 02.07.2017.
 */
public class FormController {

    //user inputs keeper
    private ArrayList<String> formUserInputs;

    public FormController(FormView view){
        formUserInputs = new ArrayList<>();

        //grab data from FormView
        for (JTextField textField : view.textFields) {
            formUserInputs.add(textField.getText());
        }

        //check for correctness of input data
        if(!FormValidator.getInstance().validate(formUserInputs))
            formUserInputs = null;

    }

    public ArrayList<String> getPageMetaData(){return formUserInputs;}
}
