package MainEditor;

import MainEditor.MVCTitle.TitleController;
import MainEditor.MVCTitle.TitleView;
import MainEditor.Template01.Template01View;
import MainEditor.Template02.Template02View;
import MainEditor.Template03.Template03View;
import TemplateHandlerClasses.TemplateHandler;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Wiktor Łazarski on 21.07.2017.
 */
public class MainEditorWindow extends JFrame{
    //panels
    private TitleView titleView;
    private Template01View template01View = null;
    private Template02View template02View = null;
    private Template03View template03View = null;

    private void setDefaultOptions(){
        //getting screen dimensions
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screen = kit.getScreenSize();

        //set default screen size and full screen on start
        setExtendedState(MAXIMIZED_BOTH);
        setBounds(0, 0, screen.width, screen.height);

        //set on close operation
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        setTitle("HTML Page Editor");
        setVisible(true);

        //set icon
        Image icon = new ImageIcon("html-icon.png").getImage();
        setIconImage(icon);
    }

    private void createMenu(){
        JMenuBar menuBar = new JMenuBar();

        setJMenuBar(menuBar);

        //file operations
        JMenu fileMenu = new JMenu("File");
        menuBar.add(fileMenu);

        JMenuItem save = new JMenuItem("Save as");
        save.addActionListener(event->{
            /*TODO:
            THIS IS THE PLACE WHERE ALL CONTROLLERS WILL CREATE MODEL AND RIGHT AFTER HTML PAGE WILL BE CREATED
            BASED ON THAT MODELS
             */


            TitleController titleController = new TitleController(titleView);
            //Konrad's input
            titleController.editHTMLCSS();
            System.out.println(TemplateHandler.getInstance().getPageTemplate().getHTMLDoc().toString());
            System.out.println(TemplateHandler.getInstance().getPageTemplate().getCSSDoc().toString());
        });
        fileMenu.add(save);

        //information
        JMenu infosMenu = new JMenu("Help");
        menuBar.add(infosMenu);

        JMenuItem tutorial = new JMenuItem("Basic tutorial");
        tutorial.addActionListener(event->{
            /*todo:
            PRINT SMALL TUTORIAL IN JOptionPane
             */
        });

        JMenuItem about = new JMenuItem("About program");
        about.addActionListener(event->{
            /*todo:
            PRINT infos about program IN JOptionPane
             */
        });

        infosMenu.add(tutorial);
        infosMenu.add(about);
    }

    public MainEditorWindow(){
        //setting layout manager
        setLayout(new FlowLayout(FlowLayout.LEFT));
        getContentPane().setBackground(Color.white);

        createMenu();

        //adding title, manu and content panels
        titleView = new TitleView(this);
        template03View = new Template03View(this);

        setDefaultOptions();
    }

    /*private void createTemplate01(){
        JPanel template011 = new JPanel(new GridBagLayout());
        template011.setBackground(Color.white);
        GridBagConstraints constraints = new GridBagConstraints();

        //menu
        MenuVisualizingPanel menuVisualizingPanel =
                new MenuVisualizingPanel(150, (int)(screen.height / 1.47), false);

        MenuControllerPanel menuControllerPanel =
                new MenuControllerPanel(menuVisualizingPanel);

        //content
        ContentAdsVisualizingPanel contentAdsVisualizingPanel
                = new ContentAdsVisualizingPanel((int)(screen.width / 1.747), (int)(screen.height / 1.47),"Content...");

        constraints.weightx = 0;
        constraints.weighty = 0;
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridheight = 1;
        constraints.anchor = GridBagConstraints.NORTH;
        template011.add(menuControllerPanel, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        template011.add(new ContentAdsControllPanel(contentAdsVisualizingPanel, "Content"), constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        JPanel fillingPanel = new JPanel();
        fillingPanel.setPreferredSize(new Dimension(1, screen.height / 5));
        fillingPanel.setBackground(Color.white);
        template011.add(fillingPanel, constraints);

        add(template011);

        JPanel template012 = new JPanel(new GridBagLayout());
        template011.setBackground(Color.white);

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridheight = 1;
        template012.add(menuControllerPanel.getVisulizingPanel());

        constraints.gridx = 1;
        constraints.gridy = 0;
        template012.add(contentAdsVisualizingPanel, constraints);

        add(template012);
    }

    private void createTemplate02(){
        JPanel template02 = new JPanel(new GridBagLayout());
        template02.setBackground(Color.white);
        GridBagConstraints constraints = new GridBagConstraints();

        //menu
        MenuVisualizingPanel menuVisualizingPanel =
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
        ContentAdsVisualizingPanel contentAdsVisualizingPanel
                = new ContentAdsVisualizingPanel((int)(screen.width / 1.53), (int)(screen.width / 3),"Content...");

        constraints.gridx = 0;
        constraints.gridy = 2;
        template02.add(new ContentAdsControllPanel(contentAdsVisualizingPanel, "Content"), constraints);

        constraints.gridx = 1;
        constraints.gridy = 1;
        template02.add(contentAdsVisualizingPanel, constraints);

        add(template02);
    }

    private void createTemplate03(){
        JPanel template011 = new JPanel(new GridBagLayout());
        template011.setBackground(Color.white);
        GridBagConstraints constraints = new GridBagConstraints();

        //menu
        MenuVisualizingPanel menuVisualizingPanel =
                new MenuVisualizingPanel(150, (int)(screen.height / 1.47), false);

        MenuControllerPanel menuControllerPanel =
                new MenuControllerPanel(menuVisualizingPanel);

        //content
        ContentAdsVisualizingPanel contentVisualizingPanel
                = new ContentAdsVisualizingPanel((int)(screen.width / 2.2), (int)(screen.height / 1.47), "Content");
        //ads
        ContentAdsVisualizingPanel adsVisualizingPanel
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

        add(template011);

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

        add(template012);
    }*/
}