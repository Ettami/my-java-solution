package markup;

import java.util.List;

public class ListItem implements TextElement {
    private final List<ListItemElement> elements;
    public ListItem(List<ListItemElement> elements) {
        this.elements = elements;
    }
    @Override
    public void toMarkdown(StringBuilder sb) {
    }

    @Override
    public void toTex(StringBuilder sb) {
        sb.append("\\item ");
        for (ListItemElement element : elements) {
            element.toTex(sb);
        }
    }
}