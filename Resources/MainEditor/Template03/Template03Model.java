package MainEditor.Template03;

import MainEditor.TemplateModel;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import java.awt.*;

/**
 * Created by Wiktor Łazarski on 04.08.2017.
 */
public class Template03Model extends TemplateModel {

    //extended attributes
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
        switch(StyleConstants.getAlignment(pane.getCharacterAttributes())){
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
