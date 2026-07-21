package markup;

import java.util.List;

public class Emphasis extends StringUnderlining {
    public Emphasis(List<ParagrephElement> currentList) {
        super(currentList);
    }
    @Override
    public void toMarkdown(StringBuilder stringBuilder) {
        stringBuilder.append('*');
        super.toMarkdown(stringBuilder);
        stringBuilder.append('*');
    }
    @Override
    public void toTex(StringBuilder stringBuilder) {
        stringBuilder.append("\\emph{");
        super.toTex(stringBuilder);
        stringBuilder.append('}');
    }
}
