package MainEditor.MVCTitle;

import javafx.geometry.VerticalDirection;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

/**
 * Created by Wiktor Łazarski on 21.07.2017.
 */
public class TitleView extends JPanel {

    //attributes
    private Dimension screenSize;
    private String[] fonts;
    private int[] fontSizes;

    //edit components
    private JPanel editComponents(){
        JPanel editComponentsPanel = new JPanel(new GridLayout(6, 1));

        //create and set border
        Border etched = BorderFactory.createEtchedBorder();
        Border title = BorderFactory.createTitledBorder(etched, "Edit title");
        editComponentsPanel.setBorder(title);

        //set size
        editComponentsPanel.setPreferredSize(new Dimension(screenSize.width / 3, screenSize.height / 5));

        //add components
        //Font type
        createAndAddComboBoxFontNames(editComponentsPanel, "Font : ");
        //Font size
        createAndAddComboBoxFontSizes(editComponentsPanel, "Font size : ");
        //Font color - in the future
        createAndAddComboBoxFontNames(editComponentsPanel, "Font color: ");

        editComponentsPanel.setBackground(Color.white);
        return editComponentsPanel;
    }

    //visualizing components
    private JPanel visualComponents(){
        JPanel visualComponentsPanel = new JPanel();

        //temporary to see positioning  : create and set border
        Border etched = BorderFactory.createEtchedBorder();
        Border title = BorderFactory.createTitledBorder(etched, "TITLE");
        visualComponentsPanel.setBorder(title);

        //set size
        visualComponentsPanel.setPreferredSize(new Dimension((int)(screenSize.width / 1.53), screenSize.height / 5));

        return visualComponentsPanel;
    }

    //constructor
    public TitleView(JFrame window){
        //getting all options for comboboxes
        fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
        fontSizes = new int[]{8, 9, 10, 11, 12, 14, 16, 18, 20, 22, 24, 26, 28, 36, 48, 72};

        //get screen size
        Toolkit kit = Toolkit.getDefaultToolkit();
        screenSize = kit.getScreenSize();

        setLayout(new FlowLayout(FlowLayout.LEFT, 5 , 5));
        setBackground(Color.white);

        //add components
        add(editComponents());
        add(visualComponents());

        window.add(this);
    }

    private void createAndAddComboBoxFontNames(JPanel container, String label){
        JPanel fullComponent = new JPanel(new FlowLayout(FlowLayout.LEFT));
        fullComponent.setBackground(Color.white);
        fullComponent.add(new JLabel(label));
        JComboBox<String> fontNames = new JComboBox<>();
        DefaultComboBoxModel<String> fonts = new DefaultComboBoxModel<>();

        for(int i = 0; i < this.fonts.length; i++)
            fonts.addElement(this.fonts[i]);

        fontNames.setModel(fonts);
        fontNames.addActionListener(event -> {
            //TitleController will set font name to the choosen by user one
        });

        fullComponent.add(fontNames);
        container.add(fullComponent);
    }

    private void createAndAddComboBoxFontSizes(JPanel container, String label){
        JPanel fullComponent = new JPanel(new FlowLayout(FlowLayout.LEFT));
        fullComponent.setBackground(Color.white);
        fullComponent.add(new JLabel(label));
        JComboBox<Integer> fontSizes = new JComboBox<>();

        for(int i = 0; i < this.fontSizes.length; i++)
            fontSizes.addItem(this.fontSizes[i]);

        fontSizes.addActionListener(event -> {
            //TitleController will set font size to the choosen by user one
        });

        fullComponent.add(fontSizes);
        container.add(fullComponent);
    }
}