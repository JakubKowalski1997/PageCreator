package MainEditor.MVCTitle;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Wiktor Łazarski on 21.07.2017.
 */
public class HTMLTitlePreviewPanel extends JPanel{

    private JTextField text;
    private Color background;

    HTMLTitlePreviewPanel(JPanel container){
        background = Color.white;

        setLayout(new BorderLayout());

        text = new JTextField("TITLE");
        text.setHorizontalAlignment(JTextField.LEFT);
        text.setFont(new Font("Agency FB", Font.PLAIN, 72));
        text.setForeground(Color.black);
        text.setBackground(background);
        text.setBorder(null);

        add(text, BorderLayout.CENTER);
        setBackground(background);

        container.add(this);
    }

    public JTextField getTextField(){return text;}
}
