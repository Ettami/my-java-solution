package md2html;

import java.util.List;

public class Strong extends UnderlightString{
    public Strong(List<ElementHtml> elementHtml){
        super(elementHtml);
    }

    @Override
    protected String start() {
        return "<strong>";
    }

    @Override
    protected String end() {
        return "</strong>";
    }

}

