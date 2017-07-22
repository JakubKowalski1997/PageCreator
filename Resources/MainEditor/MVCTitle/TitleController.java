package MainEditor.MVCTitle;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Wiktor Łazarski on 21.07.2017.
 */
public class TitleController {

    private TitleModel model;

    public TitleController(TitleView view){
        HTMLTitlePreviewPanel currVals = view.returnVisualizingPanel();
        Font currFont = currVals.getFont();

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
    }

    //return created model
    public TitleModel getTitleModel(){return model;}
}
