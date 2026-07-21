package md2html;

public class Text implements ElementHtml {
    private String currentText;
    public Text(String currentText) {
        this.currentText = currentText;
    }
    @Override
    public void toHtml(StringBuilder stringBuilder) {
        stringBuilder.append(currentText);
    }
}
