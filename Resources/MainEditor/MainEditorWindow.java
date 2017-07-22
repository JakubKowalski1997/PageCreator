package MainEditor;

import MainEditor.MVCTitle.TitleController;
import MainEditor.MVCTitle.TitleModel;
import MainEditor.MVCTitle.TitleView;
import TemplateHandlerClasses.TemplateHandler;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Wiktor Łazarski on 21.07.2017.
 */
public class MainEditorWindow extends JFrame{

    //panels
    private TitleView titleView;

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

            TitleModel titleModel = titleController.getTitleModel();
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
        setLayout(new FlowLayout(FlowLayout.CENTER));
        getContentPane().setBackground(Color.white);

        createMenu();

        //adding title, manu and content panels
        titleView = new TitleView(this);
        add(titleView);

        setDefaultOptions();
    }

}