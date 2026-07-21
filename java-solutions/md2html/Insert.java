package md2html;

import java.util.List;

public class Insert extends UnderlightString{
    public Insert(List<ElementHtml> elementHtml){
        super(elementHtml);
    }

    @Override
    protected String start() {
        return "<ins>";
    }

    @Override
    protected String end() {
        return "</ins>";
    }

}