import java.util.Arrays;
import java.util.Scanner;
public class Reverse {
        private static int GROW_FACTOR = 2;
        private static int SIZE = 1;
        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            int[][] lines = new int[SIZE][];
            int[] len_lines = new int[SIZE];
            int count_lines = 0;
            int maxColumns = 0;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Scanner lineScanner = new Scanner(line);
                int[] currentLine = new int[SIZE];
                int j = 0;

                while (lineScanner.hasNextInt()) {
                    if (j >= currentLine.length) {
                        currentLine = Arrays.copyOf(currentLine,currentLine.length * GROW_FACTOR);
                    }
                    currentLine[j] = lineScanner.nextInt();
                    j++;
                }

                lines[count_lines] = currentLine;
                len_lines[count_lines] = j;
                maxColumns = Math.max(j,maxColumns);
                count_lines++;

                if (count_lines >= lines.length) {
                    lines = Arrays.copyOf(lines,lines.length * GROW_FACTOR);
                    len_lines = Arrays.copyOf(len_lines,len_lines.length * GROW_FACTOR);
                }
            }


            for (int i = count_lines-1; i >= 0; i--) {
                for (int j = len_lines[i]-1; j >=0; j--) {
                    System.out.print(lines[i][j] + " ");
                }
                System.out.println();
            }
        }
    }
