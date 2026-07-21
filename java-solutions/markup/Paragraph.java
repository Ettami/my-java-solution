package markup;

import java.util.List;

public class Paragraph implements ListItemElement {
    private final List<ParagrephElement> elements;

    public Paragraph(List<ParagrephElement> elements) {
        this.elements = elements;
    }

    @Override
    public void toMarkdown(StringBuilder sb) {
        for (ParagrephElement element : elements) {
            element.toMarkdown(sb);
        }
    }

    @Override
    public void toTex(StringBuilder sb) {
        sb.append("\\par{}");
        for (ParagrephElement element : elements) {
            element.toTex(sb);
        }
    }
}
