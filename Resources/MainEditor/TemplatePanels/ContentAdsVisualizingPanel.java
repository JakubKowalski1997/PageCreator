package MainEditor.TemplatePanels;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Wiktor Łazarski on 28.07.2017.
 */
public class ContentAdsVisualizingPanel extends JPanel {

    private final int WIDTH;
    private final int HEIGHT;

    private ArrayList<JTextArea> fields;
    public ArrayList<JTextArea> getFields(){return fields;}

    public ContentAdsVisualizingPanel(int width, int height, String label){
        WIDTH = width;
        HEIGHT = height;

        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        //create and add border
        Border etched = BorderFactory.createEtchedBorder();
        Border title = BorderFactory.createTitledBorder(etched, label + " current view");

        this.setBorder(title);

        //init Array of fields
        fields = new ArrayList<>();
        JTextArea initial = new JTextArea(label);
        initial.setFont(new Font("Agency FB", Font.PLAIN, 72));
        initial.setForeground(Color.black);
        initial.setBackground(Color.white);
        fields.add(initial);

        JScrollPane scrollPane = new JScrollPane(initial);

        add(scrollPane);
    }

}
