package MainEditor.MVCTitle;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

/**
 * Created by Wiktor Łazarski on 21.07.2017.
 */
public class TitleView extends JPanel {

    //edit components
    private JPanel editComponents(){
        //new GridLayout(3, 1)
        JPanel editComponentsPanel = new JPanel();

        //create and set border
        Border etched = BorderFactory.createEtchedBorder();
        Border title = BorderFactory.createTitledBorder(etched, "Edit title");
        editComponentsPanel.setBorder(title);

        editComponentsPanel.setPreferredSize(new Dimension(400, 300));

        return editComponentsPanel;
    }

    //visualizing components
    private JPanel visualComponents(){
        JPanel visualComponentsPanel = new JPanel();

        //temporary to see positioning  : create and set border
        Border etched = BorderFactory.createEtchedBorder();
        Border title = BorderFactory.createTitledBorder(etched, "TITLE");
        visualComponentsPanel.setBorder(title);

        visualComponentsPanel.setPreferredSize(new Dimension(1500, 300));

        return visualComponentsPanel;
    }

    public TitleView(JFrame window){
        setLayout(new FlowLayout(FlowLayout.LEFT, 5 , 5));

        //add components
        add(editComponents());
        add(visualComponents());

        window.add(this);
    }

}
