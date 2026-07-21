package markup;
public interface TextElement {
    void toMarkdown(StringBuilder stringBuilder);
    void toTex(StringBuilder stringBuilder);
}
