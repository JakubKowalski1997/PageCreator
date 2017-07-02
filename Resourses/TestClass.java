import javax.swing.*;
import java.awt.*;

/**
 * Created by Wiktor Łazarski on 27.06.2017.
 */
public class TestClass {

    public static void main(String[] args){
        FormModel form = new FormModel();

        //main loop
        EventQueue.invokeLater(()->
        {
            JFrame formEditor = new FormView();
        });
    }
}
