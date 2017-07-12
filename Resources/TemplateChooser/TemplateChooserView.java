package TemplateChooser;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Wiktor Łazarski on 12.07.2017.
 */
public class TemplateChooserView extends JFrame {

    private void createAndAddNorthComponents(){
        JPanel northPanel = new JPanel();

        //create title label
        JLabel jLabel = new JLabel("Choose template to edit : ");
        jLabel.setFont(new Font("Courier New", Font.BOLD + Font.ITALIC, 40));

        //add panel
        northPanel.setBackground(Color.white);
        northPanel.add(jLabel, SwingConstants.CENTER);
        add(northPanel, BorderLayout.NORTH);
    }

    private void setDefaultOption(){
        //check screen size
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();

        //default size
        setSize(screenSize.width / 2, screenSize.height / 2);

        //set window full screen
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        //set others default opt
        setTitle("HTML Page Creator");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    //window constructor
    public TemplateChooserView(){
        //create components and set their location
        createAndAddNorthComponents();

        setDefaultOption();

        //set icon
        Image icon = new ImageIcon("html-icon.png").getImage();
        setIconImage(icon);
    }

}
