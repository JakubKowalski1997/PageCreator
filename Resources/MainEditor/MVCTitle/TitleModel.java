package MainEditor.MVCTitle;

import javax.swing.text.AttributeSet;
import java.awt.*;

/**
 * Created by Wiktor Łazarski on 21.07.2017.
 */
//enum of title label location
enum Position{
    LEFT, MIDDLE, RIGHT
}

public class TitleModel {
    //font
    private String fontName;
    private int fontSize;
    private String fontColor;
    private int fontStyle;

    //background
    private String backgroundColor;

    //position
    private Position position;

    TitleModel(){
        //set default options
        fontName = "Agency FB";
        fontSize = 8;
        //fontColor =
        fontStyle = 0;
        //backgroundColor =
        position = Position.LEFT;
    }

    //getters
    public Font getFont(){return new Font(fontName, fontStyle, fontSize);}

    public String getFontColor(){return fontColor;}

    public String getBackgroundColor(){return backgroundColor;}

    public Position getPosition(){return position;}

    //setters
    public void setFontName(String fontName) {
        this.fontName = fontName;
    }

    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
    }

    public void setFontColor(String fontColor) {
        this.fontColor = fontColor;
    }

    public void setFontStyle(int fontStyle) {
        this.fontStyle = fontStyle;
    }

    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

}
