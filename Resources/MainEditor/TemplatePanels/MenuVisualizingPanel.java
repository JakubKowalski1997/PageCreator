package MainEditor.TemplatePanels;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Wiktor Łazarski on 27.07.2017.
 */
public class MenuVisualizingPanel extends JPanel {

    private final int WIDTH;
    private final int HEIGHT;

    private String initFontName;

    //cnt of JTextFields in Panel
    static public int gridN;

    private ArrayList<JTextField> fields;
    public ArrayList<JTextField> getFields(){return fields;}

    private boolean horizontally;

    public MenuVisualizingPanel(int width, int height, boolean horizontally){
        WIDTH = width;
        HEIGHT = height;
        gridN = 1;
        this.horizontally = horizontally;

        if(horizontally)
            setLayout(new GridLayout(1, gridN, 5, 5));
        else
            setLayout(new GridLayout(gridN, 1, 5, 5));

        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        //create and add border
        Border etched = BorderFactory.createEtchedBorder();
        Border title = BorderFactory.createTitledBorder(etched, "Menu current view");
        this.setBorder(title);

        initFontName = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames()[0];

        //init Array of fields
        fields = new ArrayList<>();
        JTextField initial = new JTextField("Menu");
        initial.setHorizontalAlignment(JTextField.LEFT);
        initial.setFont(new Font(initFontName, Font.PLAIN, 72));
        initial.setForeground(Color.black);
        initial.setBackground(Color.white);
        Border currSelected = BorderFactory.createLineBorder(Color.green, 5);
        Border titledCurrSelected = BorderFactory.createTitledBorder(currSelected, "Content of : ");
        initial.setBorder(titledCurrSelected);
        fields.add(initial);

        add(fields.get(0));
    }

    //add menu div
    public void add(){
        gridN++;
        if(!horizontally)
            setLayout(new GridLayout(gridN, 1, 5, 5));

        JTextField newField = new JTextField("Menu", JTextField.LEFT);
        JTextField currStyle = fields.get(gridN - 2);

        //setting style
        newField.setFont(currStyle.getFont());
        newField.setBackground(currStyle.getBackground());
        newField.setForeground(currStyle.getForeground());
        newField.setHorizontalAlignment(currStyle.getHorizontalAlignment());
        newField.setBorder(null);
        fields.add(newField);

        add(fields.get(gridN - 1));
    }

    //delete menu div
    public void delete(){
        if(gridN == 1)
            return;

        gridN--;

        if(!horizontally)
            setLayout(new GridLayout(gridN, 1, 5, 5));

        remove(fields.get(gridN));
        fields.remove(gridN);
    }
}
