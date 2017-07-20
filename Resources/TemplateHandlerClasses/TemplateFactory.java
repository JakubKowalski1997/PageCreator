package TemplateHandlerClasses;


/**
 * Created by Konrad on 2017-07-19.
 */

import java.util.HashMap;
import java.util.Map;

public class TemplateFactory {

    private static TemplateFactory instance = new TemplateFactory();

    public static TemplateFactory getInstance() {
        return instance;
    }

    private Map<Templates, PageTemplate> templatesMap = new HashMap<>();

    TemplateFactory() {
        registerTemplate(Templates.FIRST, "First/index.html", "First/main.css");
        registerTemplate(Templates.SECOND, "Second/index.html", "Second/main.css");
        registerTemplate(Templates.THIRD, "Third/index.html", "Third/main.css");
    }

    private void registerTemplate(Templates key, String htmlPath, String cssPath) {
        templatesMap.put(key, new PageTemplate(htmlPath, cssPath));
    }

    public PageTemplate create(Templates kind) {
        return templatesMap.get(kind);
    }
}
