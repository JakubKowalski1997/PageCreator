package MainEditor.TemplatePanels;

import MainEditor.JColorComboBox;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static MainEditor.TemplatePanels.MenuVisualizingPanel.gridN;

/**
 * Created by Wiktor Łazarski on 26.07.2017.
 */
public class ContentAdsControllPanel extends JPanel {

    //attributes
    private String type;
    private Dimension screenSize;
    private String[] fonts;
    private int[] fontSizes;
    private int currField;
    //panel which show changes in controller
    private ContentAdsVisualizingPanel visualizingPanel;
    //JColorComboBox
    private JColorComboBox fontColors;
    private JColorComboBox backgroundColors;
    public JColorComboBox getFontColors(){return fontColors;}
    public JColorComboBox getBackgroundColors(){return backgroundColors;}

    //constructor
    public ContentAdsControllPanel(ContentAdsVisualizingPanel visualizingPanel, String border){
        this.visualizingPanel = visualizingPanel;
        currField = 0;
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
        JButton prevBtn = new JButton(" Previous ");
        prevBtn.addActionListener(event->{
            //callback
            /*if(visualizingPanel.getFields().size() != gridN){
                for(int i = gridN; i < visualizingPanel.getFields().size(); i++) {
                    visualizingPanel.delete();
                }
            }*/

            if(currField == 0) {
                return;
            }

            visualizingPanel.scrollPane.getViewport().remove(visualizingPanel.getFields().get(currField));

            visualizingPanel.scrollPane.getViewport().add(visualizingPanel.getFields().get(--currField));
            visualizingPanel.revalidate();
            visualizingPanel.repaint();
        });
        fullComponent.add(prevBtn);

        JButton nextBtn = new JButton(" Next ");
        nextBtn.addActionListener(event->{
            //callback
            if(visualizingPanel.getFields().size() != gridN){
                for(int i = visualizingPanel.getFields().size(); i < gridN; i++)
                    visualizingPanel.add();
            }

            if(currField >= gridN - 1)
                return;

            visualizingPanel.scrollPane.getViewport().remove(visualizingPanel.getFields().get(currField));

            visualizingPanel.scrollPane.getViewport().add(visualizingPanel.getFields().get(++currField));
            visualizingPanel.revalidate();
            visualizingPanel.repaint();
        });
        fullComponent.add(nextBtn);

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
            StyledDocument doc = visualizingPanel.getFields().get(currField).getStyledDocument();
            SimpleAttributeSet fontNameSet = new SimpleAttributeSet();
            StyleConstants.setFontFamily(fontNameSet, fontNames.getSelectedItem().toString());
            doc.setParagraphAttributes(0, doc.getLength(), fontNameSet, false);
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
            StyledDocument doc = visualizingPanel.getFields().get(currField).getStyledDocument();
            SimpleAttributeSet fontSizeSet = new SimpleAttributeSet();
            StyleConstants.setFontSize(fontSizeSet, (int)fontSizes.getSelectedItem());
            doc.setParagraphAttributes(0, doc.getLength(), fontSizeSet, false);
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
                StyledDocument doc = visualizingPanel.getFields().get(currField).getStyledDocument();
                SimpleAttributeSet fontStyle = new SimpleAttributeSet();

                if (bold.isSelected())
                    StyleConstants.setBold(fontStyle, true);
                else
                    StyleConstants.setBold(fontStyle, false);
                if (italic.isSelected())
                    StyleConstants.setItalic(fontStyle, true);
                else
                    StyleConstants.setItalic(fontStyle, false);

                doc.setParagraphAttributes(0, doc.getLength(), fontStyle, false);
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
            StyledDocument doc = visualizingPanel.getFields().get(currField).getStyledDocument();
            SimpleAttributeSet leftSet = new SimpleAttributeSet();
            StyleConstants.setAlignment(leftSet, StyleConstants.ALIGN_LEFT);
            doc.setParagraphAttributes(0, doc.getLength(), leftSet, false);
        });

        JRadioButton center = new JRadioButton("JUSTIFY", false);
        center.setBackground(Color.white);
        center.addActionListener(event->{
            StyledDocument doc = visualizingPanel.getFields().get(currField).getStyledDocument();
            SimpleAttributeSet justify = new SimpleAttributeSet();
            StyleConstants.setAlignment(justify, StyleConstants.ALIGN_JUSTIFIED);
            doc.setParagraphAttributes(0, doc.getLength(), justify, false);

        });

        JRadioButton right = new JRadioButton("RIGHT", false);
        right.setBackground(Color.white);
        right.addActionListener(event->{
            StyledDocument doc = visualizingPanel.getFields().get(currField).getStyledDocument();
            SimpleAttributeSet rightSet = new SimpleAttributeSet();
            StyleConstants.setAlignment(rightSet, StyleConstants.ALIGN_RIGHT);
            doc.setParagraphAttributes(0, doc.getLength(), rightSet, false);
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
                visualizingPanel.getFields().get(currField).setForeground(fontColors.getSelectedColor());
            });

            fullComponent.add(fontColors);
        }
        else{
            backgroundColors = new JColorComboBox();
            backgroundColors.setSelectedItem("WHITE");
            backgroundColors.addActionListener(event->{
                visualizingPanel.getFields().get(currField).setBackground(backgroundColors.getSelectedColor());
            });

            fullComponent.add(backgroundColors);
        }

        container.add(fullComponent);
    }

}
