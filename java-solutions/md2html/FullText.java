package md2html;

import java.util.List;

public class FullText extends BigElement{
    public FullText(List<ElementHtml> elementHtmls){
        super(elementHtmls);
    }

    @Override
    protected String start() {
        return "";
    }

    @Override
    protected String end() {
        return "\n";
    }
}

