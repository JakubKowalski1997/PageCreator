package MainEditor.Template01;

import MainEditor.TemplateModel;

import javax.swing.*;

/**
 * Created by Wiktor Łazarski on 04.08.2017.
 */
public class Template01Controller {

    private TemplateModel model;
    public TemplateModel getModel(){return model;}

    public Template01Controller(Template01View view){
        //creating model
        model = new TemplateModel();

        //menu attributes
        createMenuModel(view);

        //content attributes
        model.setIframes(view.contentAdsVisualizingPanel.getFields());
    }

    private void createMenuModel(Template01View view){

        //adding menu size
        model.setMenuSize(view.menuVisualizingPanel.getFields().size());

        //adding menu texts
        String[] texts = new String[view.menuVisualizingPanel.getFields().size()];
        for(int i = 0; i < texts.length; i++)
            texts[i] = view.menuVisualizingPanel.getFields().get(i).getText();

        model.setMenuTexts(texts);

        //adding menu font
        model.setMenuFont(view.menuVisualizingPanel.getFields().get(0).getFont());

        //adding menu font color
        model.setMenuFontColor(view.menuControllerPanel.getFontColors().getSelectedItem().toString());

        //adding menu background color
        model.setMenuBackgroundColor(view.menuControllerPanel.getBackgroundColors().getSelectedItem().toString());

        //adding menu text position
        switch (view.menuVisualizingPanel.getFields().get(0).getHorizontalAlignment()){
            case JTextField.LEFT :
                model.setMenuPosition("left");break;
            case JTextField.CENTER :
                model.setMenuPosition("center");break;
            case JTextField.RIGHT :
                model.setMenuPosition("right");break;
        }
    }
}
