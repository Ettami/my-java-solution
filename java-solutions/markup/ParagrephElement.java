package markup;

public interface ParagrephElement extends TextElement{
    void toMarkdown(StringBuilder stringBuilder);
    void toTex(StringBuilder stringBuilder);
}
