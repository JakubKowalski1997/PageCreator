package MainEditor.TemplateMVC;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Wiktor Łazarski on 27.07.2017.
 */
public class MenuVisualizingPanel extends JPanel {

    private final int WIDTH;
    private final int HEIGHT;

    //cnt of JTextFields in Panel
    private int gridN;

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

        //init Array of fields
        fields = new ArrayList<>();
        JTextField initial = new JTextField("Menu", JTextField.LEFT);
        initial.setFont(new Font("Agency FB", Font.PLAIN, 72));
        initial.setForeground(Color.black);
        initial.setBackground(Color.white);
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
        fields.add(newField);

        add(fields.get(gridN - 1));
    }

    //delete menu div
    public void delete(){
        if(gridN == 1)
            return;

        gridN--;

        remove(fields.get(gridN));
        fields.remove(gridN);
    }
}
