package md2html;

import java.io.*;
import java.util.*;

abstract class UnderlightString implements ElementHtml {
    protected  List<ElementHtml> smallElement;
    protected abstract String start();
    protected abstract String end();
    public UnderlightString(List<ElementHtml> smallElement) {
        this.smallElement = smallElement;
    }
    @Override
    public void toHtml(StringBuilder stringBuilder) {
        stringBuilder.append(start());
        for(ElementHtml element: smallElement){
            element.toHtml(stringBuilder);
        }
        stringBuilder.append(end());
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        toHtml(sb);
        return sb.toString();
    }
}
