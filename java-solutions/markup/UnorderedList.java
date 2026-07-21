package markup;

import java.util.List;

public class UnorderedList extends AllList{
    public UnorderedList(List<ListItem> items) {
        super(items);
    }
    @Override
    public void toMarkdown(StringBuilder sb) {
    }
    @Override
    public void toTex(StringBuilder sb) {
        sb.append("\\begin{itemize}");
        super.toTex(sb);
        sb.append("\\end{itemize}");
    }
}