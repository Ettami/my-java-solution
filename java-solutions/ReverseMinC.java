import java.util.Arrays;
import java.util.List;

public class ReverseMinC {
    private static int GROW_FACTOR = 3;
    private static int SIZE = 1;

    static int parse(final String str) {
        try {
            if (str.endsWith("o") || str.endsWith("O")) {
                final String octalStr = str.substring(0, str.length() - 1);
                return Integer.parseUnsignedInt(octalStr, 8);
            } else {
                return Integer.parseInt(str);
            }
        } catch (final NumberFormatException e) {
            return Integer.MAX_VALUE;
        }
    }

    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        // :NOTE: simplified
        final List<String> goodSymb = List.of( "-", "o", "O", Scanner.DIGIT);
        scanner.setGoodSymbol(goodSymb);
        int[][] lines = new int[SIZE][];
        int[] len_lines = new int[SIZE];
        int count_lines = 0;
        int maxColumns = 0;
        while (scanner.hasNextLine()) {
            // :NOTE: Memory
            final String line = scanner.nextLine();
            final Scanner lineScanner = new Scanner(line);
            lineScanner.setGoodSymbol(goodSymb);
            int[] currentLine = new int[SIZE];
            String curLine;
            int j = 0;
            while (lineScanner.hasNextPart()) {
                if (j >= currentLine.length) {
                    currentLine = Arrays.copyOf(currentLine,currentLine.length * GROW_FACTOR);
                }
                curLine = lineScanner.nextPart();
                currentLine[j++] = parse(curLine);
            }
            // :NOTE: count_lines
            lines[count_lines] = currentLine;
            len_lines[count_lines++] = j;
            maxColumns = Math.max(j, maxColumns);

            if (count_lines >= lines.length) {
                lines = Arrays.copyOf(lines,lines.length * GROW_FACTOR);
                len_lines = Arrays.copyOf(len_lines,len_lines.length * GROW_FACTOR);
            }
        }

        final int[] minPreviousLines = new int[maxColumns];
        Arrays.fill(minPreviousLines, Integer.MAX_VALUE);
        for (int i = count_lines - 1; i >= 0; i--) {
            for (int j = 0; j < len_lines[i]; j++) {
                lines[i][j] = Math.min(lines[i][j], minPreviousLines[j]);
                minPreviousLines[j] = Math.min(lines[i][j], minPreviousLines[j]);
            }
        }

        for (int i = 0; i < count_lines; i++) {
            for (int j = 0; j < len_lines[i]; j++) {
                System.out.print(lines[i][j] + " ");
            }
            System.out.println();
        }
    }
}
