package markup;

public class Text implements ParagrephElement{
    private String currentText;
    public Text(String currentText) {
        this.currentText = currentText;
    }
    @Override
    public void toMarkdown(StringBuilder stringBuilder) {
        stringBuilder.append(currentText);
    }
    public void toTex(StringBuilder sb) {
        sb.append(currentText);
    }
}
