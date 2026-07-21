package md2html;

import java.util.List;

public class Code extends UnderlightString{
    public Code(List<ElementHtml> elementHtml){
        super(elementHtml);
    }

    @Override
    protected String start() {
        return "<code>";
    }

    @Override
    protected String end() {
        return "</code>";
    }

}
