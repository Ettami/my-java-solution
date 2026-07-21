import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class WordStatLengthMiddle {
    public static void main(String[] args) {
        String inputFile = args[0];
        String outputFile = args[1];
        ArrayList<String> goodSymb = new ArrayList<>(Arrays.asList("'", "-", Scanner.LETTER, Scanner.DASH));
        Map<String, Integer> wordCounts = new LinkedHashMap<>();
        try (InputStream inputStream = new FileInputStream(inputFile)) {
            Scanner scanner = new Scanner(inputStream);
            scanner.setGoodSymbol(goodSymb);
            while (scanner.hasNextPart()) {
                String word = scanner.nextPart().toLowerCase();
                if (word.length() >= 7) {
                    word = word.substring(3, word.length() - 3);
                    wordCounts.put(word, wordCounts.getOrDefault(word, 0) + 1);
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Input file not found: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error reading input file: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
        }

        List<Map.Entry<String, Integer>> answer = new ArrayList<>(wordCounts.entrySet());
        answer.sort(Comparator.comparingInt(e -> e.getKey().length()));
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputFile), StandardCharsets.UTF_8))) {
            for (int i = 0; i < answer.size(); i++) {
                writer.write(answer.get(i).getKey() + " " + answer.get(i).getValue());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error write output file: " + e.getMessage());
        }
    }
}