package MainEditor.TemplatePanels;

import MainEditor.JColorComboBox;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Wiktor Łazarski on 26.07.2017.
 */
public class ContentAdsControllPanel extends JPanel {

    //attributes
    private String type;
    private Dimension screenSize;
    private String[] fonts;
    private int[] fontSizes;
    //panel which show changes in controller
    private ContentAdsVisualizingPanel visulizingPanel;
    //JColorComboBox
    private JColorComboBox fontColors;
    private JColorComboBox backgroundColors;
    public JColorComboBox getFontColors(){return fontColors;}
    public JColorComboBox getBackgroundColors(){return backgroundColors;}

    //constructor
    public ContentAdsControllPanel(ContentAdsVisualizingPanel visulizingPanel, String border){
        this.visulizingPanel = visulizingPanel;

        type = border;

        //getting all options for comboboxes
        fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
        fontSizes = new int[]{16, 18, 20, 22, 24, 26, 28, 36, 48, 72, 96, 130, 154, 170};

        //get screen size
        Toolkit kit = Toolkit.getDefaultToolkit();
        screenSize = kit.getScreenSize();

        setLayout(new FlowLayout(FlowLayout.LEFT, 5 , 5));
        setBackground(Color.white);

        //add components
        add(editComponents(border));
    }

    //edit components
    private JPanel editComponents(String border) {
        JPanel editComponentsPanel;
        if(type.equals("Content")) {
            editComponentsPanel = new JPanel(new GridLayout(7, 1));
            //set size
            editComponentsPanel.setPreferredSize(new Dimension(screenSize.width / 3, (int) (screenSize.height / 4.3)));
        }
        else {
            editComponentsPanel = new JPanel(new GridLayout(6, 1));
            //set size
            editComponentsPanel.setPreferredSize(new Dimension(screenSize.width / 3, (int) (screenSize.height / 4.7)));
        }


        //create and set border
        Border etched = BorderFactory.createEtchedBorder();
        Border title = BorderFactory.createTitledBorder(etched, border + " atributes");
        editComponentsPanel.setBorder(title);

        //add components
        //Font type
        createAndAddComboBoxFontNames(editComponentsPanel, "Font : ");
        //Font size
        createAndAddComboBoxFontSizes(editComponentsPanel, "Font size : ");
        //Font color - in the future
        createAndAddColorComboBox(editComponentsPanel, "Font color : ", true);
        //Font style
        createAndAddComponentsFontStyle(editComponentsPanel, "Font style : ");
        //Background color - in the future
        createAndAddColorComboBox(editComponentsPanel, "Background color : ", false);
        //Text Position
        createAndAddComponentsTextPosition(editComponentsPanel, "Text position : ");

        if(type.equals("Content"))
            createAndAddButtonsToSelectCurrContent(editComponentsPanel, "Current content field : ");

        editComponentsPanel.setBackground(Color.white);
        return editComponentsPanel;
    }

    private void createAndAddButtonsToSelectCurrContent(JPanel container, String label){
        JPanel fullComponent = new JPanel(new FlowLayout(FlowLayout.LEFT));
        fullComponent.setBackground(Color.white);
        fullComponent.add(new JLabel(label));

        //buttons
        JButton addBtn = new JButton(" Previous ");
        addBtn.addActionListener(event->{
            //callback
        });
        fullComponent.add(addBtn);

        JButton deleteBtn = new JButton(" Next ");
        addBtn.addActionListener(event->{
            //callback
        });
        fullComponent.add(deleteBtn);

        container.add(fullComponent);
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
        //callback
        fontNames.addActionListener(event -> {
            //Font curr = visualizingPanel.getTextField().getFont();
            //visualizingPanel.getTextField().setFont(new Font(fontNames.getSelectedItem().toString(), curr.getStyle(), curr.getSize()));
            //visualizingPanel.repaint();
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

        fontSizes.setSelectedItem(72);
        //callback
        fontSizes.addActionListener(event -> {
            //Font curr = visualizingPanel.getTextField().getFont();
            //visualizingPanel.getTextField().setFont(new Font(curr.getName(), curr.getStyle(), (int)fontSizes.getSelectedItem()));
            //visualizingPanel.repaint();
        });

        fullComponent.add(fontSizes);
        container.add(fullComponent);
    }

    private void createAndAddComponentsFontStyle(JPanel container, String label){
        JPanel fullComponent = new JPanel(new FlowLayout(FlowLayout.LEFT));
        fullComponent.setBackground(Color.white);
        fullComponent.add(new JLabel(label));

        JCheckBox bold = new JCheckBox("BOLD", false);
        bold.setBackground(Color.white);
        JCheckBox italic = new JCheckBox("ITALIC", false);
        italic.setBackground(Color.white);

        //callback
        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int mode = 0;
                if (bold.isSelected())
                    mode += Font.BOLD;
                if (italic.isSelected())
                    mode += Font.ITALIC;

                //Font curr = visualizingPanel.getTextField().getFont();
                //visualizingPanel.getTextField().setFont(new Font(curr.getName(), mode, curr.getSize()));
                //visualizingPanel.repaint();
            }
        };

        bold.addActionListener(listener);
        italic.addActionListener(listener);

        fullComponent.add(bold);
        fullComponent.add(italic);

        container.add(fullComponent);
    }

    private void createAndAddComponentsTextPosition(JPanel container, String label){
        JPanel fullComponent = new JPanel(new FlowLayout(FlowLayout.LEFT));
        fullComponent.setBackground(Color.white);
        fullComponent.add(new JLabel(label));

        ButtonGroup radioButtons = new ButtonGroup();

        JRadioButton left = new JRadioButton("LEFT", true);
        left.setBackground(Color.white);
        left.addActionListener(event->{
            //visualizingPanel.getTextField().setHorizontalAlignment(JTextField.LEFT);
            //visualizingPanel.repaint();
        });

        JRadioButton center = new JRadioButton("CENTER", false);
        center.setBackground(Color.white);
        center.addActionListener(event->{
            //visualizingPanel.getTextField().setHorizontalAlignment(JTextField.CENTER);
            //visualizingPanel.repaint();
        });

        JRadioButton right = new JRadioButton("RIGHT", false);
        right.setBackground(Color.white);
        right.addActionListener(event->{
            // visualizingPanel.getTextField().setHorizontalAlignment(JTextField.RIGHT);
            //visualizingPanel.repaint();
        });

        radioButtons.add(left);
        radioButtons.add(center);
        radioButtons.add(right);

        fullComponent.add(left);
        fullComponent.add(center);
        fullComponent.add(right);

        container.add(fullComponent);
    }

    private void createAndAddColorComboBox(JPanel container, String label, boolean foreground){
        JPanel fullComponent = new JPanel(new FlowLayout(FlowLayout.LEFT));
        fullComponent.setBackground(Color.white);
        fullComponent.add(new JLabel(label));


        if(foreground) {
            fontColors = new JColorComboBox();
            fontColors.setSelectedItem("BLACK");
            fontColors.addActionListener(event->{
                //visualizingPanel.getTextField().setForeground(fontColors.getSelectedColor());
                //visualizingPanel.repaint();
            });

            fullComponent.add(fontColors);
        }
        else{
            backgroundColors = new JColorComboBox();
            backgroundColors.setSelectedItem("WHITE");
            backgroundColors.addActionListener(event->{
                //visualizingPanel.getTextField().setBackground(backgroundColors.getSelectedColor());
                //visualizingPanel.repaint();
            });

            fullComponent.add(backgroundColors);
        }

        container.add(fullComponent);
    }

}
