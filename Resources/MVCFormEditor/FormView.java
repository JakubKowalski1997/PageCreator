package MVCFormEditor;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import java.util.Map;
import HTMLHandlerClasses.*;

import TemplateHandlerClasses.TemplateHandler;

/**
 * Created by Wiktor Łazarski on 02.07.2017.
 */

import TemplateChooser.TemplateChooserView;

public class FormView extends JFrame {

    //queries used in JLabels
    private ArrayList<String> queries;

    //textareas
    public ArrayList<JTextField> textFields;

    private void createAndAddNorthComponents(){
        JPanel northPanel = new JPanel();

        //create title label
        JLabel jLabel = new JLabel("HTML Page metadata");
        jLabel.setFont(new Font("Courier New", Font.PLAIN, 40));

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
            jLabel.setFont(new Font("Courier New", Font.PLAIN, 20));
            centerPanel.add(jLabel);

            //add TextField to ArrayList and centerPanel
            textFields.add(new JTextField());
            textFields.get(i).setMargin(new Insets(0, 10, 0, 0));
            textFields.get(i).setFont(new Font("Courier New", Font.BOLD + Font.ITALIC, 20));
            centerPanel.add(textFields.get(i));
        }

        add(centerPanel, BorderLayout.CENTER);
    }

    //"Next Step" button's callback
    private MVCFormEditor.FormModel onExitHeadSection(){
        //collect, valid input data
        FormController formController = new FormController(this);

        if(formController.getPageMetaData() == null)
            return null;

        //create FormModel
        return new MVCFormEditor.FormModel(formController.getPageMetaData());
    }

    private ContainerTag getHeadSection(Map<HeadAttrib, String> formData) {
        ContainerTag headTag = new ContainerTag(HTMLContainerTags.HEAD);
        java.util.List<HTMLTag> tagsToInsert = getTagsBasedOnInput(formData);


        for (int i = 0; i < tagsToInsert.size(); ++i) {
            try {
                headTag.addNestedTag(tagsToInsert.get(i));
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }

        return headTag;
    }

    private java.util.List<HTMLTag> getTagsBasedOnInput(Map<HeadAttrib, String> formData) {
        java.util.List<HTMLTag> tags = new ArrayList<HTMLTag>();

        //add author's data
        java.util.List<TagAttribute> authorAtts = new ArrayList<TagAttribute>();
        authorAtts.add(new TagAttribute("name", "author"));
        authorAtts.add(new TagAttribute("content", formData.get(HeadAttrib.NAME) + " " + formData.get(HeadAttrib.SURNAME)));
        tags.add(new SelfClosingTag(HTMLSelfClosingTags.META, authorAtts));

        //add page title
        tags.add(new TextTag(HTMLTextTags.TITLE, formData.get(HeadAttrib.TITLE)));

        //add keywords
        java.util.List<TagAttribute> keywordsAtts = new ArrayList<TagAttribute>();
        keywordsAtts.add(new TagAttribute("name", "keywords"));
        keywordsAtts.add(new TagAttribute("content", formData.get(HeadAttrib.KEYWORDS)));
        tags.add(new SelfClosingTag(HTMLSelfClosingTags.META, keywordsAtts));

        //add description
        java.util.List<TagAttribute> descriptionAtts = new ArrayList<TagAttribute>();
        descriptionAtts.add(new TagAttribute("name", "decription"));
        descriptionAtts.add(new TagAttribute("content", formData.get(HeadAttrib.DESCRIPTION)));
        tags.add(new SelfClosingTag(HTMLSelfClosingTags.META, descriptionAtts));

        //add charset
        java.util.List<TagAttribute> charsetAtts = new ArrayList<TagAttribute>();
        charsetAtts.add(new TagAttribute("charset", formData.get(HeadAttrib.CHARSET)));
        tags.add(new SelfClosingTag(HTMLSelfClosingTags.META, charsetAtts));

        return tags;
    }

    private void createAndAddSouthComponents(){
        JPanel southPanel = new JPanel();
        southPanel.setBackground(Color.white);

        //create button
        JButton jButton = new JButton("Next step");
        jButton.addActionListener(event -> {

            FormModel metaData = onExitHeadSection();

            if(metaData != null) {
                TemplateHandler.getInstance().setHeadSection(getHeadSection(metaData.returnFormData()));

                System.out.println(metaData.returnFormData());

                //close current Form's window
                this.dispose();

                //start a new TemplateChooser's window
                JFrame templateChooserView = new TemplateChooserView();
            }
            else {
                //info for user to correct data
                JOptionPane.showMessageDialog(null, "Invalid input data!\nPlease, check correctness",
                        "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        });

        //add button
        southPanel.add(jButton);
        add(southPanel, BorderLayout.SOUTH);
    }

    private void setDefaultOption(){
        //check screen size
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();

        //set window dimension and location on screen - center
        setSize(screenSize.width / 2, screenSize.height / 2);
        setLocation((screenSize.width / 2) - (screenSize.width / 4), (screenSize.height / 2) - (screenSize.height / 4));

        //set others default opt
        setTitle("HTML Page Creator");
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    //Window constructor
    public FormView(){
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