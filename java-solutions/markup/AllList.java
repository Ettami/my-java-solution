package markup;

import java.util.List;

public abstract class AllList implements ListItemElement{
    protected List<ListItem> currentList;
    public AllList(List<ListItem> currentList) {
        this.currentList = currentList;
    }
    @Override
    public void toMarkdown(StringBuilder stringBuilder) {
    }
    @Override
    public void toTex(StringBuilder stringBuilder) {
        for (ListItem element : currentList) {
            element.toTex(stringBuilder);
        }
    }
}
