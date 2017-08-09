package MVCFormEditor;

/**
 * Created by Konrad on 2017-08-08.
 */

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import java.awt.*;
import java.util.*;
import java.awt.event.ActionListener;

public class WelcomeScreen extends JFrame {

    //check screen size
    Toolkit kit = Toolkit.getDefaultToolkit();
    Dimension screenSize = kit.getScreenSize();


    private void setDefaultOption() {
        //set window dimension and location on screen - center
        setSize(screenSize.width / 2, (int) (screenSize.height / 3.5));
        setLocation((screenSize.width / 2) - (screenSize.width / 4), (screenSize.height / 2) - (screenSize.height / 4));

        Image icon = new ImageIcon("html-icon.png").getImage();
        setIconImage(icon);

        //set others default opt
        setTitle("HTML Page Creator");
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    //constructor
    public WelcomeScreen() {
        JPanel initial = new JPanel();
        initial.setBackground(Color.white);

        JLabel title = new JLabel("<HTML/> PageCreator");
        title.setFont(new Font("Courier New", Font.PLAIN, 70));
        title.setForeground(Color.black);

        Border titleBorder = BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.green, Color.red);
        title.setBorder(titleBorder);

        //adding title
        initial.add(title);

        JPanel subPane = new JPanel(new GridLayout(5, 1, 10, 10));
        subPane.setBackground(Color.white);
        JPanel marginAbove = new JPanel();
        marginAbove.setBackground(Color.white);
        subPane.add(marginAbove);

        JButton create = new JButton("Create new HTML page");
        create.addActionListener(event -> {
            this.dispose();
            new FormView();
        });
        subPane.add(create);

        JButton tutorial = new JButton("How to use HTMLPageCreator");
        tutorial.addActionListener(e -> {
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
                    "At the end of your work do not forget to SAVE.\n" +
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
        subPane.add(tutorial);

        JButton about = new JButton("About HTMLPageCreator");
        about.addActionListener(event -> {
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
        subPane.add(about);

        JButton exit = new JButton("Exit");
        exit.addActionListener(event -> {
            System.exit(0);
        });
        subPane.add(exit);
        initial.add(subPane);

        add(initial);
        setDefaultOption();
    }
}
