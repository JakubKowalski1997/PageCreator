package MainEditor.Template03;

import MainEditor.TemplateModel;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Wiktor Łazarski on 04.08.2017.
 */
public class Template03Model extends TemplateModel {

    private JTextPane pane;

    //setter
    public void setPane(JTextPane pane) {
        this.pane = pane;
    }

    //getters
    public String getAdsText(){
        return pane.getText();
    }
    public Font getAdsFont(){
        return pane.getFont();
    }
    public Color getAdsFontColor(){
        return pane.getForeground();
    }
    public Color getAdsBackgroundColor(){
        return pane.getBackground();
    }
    public String getAdsTextPosition(){
        //should always choose one option
        SimpleAttributeSet attributes = (SimpleAttributeSet) pane.getCharacterAttributes();
        switch(StyleConstants.getAlignment(attributes)){
            case StyleConstants.ALIGN_LEFT:
                return "left";
            case StyleConstants.ALIGN_CENTER:
                return "center";
            case StyleConstants.ALIGN_RIGHT:
                return "right";
        }

        return null;
    }
}
