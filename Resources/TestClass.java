import javax.swing.*;
import java.awt.*;

/**
 * Created by Wiktor Łazarski on 27.06.2017.
 */

import MVCFormEditor.FormView;
import MainEditor.MainEditorWindow;

public class TestClass {

    public static void main(String[] args){

        //main loop
        EventQueue.invokeLater(()->
        {
            JFrame formEditor = new FormView();
        });
    }
}
