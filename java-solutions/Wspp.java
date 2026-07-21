import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.ArrayList;

public class Wspp {
    public static void main(String[] args) {
        String inputFile = args[0];
        String outputFile = args[1];
        Map<String, IntList> wordCounts = new LinkedHashMap<>();

        try (InputStream inputStream = new FileInputStream(inputFile)) {
            Scanner scanner = new Scanner(inputStream);
            ArrayList<String> goodSymb = new ArrayList<>(Arrays.asList("'", "-", Scanner.LETTER, Scanner.DASH));
            scanner.setGoodSymbol(goodSymb);
            int wordCounter = 0;
            while (scanner.hasNextPart()) {
                String word = scanner.nextPart().toLowerCase();
                wordCounter++;
                if (!wordCounts.containsKey(word)) {
                    wordCounts.put(word, new IntList());
                }
                wordCounts.get(word).add(wordCounter);
            }
        } catch (FileNotFoundException e) {
            System.err.println("Input file not found: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error reading input file: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
        }

        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputFile), StandardCharsets.UTF_8))) {
            for (Map.Entry<String, IntList> entry : wordCounts.entrySet()) {
                String word = entry.getKey();
                IntList list = entry.getValue();
                writer.write(word + " " + list.size());
                for (int i = 0; i < list.size(); i++) {
                    writer.write(" " + list.get(i));
                }
                writer.write("\n");
            }
        } catch (IOException e) {
            System.err.println("Error write output file: " + e.getMessage());
        }
    }
}
