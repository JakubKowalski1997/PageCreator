package MainEditor.Template02;

import MainEditor.TemplatePanels.ContentAdsControllPanel;
import MainEditor.TemplatePanels.ContentAdsVisualizingPanel;
import MainEditor.TemplatePanels.MenuControllerPanel;
import MainEditor.TemplatePanels.MenuVisualizingPanel;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Wiktor Łazarski on 04.08.2017.
 */
public class Template02View {

    //getting screen dimensions
    private Toolkit kit = Toolkit.getDefaultToolkit();
    private Dimension screen = kit.getScreenSize();

    //attributes
    public MenuVisualizingPanel menuVisualizingPanel;
    public ContentAdsVisualizingPanel contentAdsVisualizingPanel;


    public Template02View(JFrame window){
        JPanel template02 = new JPanel(new GridBagLayout());
        template02.setBackground(Color.white);
        GridBagConstraints constraints = new GridBagConstraints();

        //menu
        menuVisualizingPanel =
                new MenuVisualizingPanel((int)(screen.width / 1.53), 100, true);

        MenuControllerPanel menuControllerPanel =
                new MenuControllerPanel(menuVisualizingPanel);

        constraints.weightx = 0;
        constraints.weighty = 0;
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridheight = 2;
        constraints.anchor = GridBagConstraints.NORTH;
        template02.add(menuControllerPanel, constraints);

        constraints.gridx = 1;
        template02.add(menuControllerPanel.getVisulizingPanel());

        //content
        contentAdsVisualizingPanel
                = new ContentAdsVisualizingPanel((int)(screen.width / 1.53), (int)(screen.width / 3),"Content...");

        constraints.gridx = 0;
        constraints.gridy = 2;
        template02.add(new ContentAdsControllPanel(contentAdsVisualizingPanel, "Content"), constraints);

        constraints.gridx = 1;
        constraints.gridy = 1;
        template02.add(contentAdsVisualizingPanel, constraints);

        window.add(template02);
    }
}
