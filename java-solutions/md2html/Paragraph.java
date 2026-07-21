package md2html;

import java.util.List;

public class Paragraph extends BigElement{
    public Paragraph(List<ElementHtml> elementHtmls){
        super(elementHtmls);
    }

    @Override
    protected String start() {
        return "<p>";
    }

    @Override
    protected String end() {
        return "</p>";
    }
}
