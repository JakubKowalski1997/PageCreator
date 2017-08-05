package MainEditor;

import MainEditor.MVCTitle.TitleController;
import MainEditor.MVCTitle.TitleView;
import MainEditor.Template01.Template01Controller;
import MainEditor.Template01.Template01View;
import MainEditor.Template02.Template02Controller;
import MainEditor.Template02.Template02View;
import MainEditor.Template03.Template03Controller;
import MainEditor.Template03.Template03Model;
import MainEditor.Template03.Template03View;
import TemplateHandlerClasses.TemplateHandler;
import TemplateHandlerClasses.Templates;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import Utils.Page;

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

            //next step different 3 ways to follow
            if(template01View != null){
                Template01Controller controller = new Template01Controller(template01View);
                TemplateModel templateModel = controller.getModel();
                controller.editHTMLCSS();

                java.util.List<Page> subPages = controller.getSubPages();

                for (Page page : subPages) {
                    System.out.println(page.getHTMLDoc().toString());
                }
            }

            if(template02View != null){
                Template02Controller controller = new Template02Controller(template02View);
                TemplateModel templateModel = controller.getModel();
                controller.editHTMLCSS();

                java.util.List<Page> subPages = controller.getSubPages();

                for (Page page : subPages) {
                    System.out.println(page.getHTMLDoc().toString());
                }
            }

            if(template03View != null){
                Template03Controller controller = new Template03Controller(template03View);
                Template03Model templateModel = controller.getModel();
                controller.editHTMLCSS();

                java.util.List<Page> subPages = controller.getSubPages();

                for (Page page : subPages) {
                    System.out.println(page.getHTMLDoc().toString());
                }
            }

            System.out.println(TemplateHandler.getInstance().getPageTemplate().getHTMLDoc().toString());
            System.out.println(TemplateHandler.getInstance().getPageTemplate().getCSSDoc().toString());
        });
        fileMenu.add(save);

        //information
        JMenu infosMenu = new JMenu("Help");
        menuBar.add(infosMenu);

        JMenuItem tutorial = new JMenuItem("Basic tutorial");
        tutorial.addActionListener(event -> {
            /*todo:
            PRINT SMALL TUTORIAL IN JOptionPane
             */
            //Template02Controller controller = new Template02Controller(template02View);
            //controller.getModel().getContentFontColor(0);
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

    public MainEditorWindow(TemplateHandlerClasses.Templates choosen){
        //setting layout manager
        setLayout(new FlowLayout(FlowLayout.LEFT));
        getContentPane().setBackground(Color.white);

        createMenu();

        //adding title, manu and content panels
        titleView = new TitleView(this);

        switch(choosen){
            case FIRST:
                template01View = new Template01View(this); break;
            case SECOND:
                template02View = new Template02View(this); break;
            case THIRD:
                template03View = new Template03View(this); break;
        }

        setDefaultOptions();
    }
}