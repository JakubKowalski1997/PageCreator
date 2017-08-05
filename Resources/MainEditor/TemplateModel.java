package MainEditor;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.util.ArrayList;

import static MainEditor.TemplatePanels.MenuVisualizingPanel.gridN;

/**
 * Created by Wiktor Łazarski on 04.08.2017.
 */
public class TemplateModel {

    //menu attributes
    private int menuSize;       //number of dives to place on website
    private String[] menuTexts;

    //font
    private Font font;
    private String fontColor;

    //background
    private String backgroundColor;

    //position
    private String position;

    //content attributes
    private ArrayList<JTextPane> iframes;

    //setters
    public void setMenuSize(int menuSize) {
        this.menuSize = menuSize;
    }
    public void setMenuTexts(String[] menuTexts) {
        this.menuTexts = menuTexts;
    }
    public void setMenuFont(Font font) {
        this.font = font;
    }
    public void setMenuFontColor(String fontColor) {
        this.fontColor = fontColor;
    }
    public void setMenuBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }
    public void setMenuPosition(String position) {
        this.position = position;
    }

    public void setIframes(ArrayList<JTextPane> iframes) {this.iframes = iframes;}

    //getters
    public  int     getMenuSize() {
        return menuSize;
    }
    public String[] getMenuTexts() {
        return menuTexts;
    }
    public Font     getMenuFont() {
        return font;
    }
    public String   getMenuFontColor() {
        return fontColor;
    }
    public String   getMenuBackgroundColor() {
        return backgroundColor;
    }
    public String   getMenuPosition() {
        return position;
    }

    public int getContentNumber(){
        return iframes.size();
    }
    public String getContentText(int index){
        if(checkIndex(index))
            return null;

        return iframes.get(index).getText();
    }
    public Font getContentFont(int index){
        if(checkIndex(index))
            return null;

        return iframes.get(index).getFont();
    }
    public Color getContentFontColor(int index){
        if(checkIndex(index))
            return null;

        return iframes.get(index).getForeground();
    }
    public Color getContentBackgroundColor(int index){
        if(checkIndex(index))
            return null;

        return iframes.get(index).getBackground();
    }
    public String getContentTextPosition(int index){
        if(checkIndex(index))
            return null;

        //should always choose one option
        switch(StyleConstants.getAlignment(iframes.get(index).getCharacterAttributes())){
            case StyleConstants.ALIGN_LEFT:
                return "left";
            case StyleConstants.ALIGN_CENTER:
                return "center";
            case StyleConstants.ALIGN_RIGHT:
                return "right";
        }

        return null;
    }

    private boolean checkIndex(int index){
        return (index < 0 && index >= iframes.size());
    }
}
