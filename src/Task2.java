import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Task2 {
    public static void main() {
        // Вхідний та вихідний файли
        String inputFile = "output1.txt";
        String outputFile = "result1.txt";

        // Читання тексту з вхідного файлу
        String text = "";
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                text += line + "\n";
            }
        } catch (IOException e) {
            System.out.println("Error reading input file: " + e.getMessage());
            return;
        }

        // Підрахунок кількості символів та біграм
        int totalChars = text.length();
        Map<Character, Integer> charCounts = new HashMap<>();
        Map<String, Integer> crossingBigramCounts = new HashMap<>();
        Map<String, Integer> nonCrossingBigramCounts = new HashMap<>();
        List<Map.Entry<String, Integer>> toplist1 = new ArrayList<>();
        List<Map.Entry<String, Integer>> toplist2 = new ArrayList<>();

        int count = 0;
        for (int i = 0; i < text.length() - 1; i++) {
            char c = text.charAt(i);
            if (Character.isAlphabetic(c)) {
                charCounts.put(c, charCounts.getOrDefault(c, 0) + 1);
            }
            String bigram2 = "";
            String bigram1 = "";
            if ((i + 2) <= text.length()) {
                bigram1 = text.substring(i, i + 2);
            }
            if ((count + 2) <= text.length()) {
                bigram2 = text.substring(count, count + 2);
            }
            count += 2;

            for (int j = 0; j <= 1; j++) {
                if (bigram1.length() == 2) {
                    char h1 = bigram1.charAt(j);
                    if (!Character.isAlphabetic(h1)) {
                        break;
                    }
                    if (j == 1) {
                        crossingBigramCounts.put(bigram1, crossingBigramCounts.getOrDefault(bigram1, 0) + 1);
                    }
                }
            }
            for (int j = 0; j <= 1; j++) {
                if (bigram2.length() == 2) {
                    char h2 = bigram2.charAt(j);
                    if (!Character.isAlphabetic(h2)) {
                        break;
                    }
                    if (j == 1) {
                        nonCrossingBigramCounts.put(bigram2, nonCrossingBigramCounts.getOrDefault(bigram2, 0) + 1);
                    }
                }
            }
            charCounts = charCounts.entrySet()
                    .stream()
                    .sorted(Map.Entry.comparingByValue(Collections.reverseOrder()))
                    .collect(Collectors
                            .toMap(Map.Entry::getKey,
                                    Map.Entry::getValue,
                                    (e1, e2) -> e2,
                                    LinkedHashMap::new));
            crossingBigramCounts = crossingBigramCounts.entrySet()
                    .stream()
                    .sorted(Map.Entry.comparingByValue(Collections.reverseOrder()))
                    .collect(Collectors
                            .toMap(Map.Entry::getKey,
                                    Map.Entry::getValue,
                                    (e1, e2) -> e2,
                                    LinkedHashMap::new));
            nonCrossingBigramCounts = nonCrossingBigramCounts.entrySet()
                    .stream()
                    .sorted(Map.Entry.comparingByValue(Collections.reverseOrder()))
                    .collect(Collectors
                            .toMap(Map.Entry::getKey,
                                    Map.Entry::getValue,
                                    (e1, e2) -> e2,
                                    LinkedHashMap::new));
            toplist1 = getTopValues(crossingBigramCounts, 10);
            toplist2 = getTopValues(nonCrossingBigramCounts, 10);

        }

        // Запис результатів у вихідний файл
        try (FileWriter writer = new FileWriter(outputFile)) {
            writer.write("Text without spaces +\n\n");
            writer.write("Total characters: " + totalChars + "\n\n");
            writer.write("Character counts:\n");
            for (char c : charCounts.keySet()) {
                double frequency = (double) charCounts.get(c) / totalChars;
                writer.write(c + ": " + charCounts.get(c) + " (" + String.format("%.4f", frequency) + ")\n");
            }
            writer.write("\nTop 10 bigrams with cross:\n");
            for (int i = 0; i < toplist1.size(); i++) {
                writer.write(i + 1 + " - " + toplist1.get(i).getKey() + ": " + toplist1.get(i).getValue() + "\n");
            }
            writer.write("\nBigram counts with cross:\n");
            for (String bigram : crossingBigramCounts.keySet()) {
                double frequency = (double) crossingBigramCounts.get(bigram) / (totalChars - 1);
                writer.write(bigram + ": " + crossingBigramCounts.get(bigram) + " (" + String.format("%.4f", frequency) + ")\n");
            }
            writer.write("\nTop 10 bigrams without cross:\n");
            for (int i = 0; i < toplist2.size(); i++) {
                writer.write(i + 1 + " - " + toplist2.get(i).getKey() + ": " + toplist2.get(i).getValue() + "\n");
            }
            writer.write("\nBigram counts without cross:\n");
            for (String bigram : nonCrossingBigramCounts.keySet()) {
                double frequency = (double) nonCrossingBigramCounts.get(bigram) / (totalChars - 1);
                writer.write(bigram + ": " + nonCrossingBigramCounts.get(bigram) + " (" + String.format("%.4f", frequency) + ")\n");
            }
        } catch (IOException e) {
            System.out.println("Error writing output file: " + e.getMessage());
            return;
        }
        // Вхідний та вихідний файли
        inputFile = "output2.txt";

        // Читання тексту з вхідного файлу
        text = "";
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                text += line + "\n";
            }
        } catch (IOException e) {
            System.out.println("Error reading input file: " + e.getMessage());
            return;
        }

        // Підрахунок кількості символів та біграм
        totalChars = text.length();

        count = 0;
        for (int i = 0; i < text.length() - 1; i++) {
            char c = text.charAt(i);
            if (Character.isAlphabetic(c)) {
                charCounts.put(c, charCounts.getOrDefault(c, 0) + 1);
            }
            String bigram2 = "";
            String bigram1 = "";
            if ((i + 2) <= text.length()) {
                bigram1 = text.substring(i, i + 2);
            }
            if ((count + 2) <= text.length()) {
                bigram2 = text.substring(count, count + 2);
            }
            count += 2;

            for (int j = 0; j <= 1; j++) {
                if (bigram1.length() == 2) {
                    char h1 = bigram1.charAt(j);
                    if (!Character.isAlphabetic(h1)) {
                        break;
                    }
                    if (j == 1) {
                        crossingBigramCounts.put(bigram1, crossingBigramCounts.getOrDefault(bigram1, 0) + 1);
                    }
                }
            }
            for (int j = 0; j <= 1; j++) {
                if (bigram2.length() == 2) {
                    char h2 = bigram2.charAt(j);
                    if (!Character.isAlphabetic(h2)) {
                        break;
                    }
                    if (j == 1) {
                        nonCrossingBigramCounts.put(bigram2, nonCrossingBigramCounts.getOrDefault(bigram2, 0) + 1);
                    }
                }
            }
            charCounts = charCounts.entrySet()
                    .stream()
                    .sorted(Map.Entry.comparingByValue(Collections.reverseOrder()))
                    .collect(Collectors
                            .toMap(Map.Entry::getKey,
                                    Map.Entry::getValue,
                                    (e1, e2) -> e2,
                                    LinkedHashMap::new));
            crossingBigramCounts = crossingBigramCounts.entrySet()
                    .stream()
                    .sorted(Map.Entry.comparingByValue(Collections.reverseOrder()))
                    .collect(Collectors
                            .toMap(Map.Entry::getKey,
                                    Map.Entry::getValue,
                                    (e1, e2) -> e2,
                                    LinkedHashMap::new));
            nonCrossingBigramCounts = nonCrossingBigramCounts.entrySet()
                    .stream()
                    .sorted(Map.Entry.comparingByValue(Collections.reverseOrder()))
                    .collect(Collectors
                            .toMap(Map.Entry::getKey,
                                    Map.Entry::getValue,
                                    (e1, e2) -> e2,
                                    LinkedHashMap::new));
            toplist1 = getTopValues(crossingBigramCounts, 10);
            toplist2 = getTopValues(nonCrossingBigramCounts, 10);
        }
            // Запис результатів у вихідний файл
            try (FileWriter writer = new FileWriter(outputFile, true)) {
                writer.write("Text with spaces +\n\n");
                writer.write("Total characters: " + totalChars + "\n\n");
                writer.write("Character counts:\n");
                for (char c : charCounts.keySet()) {
                    double frequency = (double) charCounts.get(c) / totalChars;
                    writer.write(c + ": " + charCounts.get(c) + " (" + String.format("%.4f", frequency) + ")\n");
                }
                writer.write("\nTop 10 bigrams with cross:\n");
                for (int i = 0; i < toplist1.size(); i++) {
                    writer.write(i + 1 + " - " + toplist1.get(i).getKey() + ": " + toplist1.get(i).getValue() + "\n");
                }
                writer.write("\nBigram counts with cross:\n");
                for (String bigram : crossingBigramCounts.keySet()) {
                    double frequency = (double) crossingBigramCounts.get(bigram) / (totalChars - 1);
                    writer.write(bigram + ": " + crossingBigramCounts.get(bigram) + " (" + String.format("%.4f", frequency) + ")\n");
                }
                writer.write("\nTop 10 bigrams without cross:\n");
                for (int i = 0; i < toplist2.size(); i++) {
                    writer.write(i + 1 + " - " + toplist2.get(i).getKey() + ": " + toplist2.get(i).getValue() + "\n");
                }
                writer.write("\nBigram counts without cross:\n");
                for (String bigram : nonCrossingBigramCounts.keySet()) {
                    double frequency = (double) nonCrossingBigramCounts.get(bigram) / (totalChars - 1);
                    writer.write(bigram + ": " + nonCrossingBigramCounts.get(bigram) + " (" + String.format("%.4f", frequency) + ")\n");
                }
            } catch (IOException e) {
                System.out.println("Error writing output file: " + e.getMessage());
                return;
            }


            System.out.println("Analysis complete. Results written to " + outputFile);
    }

        public static List<Map.Entry<String, Integer>> getTopValues (Map < String, Integer > map,int count){
            // Створення списка і сортування мапи за значеннями в порядку спадання
            List<Map.Entry<String, Integer>> list =
                    new ArrayList<>(map.entrySet());
            Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
                public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                    return o2.getValue().compareTo(o1.getValue());
                }
            });

            // Вибірка n найбільших значень з мапи і зберігання їх у новому списку
            List<Map.Entry<String, Integer>> topList = new ArrayList<>();
            for (int i = 0; i < count && i < list.size(); i++) {
                topList.add(list.get(i));
            }

            return topList;
        }
    }


