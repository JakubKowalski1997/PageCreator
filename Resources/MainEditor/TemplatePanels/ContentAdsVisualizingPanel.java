package MainEditor.TemplatePanels;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.util.ArrayList;

import static MainEditor.TemplatePanels.MenuVisualizingPanel.gridN;

/**
 * Created by Wiktor Łazarski on 28.07.2017.
 */
public class ContentAdsVisualizingPanel extends JPanel {

    private final int WIDTH;
    private final int HEIGHT;

    private String label;

    private ArrayList<JTextPane> fields;
    public ArrayList<JTextPane> getFields(){return fields;}

    public JScrollPane scrollPane;

    public ContentAdsVisualizingPanel(int width, int height, String label){
        WIDTH = width;
        HEIGHT = height;

        this.label = label;

        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        //create and add border
        Border etched = BorderFactory.createEtchedBorder();
        Border title = BorderFactory.createTitledBorder(etched, label + " current view");

        this.setBorder(title);

        //init Array of fields
        fields = new ArrayList<>();
        JTextPane initial = new JTextPane();
        initial.setText(label);
        initial.setFont(new Font("Agency FB", Font.PLAIN, 72));
        initial.setForeground(Color.black);
        initial.setBackground(Color.white);
        fields.add(initial);
        scrollPane = new JScrollPane(initial);

        add(scrollPane);
    }

    public void add(){
        JTextPane addPane = new JTextPane();
        addPane.setText(label);
        addPane.setFont(new Font("Agency FB", Font.PLAIN, 72));
        addPane.setForeground(Color.black);
        addPane.setBackground(Color.white);
        fields.add(addPane);
    }

    public void delete(){
        if(fields.size() == 1)
            return;

        fields.remove(fields.size() - 1);
    }
}
