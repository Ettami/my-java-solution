import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Scanner  {
    private final String[] lines;
    private int curI;
    private String curLine;
    private int lineI;
    private String part;
    private boolean hasNext;
    private List<String> goodSymbol = new ArrayList<>();
    public static String DIGIT = "Digit";
    public static String LETTER = "Letter";
    public static String DASH = "Dash";
    public Scanner(InputStream inputStream ) {
        List<String> inputLines = input(inputStream);
        this.lines = inputLines.toArray(new String[0]);
        resetVar();
    }

    public Scanner(String inputString) {
        this.lines = new String[]{inputString};
        resetVar();
    }

    private void resetVar() {
        this.curI = 0;
        this.lineI = 0;
        this.part = null;
        this.hasNext = false;
        this.curLine = lines.length == 0 ? "" : lines[0];
        this.goodSymbol.add("whitespace");
    }

    private List<String> input(InputStream inputStream) {
        List<String> myLines = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            char[] buffer = new char[1024];
            StringBuilder line = new StringBuilder();
            boolean wasSpace = false;
            int read;
            while ((read = reader.read(buffer)) != -1) {
                for (int i = 0; i < read; i++) {
                    char c = buffer[i];
                    if (c == '\n') {
                        myLines.add(line.toString());
                        line.setLength(0);
                        wasSpace = false;
                    } else if (Character.isWhitespace(c)) {
                        if (!wasSpace && !line.isEmpty()) {
                            line.append(' ');
                            wasSpace = true;
                        }
                    } else {
                        line.append(c);
                        wasSpace = false;
                    }
                }
            }
            if (!line.isEmpty()) {
                myLines.add(line.toString());
            }
        } catch (IOException e) {
            System.out.println("Input Error " + e.getMessage());
        }
        return myLines;
    }

    public void setGoodSymbol(List<String> goodSymbol) {
        this.goodSymbol = goodSymbol;
    }

    private boolean goodOrBad(char c){
        String currentChar = String.valueOf(c);
        for (String s : goodSymbol) {
               if (
                        s.equals(DIGIT) && Character.isDigit(c) ||
                        s.equals(LETTER) && Character.isLetter(c) ||
                        s.equals(DASH) && Character.getType(c) == Character.DASH_PUNCTUATION ||
                        s.equals("whitespace")||
                        s.equals(currentChar)
                ) {
                    return !Character.isWhitespace(c);
                }
            }
        return false;
    }

    public String nextLine() {
        if (hasNextLine()) {
            String answer = lines[curI];
            curI++;
            lineI = 0;
            return answer;
        }
        return "";
    }

    public boolean hasNextLine() {
        return curI < lines.length;
    }

    private void removeSymb() {
        if (curLine == null || curLine.isEmpty())
            return;

        while (lineI < curLine.length()) {
            char c = curLine.charAt(lineI);
            if (goodOrBad(c))
                break;
            else
                lineI++;
        }
    }

    public boolean hasNextPart() {
        if (hasNext && part != null) {
            return true;
        }
        while (curLine != null) {
            if (lineI >= curLine.length()) {
                curI++;
                if (curI >= lines.length) {
                    curLine = null;
                    break;
                }
                curLine = lines[curI];
                lineI = 0;
            }
            removeSymb();
            if (curLine == null || lineI >= curLine.length()) {
                if (curLine == null) break;
                continue;
            }

            StringBuilder cur_part = new StringBuilder();
            while (lineI < curLine.length()) {
                char c = curLine.charAt(lineI);
                if (goodOrBad(c)) {
                    cur_part.append(c);
                    lineI++;
                } else
                    break;
            }

            if (cur_part.length() > 0) {
                part = cur_part.toString();
                hasNext = true;
                return true;
            }
        }
        hasNext = false;
        return false;
    }

    public boolean hasNextInt() {
        if (!hasNextPart())
            return false;
        try {
            Integer.parseInt(part);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public int nextInt() {
        if (hasNextInt()) {
            int result = Integer.parseInt(part);
            part = null;
            hasNext = false;
            return result;
        } else {
            throw new Error("No integers");
        }
    }

    public String nextPart() {
        if (!hasNextPart())
            throw new Error("No part");
        else{
            String result = part;
            part = null;
            hasNext = false;
            return result;
        }
    }
}