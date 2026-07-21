import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class WsppLast {
    public static void main(String[] args) {
        String inputFile = args[0];
        String outputFile = args[1];
        Map<String, IntList> wordPositions = new LinkedHashMap<>();
        Map<String, IntList> wordCounts = new LinkedHashMap<>();
        try (InputStream inputStream = new FileInputStream(inputFile)) {
            Scanner scanner = new Scanner(inputStream);
            ArrayList<String> goodSymb = new ArrayList<>(Arrays.asList("'", "-", Scanner.LETTER, Scanner.DIGIT, Scanner.DASH, "$", "_"));
            scanner.setGoodSymbol(goodSymb);
            int wordCount = 0;

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.isEmpty())
                    continue;
                Scanner lineScanner = new Scanner(line);
                lineScanner.setGoodSymbol(goodSymb);
                Map<String, IntList> lastInLine = new LinkedHashMap<>();
                while (lineScanner.hasNextPart()) {
                    String word = lineScanner.nextPart().toLowerCase();
                    wordCount++;
                    IntList newLine = new IntList();
                    newLine.add(wordCount);
                    lastInLine.put(word, newLine);
                    IntList counts = wordCounts.get(word);
                    if (counts == null) {
                        counts = new IntList();
                        counts.add(0);
                        wordCounts.put(word, counts);
                        wordPositions.put(word, new IntList());
                    }
                    counts.set(0, counts.get(0) + 1);
                }
                for (Map.Entry<String, IntList> e : lastInLine.entrySet()) {
                    String word = e.getKey();
                    int lastPos = e.getValue().get(e.getValue().size() - 1);
                    wordPositions.get(word).add(lastPos);
                }
            }

        } catch (FileNotFoundException e) {
            System.err.println("Input file not found " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error read input file: " + e.getMessage());
        }

        List<Map.Entry<String, IntList>> sortWords = new ArrayList<>(wordPositions.entrySet());
        sortWords.sort(Comparator.comparingInt(a -> a.getKey().length()));

        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputFile), StandardCharsets.UTF_8))) {
            for (Map.Entry<String, IntList> wordInSortWords : sortWords) {
                String word = wordInSortWords.getKey();
                IntList position = wordInSortWords.getValue();
                int countSize = wordCounts.get(word).get(0);
                writer.write(word + " " + countSize);
                for (int i = 0; i < position.size(); i++) {
                    writer.write(" " + position.get(i));
                }
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error write output: " + e.getMessage());
        }
    }
}
