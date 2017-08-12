import javax.swing.*;
import java.awt.*;

/**
 * Created by Wiktor Łazarski on 27.06.2017.
 */

import MVCFormEditor.FormView;
import MVCFormEditor.WelcomeScreen;
import MainEditor.MainEditorWindow;
import TemplateChooser.TemplateChooserView;
import TemplateEditor.ColorFactory;

public class MainClass {

    private static void registerColors() {
        ColorFactory colorFactory = ColorFactory.getInstance();
        colorFactory.registerColor("WHITE", new Color(255, 255, 255));
        colorFactory.registerColor("MAGENTA", new Color(255, 0, 255));
        colorFactory.registerColor("ORANGE", new Color(255, 200, 0));
        colorFactory.registerColor("PINK", new Color(255, 175, 175));
        colorFactory.registerColor("BLUE", new Color(0, 0, 255));
        colorFactory.registerColor("YELLOW", new Color(255, 255, 0));
        colorFactory.registerColor("DARK_GRAY", new Color(64, 64, 64));
        colorFactory.registerColor("GRAY", new Color(128, 128, 128));
    }

    public static void main(String[] args){

        registerColors();

        //main loop
        EventQueue.invokeLater(()->
        {
            JFrame startOfProgram = new WelcomeScreen();
        });
    }
}
