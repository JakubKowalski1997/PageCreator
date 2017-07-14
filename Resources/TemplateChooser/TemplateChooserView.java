package TemplateChooser;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.util.ArrayList;

/**
 * Created by Wiktor Łazarski on 12.07.2017.
 */
public class TemplateChooserView extends JFrame {

    //radio buttons
    private ButtonGroup radioButtons;
    private ArrayList<JRadioButton> radioButtonsArray;

    private void createAndAddNorthComponents(){
        JPanel northPanel = new JPanel();

        //create title label
        JLabel jLabel = new JLabel("HTML Page template");
        jLabel.setFont(new Font("Courier New", Font.PLAIN, 40));

        //add panel
        northPanel.setBackground(Color.white);
        northPanel.add(jLabel, SwingConstants.CENTER);
        add(northPanel, BorderLayout.NORTH);
    }

    private void createAndAddSouthComponents(){
        JPanel southPanel = new JPanel();

        //init ButtonGroup radioButtons
        radioButtons = new ButtonGroup();

        //add radio buttons
        addRadioButton("First", true, southPanel);
        addRadioButton("Second", false, southPanel);
        addRadioButton("Third", false, southPanel);

        //setting border
        Border etched = BorderFactory.createEtchedBorder();
        Border titled = BorderFactory.createTitledBorder(etched, "Choose your template");
        southPanel.setBorder(titled);

        //Next step button
        JButton button = new JButton("Next step");
        button.addActionListener(event -> {
            /*
            todo :
            after choosing upload template in HTML code
             */
            if(radioButtonsArray.get(0).isSelected()){
                // constructor for first template
            }
            else if(radioButtonsArray.get(1).isSelected()){
                // constructor for second template
            }
            else if(radioButtonsArray.get(2).isSelected()){
                // constructor for third template
            }

            System.exit(0);
        });
        southPanel.add(button);

        //add panel
        southPanel.setBackground(Color.white);
        add(southPanel, BorderLayout.SOUTH);
    }

    private void createAndAddImage(){
        JPanel imagesPanel = new JPanel();
        imagesPanel.setLayout(new GridLayout(1, 3));

        //add images
        ImageComponent image;
        image = new ImageComponent("./Resources/TemplateChooser/firstTemplate.png");
        imagesPanel.add(image);

        image = new ImageComponent("./Resources/TemplateChooser/secondTemplate.png");
        imagesPanel.add(image);

        image = new ImageComponent("./Resources/TemplateChooser/thirdTemplate.png");
        imagesPanel.add(image);

        //create and add border
        Border etched = BorderFactory.createEtchedBorder();
        Border titled = BorderFactory.createTitledBorder(etched, "HTML Page templates to edit");
        imagesPanel.setBorder(titled);

        imagesPanel.setBackground(Color.white);
        add(imagesPanel, BorderLayout.CENTER);
    }

    //create and add radioButton
    private void addRadioButton(String name, boolean selected, JPanel panel){

        //create and customise radio button
        JRadioButton button = new JRadioButton(name, selected);
        radioButtonsArray.add(button);
        button.setBackground(Color.white);
        radioButtons.add(button);

        panel.add(button);
    }

    private void setDefaultOption(){
        //check screen size
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();

        //default size and location
        setSize((int) (screenSize.width / 1.5), (int) (screenSize.height / 1.5));
        setLocation((int)(screenSize.width / 1.5) - (screenSize.width / 2), (int)(screenSize.height / 1.5) - (screenSize.height / 2));

        //set others default opt
        setTitle("HTML Page Creator");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    //window constructor
    public TemplateChooserView(){
        //init radioBurronsArray
        radioButtonsArray = new ArrayList<>();

        //create components and set their location
        createAndAddNorthComponents();
        createAndAddImage();
        createAndAddSouthComponents();

        setDefaultOption();

        //set icon
        Image icon = new ImageIcon("html-icon.png").getImage();
        setIconImage(icon);
    }
}
