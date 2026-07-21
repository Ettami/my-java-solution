package md2html;

import java.util.List;

public class Emphasis extends UnderlightString{
    public Emphasis(List<ElementHtml> elementHtml){
        super(elementHtml);
    }

    @Override
    protected String start() {
        return "<em>";
    }

    @Override
    protected String end() {
        return "</em>";
    }


}
