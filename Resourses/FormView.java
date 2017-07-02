import javax.swing.*;
import java.awt.*;

/**
 * Created by Wiktor Łazarski on 02.07.2017.
 */
public class FormView extends JFrame {

    private static final int TEXTAREA_ROWS = 8;
    private static final int TEXTAREA_COLUMNS = 20;

    private void createAndAddComponents(){

    }

    private void setDefaultOption(){
        //check screen size
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();

        //set window dimension and location on screen
        setSize(screenSize.width / 2, screenSize.height / 2);
        setLocationByPlatform(true);

        //set others default opt
        getContentPane().setBackground(new Color(238, 163, 65));
        setTitle("Page Creator - <head> section");
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    //Window constructor
    FormView(){
        setDefaultOption();

        //set icon
        /*
        * Image image = new ImageIcon(Path).getImage();
        * setIconImage(image);
        * */
    }
}