package md2html;

import java.util.List;

abstract class BigElement implements ElementHtml {
    protected final List<ElementHtml> elementHtmls;
    public BigElement(List<ElementHtml> elementHtmls) {
        this.elementHtmls = elementHtmls;
    }
    protected abstract String start();
    protected abstract String end();
    @Override
    public void toHtml(StringBuilder stringBuilder) {
        stringBuilder.append(start());
        for(ElementHtml element: elementHtmls){
            element.toHtml(stringBuilder);
        }
        stringBuilder.append(end());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        toHtml(sb);
        return sb.toString();
    }
}
