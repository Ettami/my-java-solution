package md2html;

import java.util.List;

public class Title extends BigElement{
    int levelTitle;
    public Title(List<ElementHtml> elementHtmls){
        super(elementHtmls);
    }

    public void setLevelTitle(int levelTitle) {
        this.levelTitle = levelTitle;
    }

    @Override
    protected String start() {
        return "<h"+levelTitle+">";
    }

    @Override
    protected String end() {
        return "</h"+levelTitle+">";
    }
}
