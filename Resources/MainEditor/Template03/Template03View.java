package MainEditor.Template03;

import MainEditor.TemplatePanels.ContentAdsControllPanel;
import MainEditor.TemplatePanels.ContentAdsVisualizingPanel;
import MainEditor.TemplatePanels.MenuControllerPanel;
import MainEditor.TemplatePanels.MenuVisualizingPanel;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Wiktor Łazarski on 04.08.2017.
 */
public class Template03View {

    //getting screen dimensions
    private Toolkit kit = Toolkit.getDefaultToolkit();
    private Dimension screen = kit.getScreenSize();

    //atributes
    public MenuVisualizingPanel menuVisualizingPanel;
    public MenuControllerPanel menuControllerPanel;
    public ContentAdsVisualizingPanel contentVisualizingPanel;
    public ContentAdsVisualizingPanel adsVisualizingPanel;

    public Template03View(JFrame window){
        JPanel template011 = new JPanel(new GridBagLayout());
        template011.setBackground(Color.white);
        GridBagConstraints constraints = new GridBagConstraints();

        //menu
        menuVisualizingPanel =
                new MenuVisualizingPanel(150, (int)(screen.height / 1.47), false);

        menuControllerPanel =
                new MenuControllerPanel(menuVisualizingPanel);

        //content
        contentVisualizingPanel
                = new ContentAdsVisualizingPanel((int)(screen.width / 2.2), (int)(screen.height / 1.47), "Content");
        //ads
        adsVisualizingPanel
                = new ContentAdsVisualizingPanel((int)(screen.width / 8.5), (int)(screen.height / 1.47), "Ads");

        constraints.weightx = 0;
        constraints.weighty = 0;
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridheight = 1;
        constraints.anchor = GridBagConstraints.NORTH;
        template011.add(menuControllerPanel, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        template011.add(new ContentAdsControllPanel(contentVisualizingPanel, "Content"), constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        template011.add(new ContentAdsControllPanel(adsVisualizingPanel, "Ads"), constraints);

        window.add(template011);

        JPanel template012 = new JPanel(new GridBagLayout());
        template011.setBackground(Color.white);

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridheight = 1;
        template012.add(menuControllerPanel.getVisulizingPanel());

        constraints.gridx = 1;
        constraints.gridy = 0;
        template012.add(contentVisualizingPanel, constraints);

        constraints.gridx = 2;
        constraints.gridy = 0;
        template012.add(adsVisualizingPanel, constraints);

        window.add(template012);
    }
}
