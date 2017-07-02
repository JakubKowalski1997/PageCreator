import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Wiktor Łazarski on 02.07.2017.
 */
public class FormView extends JFrame {

    //textareadimensions
    private static final int TEXTAREA_ROWS = 8;
    private static final int TEXTAREA_COLUMNS = 20;

    //queries used in JLabels
    private ArrayList<String> queries;

    private void createAndAddComponents(ArrayList<String> queries){
        JPanel centerPanel = new JPanel();
        centerPanel.setBackground(new Color(238, 163, 65));
        centerPanel.setLayout(new GridLayout(6, 2));

        //create components loop
        for(int i = 0; i < 6; i++){
            centerPanel.add(new JLabel(queries.get(i), JLabel.LEFT));
            centerPanel.add(new JTextField());
        }

        add(centerPanel, BorderLayout.CENTER);
    }

    private void setDefaultOption(){
        //check screen size
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();

        //set window dimension and location on screen
        setSize(screenSize.width / 2, screenSize.height / 2);
        setLocationByPlatform(true);

        //set others default opt
        getContentPane().setBackground(new Color(238, 163, 65));
        setTitle("Page Creator - <head> section");
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    //Window constructor
    FormView(){
        //create ArrayList
        queries = new ArrayList<>();
        //filling queries ArrayList
        queries.add("Authors name : ");
        queries.add("Authors lastname : ");
        queries.add("Page title : ");
        queries.add("Page keywords : ");
        queries.add("Page description : ");
        queries.add("Page charset : ");

        createAndAddComponents(queries);
        setDefaultOption();

        //set icon
        /*
        * Image image = new ImageIcon(Path).getImage();
        * setIconImage(image);
        * */
    }
}