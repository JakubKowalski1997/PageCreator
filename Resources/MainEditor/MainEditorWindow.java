package MainEditor;

import MainEditor.MVCTitle.TitleView;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Wiktor Łazarski on 21.07.2017.
 */
public class MainEditorWindow extends JFrame{

    private void setDefaultOptions(){
        //getting screen dimensions
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screen = kit.getScreenSize();

        //set default screen size
        setBounds(0, 0, screen.width, screen.height);
        setResizable(false);

        //set on close operation
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        setTitle("HTML Page Editor");
        setVisible(true);

        //set icon
        Image icon = new ImageIcon("html-icon.png").getImage();
        setIconImage(icon);
    }

    public MainEditorWindow(){
        //setting layout manager
        setLayout(new GridLayout(3, 1));

        //adding title, manu and content panels
        add(new TitleView(this));
        add(new TitleView(this));
        add(new TitleView(this));

        setDefaultOptions();

    }

}
