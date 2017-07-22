package MainEditor;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.Enumeration;
import java.util.Hashtable;
import javax.swing.*;

/**
 * Created by Wiktor Łazarski on 21.07.2017.
 */
public class JColorComboBox extends JComboBox {

    private static Hashtable<String, Color> colors;

    public JColorComboBox() {
        super();
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        Enumeration colorNames = addColors().keys();
        while(colorNames.hasMoreElements()){
            String temp = colorNames.nextElement().toString();
            model.addElement(temp);
        }
        setModel(model);
        setRenderer(new ColorRenderer());
        this.setOpaque(true);
    }
    @Override
    public void setSelectedItem(Object obj) {
        super.setSelectedItem(obj);

        setBackground((Color)colors.get(obj));
        setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));
        if(obj.toString().equals("BLACK") || obj.toString().equals("DARK_GRAY")){
            setForeground(Color.white);
        }
        if(obj.toString().equals("YELLOW"))
            setForeground(Color.blue);
        if(obj.toString().equals("WHITE"))
            setForeground(Color.BLACK);
    }
    public Color getSelectedColor(){
        return colors.get(this.getSelectedItem());
    }

    private Hashtable addColors(){

        colors = new Hashtable<String, Color>();

        colors.put("BLACK", Color.BLACK);
        colors.put("WHITE", Color.WHITE);
        colors.put("BLUE", Color.BLUE);
        colors.put("GREEN", Color.GREEN);
        colors.put("YELLOW", Color.YELLOW);
        colors.put("ORANGE", Color.ORANGE);
        colors.put("CYAN", Color.CYAN);
        colors.put("DARK_GRAY", Color.DARK_GRAY);
        colors.put("GRAY", Color.GRAY);
        colors.put("RED", Color.RED);
        colors.put("PINK",Color.PINK);
        colors.put("MAGENTA", Color.MAGENTA);

        return colors;
    }

    class ColorRenderer extends JLabel implements ListCellRenderer {
        public ColorRenderer() {
            this.setOpaque(true);
        }
        public Component getListCellRendererComponent(JList list, Object key, int index, boolean isSelected, boolean cellHasFocus) {

            Color color = colors.get(key);;
            String name = key.toString();

            list.setSelectionBackground(null);
            list.setSelectionForeground(null);

            setBackground(color);
            setText(name);
            setForeground(Color.black);
            if(name.equals("BLACK") || name.equals("DARK_GRAY")){
                setForeground(Color.white);
            }
            if(name.equals("YELLOW"))
                setForeground(Color.blue);
            if(name.equals("WHITE"))
                setForeground(Color.BLACK);

            return this;
        }
    }
}
