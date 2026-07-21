package md2html;

import java.util.List;

public class Strikeout extends UnderlightString{
    public Strikeout(List<ElementHtml> elementHtml){
        super(elementHtml);
    }

    @Override
    protected String start() {
        return "<s>";
    }

    @Override
    protected String end() {
        return "</s>";
    }
}
