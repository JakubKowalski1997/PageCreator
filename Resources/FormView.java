import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Wiktor Łazarski on 02.07.2017.
 */
public class FormView extends JFrame {

    //textarea dimensions
    private static final int TEXTAREA_ROWS = 8;
    private static final int TEXTAREA_COLUMNS = 20;

    //queries used in JLabels
    private ArrayList<String> queries;

    //textareas
    private ArrayList<JTextField> textFields;

    private void createAndAddNorthComponents(){
        JPanel northPanel = new JPanel();

        //create title label
        JLabel jLabel = new JLabel("HTML Page Editor");
        jLabel.setFont(new Font("Courier New", Font.BOLD + Font.ITALIC, 40));

        //add panel
        northPanel.setBackground(Color.white);
        northPanel.add(jLabel, SwingConstants.CENTER);
        add(northPanel, BorderLayout.NORTH);
    }

    private void createAndAddCenterComponents(ArrayList<String> queries){
        JPanel centerPanel = new JPanel();
        centerPanel.setBackground(Color.white);
        centerPanel.setLayout(new GridLayout(6, 2, 10, 30));

        //init textarea's array
        textFields = new ArrayList<>();

        //create components loop
        for(int i = 0; i < 6; i++){
            JLabel jLabel = new JLabel("  " + queries.get(i), JLabel.LEFT);
            jLabel.setFont(new Font("Courier New", Font.BOLD + Font.ITALIC, 20));
            centerPanel.add(jLabel);

            //add TextField to ArrayList and centerPanel
            textFields.add(new JTextField());
            textFields.get(i).setMargin(new Insets(0, 10, 0, 0));
            textFields.get(i).setFont(new Font("Courier New", Font.BOLD + Font.ITALIC, 20));
            centerPanel.add(textFields.get(i));
        }

        add(centerPanel, BorderLayout.CENTER);
    }

    private void createAndAddSouthComponents(){
        JPanel southPanel = new JPanel();
        southPanel.setBackground(Color.white);

        //create button
        JButton jButton = new JButton("Next step");
        jButton.addActionListener(event -> {
            //TODO LIST :
            /*
            Init download map by FormController
            FormController will check if user input datas are correct(FormValidator)
            FormController will send data to FormModel which will create Map with data
            Next step Konrad's method will create <head> section based on FormModel's Map
            (to download Map Konrad use returnFormData() - FormModel method which return Map of data)
             */
            System.exit(0);
        });

        //add button
        southPanel.add(jButton);
        add(southPanel, BorderLayout.SOUTH);
    }

    private void setDefaultOption(){
        //check screen size
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();

        //set window dimension and location on screen
        setSize(screenSize.width / 2, screenSize.height / 2);
        setLocationByPlatform(true);

        //set others default opt
        setTitle("HTML Page Creator - <head> section");
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    //Window constructor
    FormView(){
        //init ArrayList
        queries = new ArrayList<>(6);
        //filling queries ArrayList
        queries.add("Authors name : ");
        queries.add("Authors lastname : ");
        queries.add("Page title : ");
        queries.add("Page keywords : ");
        queries.add("Page description : ");
        queries.add("Page charset : ");

        //create window's components
        createAndAddNorthComponents();
        createAndAddCenterComponents(queries);
        createAndAddSouthComponents();
        setDefaultOption();

        //set icon
        Image image = new ImageIcon("html-icon.png").getImage();
        setIconImage(image);

    }
}