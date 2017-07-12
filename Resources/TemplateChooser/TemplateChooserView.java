package TemplateChooser;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Wiktor Łazarski on 12.07.2017.
 */
public class TemplateChooserView extends JFrame {

    //radio buttons
    private ButtonGroup radioButtons;

    private void createAndAddNorthComponents(){
        JPanel northPanel = new JPanel();

        //create title label
        JLabel jLabel = new JLabel("Choose template to edit : ");
        jLabel.setFont(new Font("Courier New", Font.PLAIN, 40));

        //add panel
        northPanel.setBackground(Color.white);
        northPanel.add(jLabel, SwingConstants.CENTER);
        add(northPanel, BorderLayout.NORTH);
    }

    //to destroy
    private void createAndAddSouthComponents(){
        JPanel southPanel = new JPanel();
        //init ButtonGroup radioButtons
        radioButtons = new ButtonGroup();

        //add radio buttons
        addRadioButton("First", true, southPanel);
        addRadioButton("Second", true, southPanel);
        addRadioButton("Third", true, southPanel);

        //add panel
        southPanel.setBackground(Color.white);
        add(southPanel, BorderLayout.SOUTH);
    }

    //create and add radioButton
    private void addRadioButton(String name, boolean selected, JPanel panel){
        JRadioButton button = new JRadioButton(name, selected);
        button.setBackground(Color.white);
        radioButtons.add(button);
        panel.add(button);

    }

    private void setDefaultOption(){
        //check screen size
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();

        //default size and location
        setSize(screenSize.width / 2, screenSize.height / 2);
        setLocation((screenSize.width / 2) - (screenSize.width / 4), (screenSize.height / 2) - (screenSize.height / 4));

        //set others default opt
        setTitle("HTML Page Creator");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    //window constructor
    public TemplateChooserView(){
        //create components and set their location
        createAndAddNorthComponents();
        createAndAddSouthComponents();

        setDefaultOption();

        //set icon
        Image icon = new ImageIcon("html-icon.png").getImage();
        setIconImage(icon);
    }

}
