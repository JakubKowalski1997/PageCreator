package MainEditor.Template02;

import CSSHandlerClasses.*;
import HTMLHandlerClasses.*;
import MainEditor.TemplateModel;
import TemplateEditor.ContentEditor.HTMLContentEditor;
import TemplateEditor.IframeEditor;
import TemplateEditor.MenuEditor.HTMLMenuEditor;
import TemplateEditor.PageEditor;
import TemplateHandlerClasses.PageTemplate;
import TemplateHandlerClasses.TemplateHandler;
import TemplateHandlerClasses.Templates;
import Utils.Page;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static MainEditor.TemplatePanels.MenuVisualizingPanel.gridN;

/**
 * Created by Wiktor Łazarski on 04.08.2017.
 */
public class Template02Controller {

    private TemplateModel model;
    public TemplateModel getModel(){return model;}

    public Template02Controller(Template02View view){
        //creating model
        model = new TemplateModel();

        //menu attributes
        createMenuModel(view);

        //content attributes
        //checking if Array of JTextPane as the same size as number of menu JTextLabels
        if(view.contentAdsVisualizingPanel.getFields().size() > gridN){
            for(int i = gridN; i <= view.contentAdsVisualizingPanel.getFields().size(); i++)
                view.contentAdsVisualizingPanel.delete();
        }
        else if(view.contentAdsVisualizingPanel.getFields().size() < gridN){
            for(int i = view.contentAdsVisualizingPanel.getFields().size(); i < gridN; i++)
                view.contentAdsVisualizingPanel.add();
        }
        model.setIframes(view.contentAdsVisualizingPanel.getFields());
    }

    private void createMenuModel(Template02View view){

        //adding menu size
        model.setMenuSize(view.menuVisualizingPanel.getFields().size());

        //adding menu texts
        String[] texts = new String[view.menuVisualizingPanel.getFields().size()];
        for(int i = 0; i < texts.length; i++)
            texts[i] = view.menuVisualizingPanel.getFields().get(i).getText();

        model.setMenuTexts(texts);

        //adding menu font
        model.setMenuFont(view.menuVisualizingPanel.getFields().get(0).getFont());

        //adding menu font color
        model.setMenuFontColor(view.menuControllerPanel.getFontColors().getSelectedItem().toString());

        //adding menu background color
        model.setMenuBackgroundColor(view.menuControllerPanel.getBackgroundColors().getSelectedItem().toString());

        //adding menu text position
        switch (view.menuVisualizingPanel.getFields().get(0).getHorizontalAlignment()){
            case JTextField.LEFT :
                model.setMenuPosition("left");break;
            case JTextField.CENTER :
                model.setMenuPosition("center");break;
            case JTextField.RIGHT :
                model.setMenuPosition("right");break;
        }
    }

    public void editHTMLCSS() {
        PageTemplate template = TemplateHandler.getInstance().getPageTemplate();

        ArrayList<PageEditor> editors = new ArrayList<>();
        Collections.addAll(editors,
                IframeEditor.getEditor(model.getMenuTexts()[0] + ".html"),
                HTMLMenuEditor.getOptionEditor(Arrays.asList(model.getMenuTexts()), Templates.SECOND),
                HTMLMenuEditor.getTextColorEditor(model.getMenuFontColor()),
                HTMLMenuEditor.getBackgroundColorEditor(model.getMenuBackgroundColor()),
                HTMLMenuEditor.getFontEditor(model.getMenuFont()),
                HTMLMenuEditor.getPositionEditor(model.getMenuPosition()));

        for (PageEditor editor : editors) {
            editor.edit(template);
        }
    }

    public List<Page> getSubPages() {
        int contentsNumber = model.getContentNumber();

        PageTemplate template = TemplateHandler.getInstance().getPageTemplate();

        ArrayList<Page> subPages = new ArrayList<>();
        for (int i = 0; i < contentsNumber; i++) {
            HTMLDocument htmlDocument = new HTMLDocument();
            CSSDocument cssDocument = new CSSDocument();

            Page page = new Page(model.getMenuTexts()[i], htmlDocument, cssDocument, template.getCharset());

            ArrayList<PageEditor> editors = new ArrayList<>();
            Collections.addAll(editors,
                    HTMLContentEditor.getHTMLContentEditor(page.getCharset(), model.getContentText(i)),
                    HTMLContentEditor.getTextColorEditor(model.getContentFontColor(i)),
                    HTMLContentEditor.getBackgroundColorEditor(model.getContentBackgroundColor(i)),
                    HTMLContentEditor.getFontEditor(model.getContentFont(i)),
                    HTMLContentEditor.getPositionEditor(model.getContentTextPosition(i)),
                    HTMLContentEditor.getSubPageStyleEditor()
            );

            for (PageEditor editor : editors) {
                editor.edit(page);
            }

                subPages.add(page);
        }

        return subPages;
    }
}
