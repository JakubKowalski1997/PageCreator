package MainEditor.TemplateMVC;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Wiktor Łazarski on 28.07.2017.
 */
public class ContentVisualizingPanel extends JPanel {

    private final int WIDTH;
    private final int HEIGHT;

    private ArrayList<JTextArea> fields;
    public ArrayList<JTextArea> getFields(){return fields;}

    public ContentVisualizingPanel(int width, int height){
        WIDTH = width;
        HEIGHT = height;

        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        //create and add border
        Border etched = BorderFactory.createEtchedBorder();
        Border title = BorderFactory.createTitledBorder(etched, "Content current view");
        this.setBorder(title);

        //init Array of fields
        fields = new ArrayList<>();
        JTextArea initial = new JTextArea("Content...");
        initial.setFont(new Font("Agency FB", Font.PLAIN, 72));
        initial.setForeground(Color.black);
        initial.setBackground(Color.white);
        fields.add(initial);

        add(initial);
    }

}
