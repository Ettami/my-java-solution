package md2html;

public interface ElementHtml {
    void toHtml(StringBuilder stringBuilder);
    @Override
    public String toString();
}
