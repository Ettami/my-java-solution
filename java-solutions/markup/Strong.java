package markup;

import java.util.List;

public class Strong extends StringUnderlining{
    public Strong(List<ParagrephElement> currentList) {
        super(currentList);
    }
    @Override
    public void toMarkdown(StringBuilder stringBuilder) {
        stringBuilder.append("__");
        super.toMarkdown(stringBuilder);
        stringBuilder.append("__");
    }
    @Override
    public void toTex(StringBuilder stringBuilder) {
        stringBuilder.append("\\textbf{");
        super.toTex(stringBuilder);
        stringBuilder.append('}');
    }

}
