package markup;

import java.util.List;

public class OrderedList extends AllList {
    public OrderedList(List<ListItem> items) {
        super(items);
    }
    @Override
    public void toMarkdown(StringBuilder sb) {
    }
    @Override
    public void toTex(StringBuilder sb) {
        sb.append("\\begin{enumerate}");
        super.toTex(sb);
        sb.append("\\end{enumerate}");
    }
}