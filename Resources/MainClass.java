import javax.swing.*;
import java.awt.*;

/**
 * Created by Wiktor Łazarski on 27.06.2017.
 */

import MVCFormEditor.FormView;
import MainEditor.MainEditorWindow;
import TemplateChooser.TemplateChooserView;

public class MainClass {

    public static void main(String[] args){

        //main loop
        EventQueue.invokeLater(()->
        {
            JFrame startOfProgram = new FormView();
        });
    }
}
