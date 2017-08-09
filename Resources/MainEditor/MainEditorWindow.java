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

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import TemplateHandlerClasses.TemplateHandler;
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
    private JFileChooser chooser;

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

    private void setChooser(String title, java.util.List<Page> subpages)
    {
        chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("HTML & CSS", "html", "css");
        chooser.setFileFilter(filter);
        chooser.setDialogTitle("Save HTML page");
        chooser.setApproveButtonToolTipText("Click to save file");
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        int returnVal = chooser.showSaveDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION)
        {
            //get path
            StringBuilder path =  new StringBuilder();
            path.append(chooser.getSelectedFile().getPath());
            path.append("\\HTMLPageEditor_" + title + "\\");

            //create additional folder
            boolean success = (new File(path.toString())).mkdirs();
            if(!success)
            {
                JOptionPane.showMessageDialog(null, "Cannot save file\nPlease try again",
                        "ERROR", JOptionPane.ERROR_MESSAGE);
                getDefaultCloseOperation();
            }

            //save page
            try {
                TemplateHandler.getInstance().getPageTemplate().save(path.toString());
                for (Page subpage : subpages) {
                    subpage.saveHTMLDocument(path.toString());
                }
            }
            catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Cannot save file\nPlease try again",
                        "ERROR", JOptionPane.ERROR_MESSAGE);
                getDefaultCloseOperation();
            }
        }
    }

    private void createMenu(){
        JMenuBar menuBar = new JMenuBar();

        setJMenuBar(menuBar);

        //file operations
        JMenu fileMenu = new JMenu("File");
        menuBar.add(fileMenu);

        JMenuItem save = new JMenuItem("Save as");
        save.addActionListener(event->{
            //create HTML Page with CSS
            TitleController titleController = new TitleController(titleView);
            titleController.editHTMLCSS();

            java.util.List<Page> subpages = null;

            //next step different 3 ways to follow
            if(template01View != null){
                Template01Controller controller = new Template01Controller(template01View);

                controller.editHTMLCSS();
                subpages = controller.getSubPages();
            }

            if(template02View != null){
                Template02Controller controller = new Template02Controller(template02View);

                controller.editHTMLCSS();
                subpages = controller.getSubPages();
            }

            if(template03View != null){
                Template03Controller controller = new Template03Controller(template03View);

                controller.editHTMLCSS();
                subpages = controller.getSubPages();
            }

            //start save window
            setChooser(titleController.getTitleModel().getTitle(), subpages);

        });
        fileMenu.add(save);

        //information
        JMenu infosMenu = new JMenu("Help");
        menuBar.add(infosMenu);

        JMenuItem tutorial = new JMenuItem("Basic tutorial");
        tutorial.addActionListener(event -> {
            Font labelFont = new Font("Calibri", 3, 16);
            StringBuilder tutorialText = new StringBuilder();
            String titleAndStep1 = "<html><h1><i><strong>Basic tutorial</strong></i></h1><hr>" +
                    "<h3>Step 1 : Welcome screen</h3><hr></html>\n" +
                    "Click \"Create\" button to start creating your own HTML page \n" +
                    "Click \"Basic tutorial\" button to launch a window with this tutorial again\n" +
                    "Click \"About program\" button to read more details about HTMLPageEditor\n" +
                    "Click \"Exit\" button to close HTMLPageEditor\n";

            String form = "<html><h3>Step 2 : Metadata editor </h3><hr></html>\n" +
                    "In this window you input yours HTML page metadata like \"author's first name\", \n" +
                    "\"page's title\" etc.\n";

            String templateChooser = "<html><h3>Step 3 : Template choose </h3><hr></html>\n" +
                    "In this window you are asked to choose your HTML page template.\n" +
                    "Mark one of the three available templates to go further.\n";

            String mainEditor = "<html><h3>Step 4 : Main editor of your HTML page </h3><hr></html>\n" +
                    "Once you get there you are forced to push the boundaries of your imagination and create the best\n" +
                    "web page of all time.\n" +
                    "On the left side of screen there are panels by which you may select current attributes of each section.\n" +
                    "In case of editing text values in section you should use visualizing panels.\n" +
                    "Program seems really intuitive and we think you should not have any problems using it :).\n";

            String finalText = "<html><h3>Step 5 : Have fun and enjoy your time </h3><hr></html>\n" +
                    "We hope that by our program you will create your dreamed HTML page.\n\n";

            String thanks = "<html><h1><strong>THANK YOU FOR USING OUR PROGRAM!</strong></h1></html>";

            tutorialText.append(titleAndStep1);
            tutorialText.append(form);
            tutorialText.append(templateChooser);
            tutorialText.append(mainEditor);
            tutorialText.append(finalText);
            tutorialText.append(thanks);

            JOptionPane aboutProgramPane = new JOptionPane();
            aboutProgramPane.setFont(labelFont);
            aboutProgramPane.showMessageDialog(this, tutorialText, "About program", JOptionPane.PLAIN_MESSAGE);
        });

        JMenuItem about = new JMenuItem("About program");
        about.addActionListener(event->{
            Font labelFont = new Font("Calibri", 3, 16);
            String aboutProgram = "<html><h1><i>About program</i></h1><hr></html>\nHTMLPageCreator was developed to help people create web pages, even though they do not have any knowledge about " +
                    "html and css. \nIt allows user to create simple page based on a template. Program can also be very useful for those " +
                    "who are familiar with  \nhtml and css, but want to create page quickly. Output of program is a directory which " +
                    "contains html files for main page and subpages  \nand one css file which describes main page formatting, so future" +
                    " updates are possible. " +
                    "\n\n" +
                    "Authors:\n" +
                    "Konrad Wojtysiak, github: https://github.com/KonWoj\n" +
                    "Wiktor Łazarski, github: https://github.com/Wiktos\n" +
                    "\nIn case you find any bugs in program please inform us by email : wlazarski@interia.pl\n" +
                    "Huge thanks ! High five !";

            JOptionPane aboutProgramPane = new JOptionPane();
            aboutProgramPane.setFont(labelFont);
            aboutProgramPane.showMessageDialog(this, aboutProgram, "About program", JOptionPane.PLAIN_MESSAGE);
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