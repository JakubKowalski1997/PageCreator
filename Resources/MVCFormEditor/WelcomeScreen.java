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


    private void setDefaultOption(){
        //set window dimension and location on screen - center
        setSize(screenSize.width / 2, (int)(screenSize.height / 3.7));
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

        JPanel subPane = new JPanel(new GridLayout(4, 1, 10, 10));
        subPane.setBackground(Color.white);
        JPanel marginAbove = new JPanel();
        marginAbove.setBackground(Color.white);
        subPane.add(marginAbove);

        JButton create = new JButton("Create new HTML page");
        create.addActionListener(event -> {
            this.dispose();
            JFrame nextFrame = new FormView();
        });
        subPane.add(create);

        JButton about = new JButton("About Program");
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
