package md2html;

import java.util.List;

public class Delete extends UnderlightString{
    public Delete(List<ElementHtml> elementHtml){
        super(elementHtml);
    }

    @Override
    protected String start() {
        return "<del>";
    }

    @Override
    protected String end() {
        return "</del>";
    }

}
