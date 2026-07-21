package markup;

import java.util.List;

public class Strikeout extends StringUnderlining {
    public Strikeout(List<ParagrephElement> currentList) {
        super(currentList);
    }
    @Override
    public void toMarkdown(StringBuilder stringBuilder) {
        stringBuilder.append('~');
        super.toMarkdown(stringBuilder);
        stringBuilder.append('~');
    }
    @Override
    public void toTex(StringBuilder stringBuilder) {
        stringBuilder.append("\\textst{");
        super.toTex(stringBuilder);
        stringBuilder.append('}');
    }
}
