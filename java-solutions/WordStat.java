import java.io.*;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.ArrayList;
import java.nio.charset.StandardCharsets;

public class WordStat {
    public static void main(String[] args)  {
        String inputFile = args[0];
        String outputFile = args[1];
        ArrayList<String> goodSymb = new ArrayList<>(Arrays.asList("'", "-", Scanner.LETTER, Scanner.DASH));
        Map<String, Integer> wordCounts = new LinkedHashMap<>();
        try (InputStream inputStream = new FileInputStream(inputFile)) {
            Scanner scanner = new Scanner(inputStream);
            scanner.setGoodSymbol(goodSymb);
            while (scanner.hasNextPart()) {
                String word = scanner.nextPart().toLowerCase();
                wordCounts.put(word, wordCounts.getOrDefault(word, 0) + 1);
            }
        }catch (FileNotFoundException e) {
            System.err.println("Input file not found: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error reading input file: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
        }
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputFile), StandardCharsets.UTF_8))) {
            for (Map.Entry<String, Integer> entry : wordCounts.entrySet()) {
                writer.write(entry.getKey() + " " + entry.getValue() + "\n");
            }
        }catch (IOException e) {
            System.err.println("Error write output file: " + e.getMessage());
        }
    }
}
