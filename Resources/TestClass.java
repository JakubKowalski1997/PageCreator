import javax.swing.*;
import java.awt.*;
import java.text.Normalizer;

/**
 * Created by Wiktor Łazarski on 27.06.2017.
 */
public class TestClass {

    public static void main(String[] args){

        //main loop
        EventQueue.invokeLater(()->
        {
            JFrame formEditor = new FormView();
        });
    }
}
