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
public class MenuControllerPanel extends JPanel {

    //attributes
    private Dimension screenSize;
    private String[] fonts;
    private int[] fontSizes;
    //panel which show changes in controller
    private MenuVisualizingPanel visualizingPanel;
    public MenuVisualizingPanel getVisualizingPanel(){return visualizingPanel;}
    //JColorComboBox
    private JColorComboBox fontColors;
    private JColorComboBox backgroundColors;
    public JColorComboBox getFontColors(){return fontColors;}
    public JColorComboBox getBackgroundColors(){return backgroundColors;}

    //constructor
    public MenuControllerPanel(MenuVisualizingPanel visualizingPanel){
        this.visualizingPanel = visualizingPanel;

        //getting all options for comboboxes
        fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
        fontSizes = new int[]{16, 18, 20, 22, 24, 26, 28, 36, 48, 72, 96, 130, 154, 170};

        //get screen size
        Toolkit kit = Toolkit.getDefaultToolkit();
        screenSize = kit.getScreenSize();

        setLayout(new FlowLayout(FlowLayout.LEFT, 5 , 5));
        setBackground(Color.white);

        //add components
        add(editComponents());
    }

    //function to avoid mixing border color with background color
    private void avoidMixColor(){
        if(backgroundColors.getSelectedColor().equals(Color.green)){
            Border currSelected = BorderFactory.createLineBorder(Color.red, 5);
            Border titledCurrSelected = BorderFactory.createTitledBorder(currSelected, "Content of : ");

            //finding current selected menu
            for(int i = 0; i < visualizingPanel.getFields().size(); i++) {
                if(visualizingPanel.getFields().get(i).getBorder() != null)
                    visualizingPanel.getFields().get(i).setBorder(titledCurrSelected);
            }
        }
        if(backgroundColors.getSelectedColor().equals(Color.RED)){
            Border currSelected = BorderFactory.createLineBorder(Color.GREEN, 5);
            Border titledCurrSelected = BorderFactory.createTitledBorder(currSelected, "Content of : ");

            //finding current selected menu
            for(int i = 0; i < visualizingPanel.getFields().size(); i++) {
                if(visualizingPanel.getFields().get(i).getBorder() != null)
                    visualizingPanel.getFields().get(i).setBorder(titledCurrSelected);
            }
        }
    }

    //edit components
    private JPanel editComponents() {
        JPanel editComponentsPanel = new JPanel(new GridLayout(7, 1));

        //create and set border
        Border etched = BorderFactory.createEtchedBorder();
        Border title = BorderFactory.createTitledBorder(etched, "Menu atributes");
        editComponentsPanel.setBorder(title);

        //set size
        editComponentsPanel.setPreferredSize(new Dimension(screenSize.width / 3, (int)(screenSize.height / 4.25)));

        //add components
        //Add and delete menu div panel
        createAndAddButtonsMenuDiv(editComponentsPanel, "Menu option : ");
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

        editComponentsPanel.setBackground(Color.white);
        return editComponentsPanel;
    }

    private void createAndAddButtonsMenuDiv(JPanel container, String label){
        JPanel fullComponent = new JPanel(new FlowLayout(FlowLayout.LEFT));
        fullComponent.setBackground(Color.white);
        fullComponent.add(new JLabel(label));

        //buttons
        JButton addBtn = new JButton(" Add ");
        addBtn.addActionListener(event->{
            //callback
            visualizingPanel.add();
            visualizingPanel.revalidate();
            visualizingPanel.repaint();
        });
        fullComponent.add(addBtn);

        JButton deleteBtn = new JButton(" Delete ");
        deleteBtn.addActionListener(event->{
            //callback
            //checking if we don't delete bordered menu div
            if(visualizingPanel.getFields().get(visualizingPanel.getFields().size() - 1).getBorder() != null){
                JOptionPane.showMessageDialog(null, "Cannot delete current editing content",
                        "ERROR", JOptionPane.ERROR_MESSAGE);
                return;
            }

            visualizingPanel.delete();
            visualizingPanel.revalidate();
            visualizingPanel.repaint();
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
            for(int i = 0; i < visualizingPanel.getFields().size(); i++) {
                Font curr = visualizingPanel.getFields().get(i).getFont();
                visualizingPanel.getFields().get(i)
                        .setFont(new Font(fontNames.getSelectedItem().toString(), curr.getStyle(), curr.getSize()));
            }
            visualizingPanel.repaint();
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

        fontSizes.setSelectedItem(48);
        //callback
        fontSizes.addActionListener(event -> {
            for(int i = 0; i < visualizingPanel.getFields().size(); i++) {
                Font curr = visualizingPanel.getFields().get(i).getFont();
                visualizingPanel.getFields().get(i)
                        .setFont(new Font(curr.getName(), curr.getStyle(), (int)fontSizes.getSelectedItem()));
            }
            visualizingPanel.repaint();
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

                for(int i = 0; i < visualizingPanel.getFields().size(); i++) {
                    Font curr = visualizingPanel.getFields().get(i).getFont();
                    visualizingPanel.getFields().get(i)
                            .setFont(new Font(curr.getName(), mode, curr.getSize()));
                }
                visualizingPanel.repaint();
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
            for(int i = 0; i < visualizingPanel.getFields().size(); i++) {
                visualizingPanel.getFields().get(i).setHorizontalAlignment(JTextField.LEFT);
            }
            visualizingPanel.repaint();
        });

        JRadioButton center = new JRadioButton("CENTER", false);
        center.setBackground(Color.white);
        center.addActionListener(event->{
            for(int i = 0; i < visualizingPanel.getFields().size(); i++) {
                visualizingPanel.getFields().get(i).setHorizontalAlignment(JTextField.CENTER);
            }
            visualizingPanel.repaint();
        });

        JRadioButton right = new JRadioButton("RIGHT", false);
        right.setBackground(Color.white);
        right.addActionListener(event->{
            for(int i = 0; i < visualizingPanel.getFields().size(); i++) {
                visualizingPanel.getFields().get(i).setHorizontalAlignment(JTextField.RIGHT);
            }
            visualizingPanel.repaint();
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
                for(int i = 0; i < visualizingPanel.getFields().size(); i++) {
                    visualizingPanel.getFields().get(i).setForeground(fontColors.getSelectedColor());
                }
                visualizingPanel.repaint();
            });

            fullComponent.add(fontColors);
        }
        else{
            backgroundColors = new JColorComboBox();
            backgroundColors.setSelectedItem("WHITE");
            backgroundColors.addActionListener(event->{

                avoidMixColor();

                for(int i = 0; i < visualizingPanel.getFields().size(); i++) {
                    visualizingPanel.getFields().get(i).setBackground(backgroundColors.getSelectedColor());
                }
                visualizingPanel.repaint();
            });

            fullComponent.add(backgroundColors);
        }

        container.add(fullComponent);
    }
}
