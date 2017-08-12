package TemplateEditor;

/**
 * Created by Konrad on 2017-08-12.
 */

import java.awt.*;
import java.util.Map;
import java.util.HashMap;

public class ColorFactory {
    private static ColorFactory instance = new ColorFactory();

    private Map<String, String> colorMap = new HashMap<>();

    public static ColorFactory getInstance() {
        return instance;
    }

    public void registerColor(String color, Color awtColor) {
        colorMap.put(color, BasicHTMLEditors.buildRgbColor(awtColor));
    }

    public String create(String color) {
        return colorMap.get(color);
    }
}
