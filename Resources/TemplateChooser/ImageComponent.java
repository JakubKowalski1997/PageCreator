package TemplateChooser;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Wiktor Łazarski on 13.07.2017.
 */
public class ImageComponent extends JComponent {
    private Image image;

    ImageComponent(String src){
        image = new ImageIcon(src).getImage();
    }

    public void paintComponent(Graphics g){
        if(image == null)
            return;

        g.drawImage(image, 10, 10, null);
    }
}
