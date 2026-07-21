package md2html;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Md2Html {
    public static void main(String[] args) {
        final List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(args[0]), StandardCharsets.UTF_8))
        ) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            System.err.println("Error" + e.getMessage());
            return;
        }

        List<BigElement> elements = new ArrayList<>();
        List<String> currentBlock = new ArrayList<>();

        for (int i = 0; i < lines.size(); i++) {
            String currentLine = lines.get(i);
            if (!currentLine.isEmpty()) {
                currentBlock.add(currentLine);
            }

            if (currentLine.isEmpty() || i == lines.size() - 1) {
                // ???
                if (!currentBlock.isEmpty()) {
                    elements.add(ParagraphOrTitle(currentBlock));
                    currentBlock.clear();
                }
            }
        }

        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(args[1]), StandardCharsets.UTF_8))) {
            for (BigElement element : elements) {
                // :NOTE: realloc
                StringBuilder sb = new StringBuilder();
                element.toHtml(sb);
                writer.write(sb.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error" + e.getMessage());
        }
    }

    private static BigElement ParagraphOrTitle(List<String> curentBlock) {
        String firstLine = curentBlock.getFirst();
        if (firstLine.startsWith("#")) {
            int level = 0;
            while (level < firstLine.length() && firstLine.charAt(level) == '#') {
                level++;
            }
            if (level < firstLine.length() && Character.isWhitespace(firstLine.charAt(level)))
                return createTitle(curentBlock, level);
        }
        return createParagraph(curentBlock);
    }

    private static BigElement createParagraph(List<String> currentBlock) {
        // System.lineSeparator()
        String text = String.join("\n", currentBlock);
        return new Paragraph(parseHtml(text));
    }

    private static BigElement createTitle(List<String> currentBlock, int levelTitle) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < currentBlock.size(); i++) {
            String line = currentBlock.get(i);
            if (i == 0) {
                line = line.substring(levelTitle).trim();
            }
            sb.append(line);
            if (i < currentBlock.size() - 1) {
                sb.append("\n");
            }
        }
        Title title = new Title(parseHtml(sb.toString()));
        title.setLevelTitle(levelTitle);
        return title;
    }


    // *
    // **
    public static List<ElementHtml> parseHtml(String text) {
        List<ElementHtml> result = new ArrayList<>();
        int i = 0;
        while (i < text.length()) {
            char c = text.charAt(i);
            boolean found = false;
            if (i < text.length() - 1) {
                char nextC = text.charAt(i + 1);
                if (c == '\\') {
                    result.add(new Text(replaceSpecial(String.valueOf(nextC))));
                    i += 2;
                    continue;
                }

                // :NOTE: greedy
                String two = text.substring(i, i + 2);
                if ("**".equals(two) || "__".equals(two)) {
                    int end = find(text, i + 2, two);
                    if (end != -1) {
                        result.add(new Strong(parseHtml(text.substring(i + 2, end))));
                        i = end + 2;
                        found = true;
                    }
                } else if ("--".equals(two)) {
                    int end = find(text, i + 2, "--");
                    if (end != -1) {
                        result.add(new Strikeout(parseHtml(text.substring(i + 2, end))));
                        i = end + 2;
                        found = true;
                    }
                } else if ("}}".equals(two)) {
                    int end = find(text, i + 2, "{{");
                    if (end != -1) {
                        result.add(new Delete(parseHtml(text.substring(i + 2, end))));
                        i = end + 2;
                        found = true;
                    }
                } else if ("<<".equals(two)) {
                    int end = find(text, i + 2, ">>");
                    if (end != -1) {
                        result.add(new Insert(parseHtml(text.substring(i + 2, end))));
                        i = end + 2;
                        found = true;
                    }
                }
            }

            if (!found) {
                if (c == '`') {
                    int end = find(text, i + 1, String.valueOf(c));
                    if (end != -1) {
                        result.add(new Code(parseHtml(text.substring(i + 1, end))));
                        i = end + 1;
                        found = true;
                    }
                }
                if (c == '*' || c == '_') {
                    int end = find(text, i + 1, String.valueOf(c));
                    if (end != -1) {
                        result.add(new Emphasis(parseHtml(text.substring(i + 1, end))));
                        i = end + 1;
                        found = true;
                    }
                }
            }

            if (!found) {
                result.add(new Text(replaceSpecial(String.valueOf(c))));
                i++;
            }
        }
        return result;
    }

    private static int find(String text, int start, String findString) {
        while (start < text.length()) {
            if (text.charAt(start) == '\\') {
                start += 2;
                continue;
            }
            if (text.startsWith(findString, start)) {
                if (findString.length() == 1 && start + 1 < text.length() && text.charAt(start + 1) == findString.charAt(0)) {
                    start += 2;
                    continue;
                }
                return start;
            }
            start++;
        }
        return -1;
    }

    private static String replaceSpecial(String text) {
        return text.replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;");
    }
    public static ElementHtml check(String key, List<ElementHtml> text ) {
        final HashMap<String, String> map = new HashMap<>();
        map.put("**", "Strong");
        map.put("__","Strong");
        map.put("--","Strikeout");
        map.put("}}","Delete");
        map.put("<<","Insert");
        map.put("`","Code");
        map.put("*","Emphasis");
        map.put("_","Emphasis");
        String s = map.get(key);
        if(s == "Strong"){
            Strong ans = new Strong(text);
            return ans;
        }
        if(s == "Strikeout"){
            Strikeout ans = new Strikeout(text);
            return ans;
        }
        if(s == "Delete"){
            Delete ans = new Delete(text);
            return ans;
        }
        if(s == "Code"){
            Code ans = new Code(text);
            return ans;
        }
        if(s == "Insert"){
            Insert ans = new Insert(text);
            return ans;
        }
        if(s == "Emphasis"){
            Emphasis ans = new Emphasis(text);
            return ans;
        }
        return null;
    }
}