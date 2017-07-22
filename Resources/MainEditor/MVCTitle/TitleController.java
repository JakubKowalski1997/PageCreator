package MainEditor.MVCTitle;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

import TemplateEditor.TemplateEditor;
import TemplateEditor.TitleEditor.HTMLTitleEditor;
import TemplateHandlerClasses.TemplateHandler;

/**
 * Created by Wiktor Łazarski on 21.07.2017.
 */
public class TitleController {

    private TitleModel model;

    public TitleController(TitleView view){
        HTMLTitlePreviewPanel currVals = view.returnVisualizingPanel();
        Font currFont = currVals.getTextField().getFont();

        //creating model based on View
        model = new TitleModel();
        model.setFont(currFont);
        model.setFontColor(view.getFontColors().getSelectedItem().toString());
        model.setBackgroundColor(view.getBackgroundColors().getSelectedItem().toString());
        switch (currVals.getTextField().getHorizontalAlignment()){
            case JTextField.LEFT :
                model.setPosition("left");break;
            case JTextField.CENTER :
                model.setPosition("center");break;
            case JTextField.RIGHT :
                model.setPosition("right");break;
        }
        System.out.println(model.getFont().toString());
    }

    public void editHTMLCSS() {
        ArrayList<TemplateEditor> editors = new ArrayList<>();
        Collections.addAll(editors,
                HTMLTitleEditor.getBackgroundColorEditor(model.getBackgroundColor().toLowerCase()),
                HTMLTitleEditor.getTextColorEditor(model.getFontColor().toLowerCase()),
                HTMLTitleEditor.getFontEditor(model.getFont()),
                HTMLTitleEditor.getPositionEditor(model.getPosition())
        );

        for (TemplateEditor editor : editors) {
            editor.edit(TemplateHandler.getInstance().getPageTemplate());
        }
    }

    //return created model
    public TitleModel getTitleModel(){return model;}
}
