package markup;

public interface ListItemElement extends TextElement {
    void toMarkdown(StringBuilder stringBuilder);
    void toTex(StringBuilder stringBuilder);
}
