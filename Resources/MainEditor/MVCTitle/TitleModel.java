package MainEditor.MVCTitle;

import java.awt.*;

/**
 * Created by Wiktor Łazarski on 21.07.2017.
 */

public class TitleModel {
    //font
    private Font font;
    private String fontColor;

    //background
    private String backgroundColor;

    //position
    private String position;

    TitleModel(){
        //set default options
        font = new Font("Agency FB", Font.PLAIN, 72);
        fontColor = "BLACK";

        //background color
        backgroundColor = "WHITE";

        position = "left";
    }

    //getters
    public Font getFont(){return font;}

    public String getFontColor(){return fontColor;}

    public String getBackgroundColor(){return backgroundColor;}

    public String getPosition(){return position;}

    //setters
    public void setFont(Font font){this.font = font;}

    public void setFontColor(String fontColor) {
        this.fontColor = fontColor;
    }

    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public void setPosition(String position) {
        this.position = position;
    }

}
