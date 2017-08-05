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

import static MainEditor.JColorComboBox.stringColors;
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
    //panel which show changes in controller and curr selected menu
    private MenuVisualizingPanel menuVisualizingPanel;
    private ContentAdsVisualizingPanel visualizingPanel;

    //components
    JComboBox<String> fontNames;
    JComboBox<Integer> fontSizesCB;
    JCheckBox bold;
    JCheckBox italic;
    JRadioButton left;
    JRadioButton center;
    JRadioButton right;
    //JColorComboBox
    private JColorComboBox fontColors;
    private JColorComboBox backgroundColors;
    public JColorComboBox getFontColors(){return fontColors;}
    public JColorComboBox getBackgroundColors(){return backgroundColors;}

    //constructor
    public ContentAdsControllPanel(ContentAdsVisualizingPanel visualizingPanel, String border, MenuVisualizingPanel menuVisualizingPanel){
        this.menuVisualizingPanel = menuVisualizingPanel;
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

    //update editComponents set proper value for content in editComponents
    public void updateEditComponentStates(){
        fontNames.setSelectedItem(visualizingPanel.getFields().get(currField).getFont().getFontName());
        fontSizesCB.setSelectedItem(visualizingPanel.getFields().get(currField).getFont().getSize());

        int mode = visualizingPanel.getFields().get(currField).getFont().getStyle();

        bold.setSelected(false);
        italic.setSelected(false);

        if(mode == 3){
            bold.setSelected(true);
            italic.setSelected(true);
        }
        else if(mode == 2)
            italic.setSelected(true);
        else if(mode == 1)
            bold.setSelected(true);

        //should always choose one option
        switch(StyleConstants.getAlignment(visualizingPanel.getFields().get(currField).getCharacterAttributes())){
            case StyleConstants.ALIGN_LEFT:
                left.setSelected(true);break;
            case StyleConstants.ALIGN_CENTER:
                center.setSelected(true);break;
            case StyleConstants.ALIGN_RIGHT:
                right.setSelected(true);break;
        }

        fontColors.setSelectedItem(stringColors.get(visualizingPanel.getFields().get(currField).getForeground()));
        backgroundColors.setSelectedItem(stringColors.get(visualizingPanel.getFields().get(currField).getBackground()));
    }

    //update menu border curr selected
    public void updateCurrentSelectedInMenu(){
        Border currSelected = BorderFactory.createLineBorder(Color.green, 5);
        Border titledCurrSelected = BorderFactory.createTitledBorder(currSelected, "Content of : ");

        //don't want to mix border with background color
        if(menuVisualizingPanel.getFields().get(0).getBackground().equals(Color.green)){
            currSelected = BorderFactory.createLineBorder(Color.red, 5);
            titledCurrSelected = BorderFactory.createTitledBorder(currSelected, "Content of : ");
        }

        menuVisualizingPanel.getFields().get(currField).setBorder(titledCurrSelected);
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

    //use to actualize content number in next and prev button's callbacks
    private boolean updateContentNumber(){
        //case we delete menu div
        if(visualizingPanel.getFields().size() > gridN){
            visualizingPanel.scrollPane.getViewport().remove(visualizingPanel.getFields().get(currField));

            for(int i = visualizingPanel.getFields().size(); i > gridN; i--) {
                visualizingPanel.delete();
            }

            currField = gridN - 1;

            visualizingPanel.scrollPane.getViewport().add(visualizingPanel.getFields().get(currField));
            return true;
        }
        //case we add menu div
        if(visualizingPanel.getFields().size() < gridN){

            for(int i = visualizingPanel.getFields().size(); i < gridN; i++) {
                visualizingPanel.add();
            }
        }

        return false;
    }

    private void createAndAddButtonsToSelectCurrContent(JPanel container, String label){
        JPanel fullComponent = new JPanel(new FlowLayout(FlowLayout.LEFT));
        fullComponent.setBackground(Color.white);
        fullComponent.add(new JLabel(label));

        //buttons
        JButton prevBtn = new JButton(" Previous ");
        prevBtn.addActionListener(event->{
            //callback

            if(currField == 0 || updateContentNumber()) {
                return;
            }

            visualizingPanel.scrollPane.getViewport().remove(visualizingPanel.getFields().get(currField));
            visualizingPanel.scrollPane.getViewport().add(visualizingPanel.getFields().get(--currField));

            menuVisualizingPanel.getFields().get(currField + 1).setBorder(null);
            updateEditComponentStates();
            updateCurrentSelectedInMenu();
            menuVisualizingPanel.repaint();
        });
        fullComponent.add(prevBtn);

        JButton nextBtn = new JButton(" Next ");
        nextBtn.addActionListener(event->{
            //callback
            updateContentNumber();

            if(currField >= gridN - 1)
                return;

            visualizingPanel.scrollPane.getViewport().remove(visualizingPanel.getFields().get(currField));
            visualizingPanel.scrollPane.getViewport().add(visualizingPanel.getFields().get(++currField));

            menuVisualizingPanel.getFields().get(currField - 1).setBorder(null);
            updateEditComponentStates();
            updateCurrentSelectedInMenu();
            menuVisualizingPanel.repaint();
        });
        fullComponent.add(nextBtn);

        container.add(fullComponent);
    }

    private void createAndAddComboBoxFontNames(JPanel container, String label){
        JPanel fullComponent = new JPanel(new FlowLayout(FlowLayout.LEFT));
        fullComponent.setBackground(Color.white);
        fullComponent.add(new JLabel(label));
        fontNames = new JComboBox<>();
        DefaultComboBoxModel<String> fonts = new DefaultComboBoxModel<>();

        for(int i = 0; i < this.fonts.length; i++)
            fonts.addElement(this.fonts[i]);

        fontNames.setModel(fonts);
        //callback
        fontNames.addActionListener(event -> {
            Font curr = visualizingPanel.getFields().get(currField).getFont();
            visualizingPanel.getFields().get(currField).setFont(new Font(fontNames.getSelectedItem().toString(), curr.getStyle(), curr.getSize()));
        });

        fullComponent.add(fontNames);
        container.add(fullComponent);
    }

    private void createAndAddComboBoxFontSizes(JPanel container, String label){
        JPanel fullComponent = new JPanel(new FlowLayout(FlowLayout.LEFT));
        fullComponent.setBackground(Color.white);
        fullComponent.add(new JLabel(label));
        fontSizesCB = new JComboBox<>();

        for(int i = 0; i < this.fontSizes.length; i++)
            fontSizesCB.addItem(this.fontSizes[i]);

        fontSizesCB.setSelectedItem(72);
        //callback
        fontSizesCB.addActionListener(event -> {
            Font curr = visualizingPanel.getFields().get(currField).getFont();
            visualizingPanel.getFields().get(currField).setFont(new Font(curr.getName(), curr.getStyle(),
                    (Integer) fontSizesCB.getSelectedItem()));
        });

        fullComponent.add(fontSizesCB);
        container.add(fullComponent);
    }

    private void createAndAddComponentsFontStyle(JPanel container, String label){
        JPanel fullComponent = new JPanel(new FlowLayout(FlowLayout.LEFT));
        fullComponent.setBackground(Color.white);
        fullComponent.add(new JLabel(label));

        bold = new JCheckBox("BOLD", false);
        bold.setBackground(Color.white);
        italic = new JCheckBox("ITALIC", false);
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

                Font curr = visualizingPanel.getFields().get(currField).getFont();
                visualizingPanel.getFields().get(currField).setFont(new Font(curr.getName(), mode, curr.getSize()));
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

        left = new JRadioButton("LEFT", true);
        left.setBackground(Color.white);
        left.addActionListener(event->{
            StyledDocument doc = visualizingPanel.getFields().get(currField).getStyledDocument();
            SimpleAttributeSet leftSet = new SimpleAttributeSet();
            StyleConstants.setAlignment(leftSet, StyleConstants.ALIGN_LEFT);
            doc.setParagraphAttributes(0, doc.getLength(), leftSet, false);
        });

        center = new JRadioButton("CENTER", false);
        center.setBackground(Color.white);
        center.addActionListener(event->{
            StyledDocument doc = visualizingPanel.getFields().get(currField).getStyledDocument();
            SimpleAttributeSet centerSet = new SimpleAttributeSet();
            StyleConstants.setAlignment(centerSet, StyleConstants.ALIGN_CENTER);
            doc.setParagraphAttributes(0, doc.getLength(), centerSet, false);

        });

        right = new JRadioButton("RIGHT", false);
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
