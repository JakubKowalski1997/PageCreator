package MainEditor.MVCTitle;

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
    private HTMLTitlePreviewPanel visualizingPanel;

    //edit components
    private JPanel editComponents(){
        JPanel editComponentsPanel = new JPanel(new GridLayout(6, 1));

        //create and set border
        Border etched = BorderFactory.createEtchedBorder();
        Border title = BorderFactory.createTitledBorder(etched, "Title atributes");
        editComponentsPanel.setBorder(title);

        //set size
        editComponentsPanel.setPreferredSize(new Dimension(screenSize.width / 3, screenSize.height / 5));

        //add components
        //Font type
        createAndAddComboBoxFontNames(editComponentsPanel, "Font : ");
        //Font size
        createAndAddComboBoxFontSizes(editComponentsPanel, "Font size : ");
        //Font color - in the future
        createAndAddComboBoxFontNames(editComponentsPanel, "Font color : ");
        //Font style
        createAndAddComponentsFontStyle(editComponentsPanel, "Font style : ");
        //Background color - in the future
        createAndAddComboBoxFontNames(editComponentsPanel, "Background color : ");
        //Text Position
        createAndAddComponentsTextPosition(editComponentsPanel, "Text position : ");

        editComponentsPanel.setBackground(Color.white);
        return editComponentsPanel;
    }

    //visualizing HTML page title components
    private JPanel visualComponents(){
        JPanel visualComponentsPanel = new JPanel(new GridLayout(1,1));

        //temporary to see positioning  : create and set border
        Border etched = BorderFactory.createEtchedBorder();
        Border title = BorderFactory.createTitledBorder(etched, "Title current view");
        visualComponentsPanel.setBorder(title);

        //set size
        visualComponentsPanel.setPreferredSize(new Dimension((int)(screenSize.width / 1.53), screenSize.height / 5));

        visualizingPanel = new HTMLTitlePreviewPanel(visualComponentsPanel);

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

    private void createAndAddComponentsFontStyle(JPanel container, String label){
        JPanel fullComponent = new JPanel(new FlowLayout(FlowLayout.LEFT));
        fullComponent.setBackground(Color.white);
        fullComponent.add(new JLabel(label));

        JCheckBox bold = new JCheckBox("BOLD");
        bold.setBackground(Color.white);
        JCheckBox italic = new JCheckBox("ITALIC");
        italic.setBackground(Color.white);
        JCheckBox plain = new JCheckBox("PLAIN");
        plain.setBackground(Color.white);

        /*
        TODO :
        CREATE AND ADD ACTION LISTENER WHERE titleController will do his job refreash visualPanel and
        set proper value to titleModel
         */

        fullComponent.add(bold);
        fullComponent.add(italic);
        fullComponent.add(plain);

        container.add(fullComponent);
    }

    private void createAndAddComponentsTextPosition(JPanel container, String label){
        JPanel fullComponent = new JPanel(new FlowLayout(FlowLayout.LEFT));
        fullComponent.setBackground(Color.white);
        fullComponent.add(new JLabel(label));

        ButtonGroup radioButtons = new ButtonGroup();

        JRadioButton left = new JRadioButton("LEFT", true);
        left.setBackground(Color.white);
        JRadioButton center = new JRadioButton("CENTER", false);
        center.setBackground(Color.white);
        JRadioButton right = new JRadioButton("RIGHT", false);
        right.setBackground(Color.white);

        radioButtons.add(left);
        radioButtons.add(center);
        radioButtons.add(right);

        /*
        TODO :
        CREATE AND ADD ACTION LISTENER WHERE titleController will do his job refreash visualPanel and
        set proper value to titleModel
         */

        fullComponent.add(left);
        fullComponent.add(center);
        fullComponent.add(right);

        container.add(fullComponent);
    }
}