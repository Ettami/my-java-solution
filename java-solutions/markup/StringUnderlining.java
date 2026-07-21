package markup;

import java.util.List;

public abstract class StringUnderlining implements ParagrephElement {
    protected List<ParagrephElement> currentList;
    public StringUnderlining(List<ParagrephElement> currentList) {
        this.currentList = currentList;
    }
    @Override
    public void toMarkdown(StringBuilder stringBuilder) {
        for (ParagrephElement element : currentList) {
            element.toMarkdown(stringBuilder);
        }
    }
    @Override
    public void toTex(StringBuilder stringBuilder) {
        for (ParagrephElement element : currentList) {
            element.toTex(stringBuilder);
        }
    }
}
