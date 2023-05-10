import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Task3 {

    public static void main() {
        String inputFile1 = "horsemen.txt";
        String inputFile2 = "output1.txt";
        String inputFile3= "output2.txt";
        final double finalMatchingIndex = 0.0553;


        // Читання тексту з вхідного файлу
        String text1 = "";
        String text2 = "";
        String text3 = "";
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile1))) {
            String line;
            while ((line = reader.readLine()) != null) {
                text1 += line + "\n";
            }
        } catch (IOException e) {
            System.out.println("Error reading input file: " + e.getMessage());
            return;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile2))) {
            String line;
            while ((line = reader.readLine()) != null) {
                text2 += line + "\n";
            }
        } catch (IOException e) {
            System.out.println("Error reading input file: " + e.getMessage());
            return;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile3))) {
            String line;
            while ((line = reader.readLine()) != null) {
                text3 += line + "\n";
            }
        } catch (IOException e) {
            System.out.println("Error reading input file: " + e.getMessage());
            return;
        }

        double matchingIndex1 = calculateMatchingIndex(text1);
        double matchingIndex2 = calculateMatchingIndex(text2);
        double matchingIndex3 = calculateMatchingIndex(text3);

        System.out.println("Індекс збігу ВТ: "+ compareDoubles(matchingIndex1,finalMatchingIndex) + "\nІндекс збігу ВТ без пробілів: " + compareDoubles(matchingIndex2,finalMatchingIndex) + "\nІндекс збігу ВТ з пробілами: " + compareDoubles(matchingIndex3,finalMatchingIndex));


    }

    private static double calculateMatchingIndex(String text) {
        double n = text.length();
        Map<Character, Integer> charCounts = new HashMap<>();
        for (int i = 0; i < text.length() - 1; i++) {
            char c = text.charAt(i);
            charCounts.put(c, charCounts.getOrDefault(c, 0) + 1);
        }
        double matchingIndex = 0.0;
        double frequency;
        for (char c : charCounts.keySet()) {
            frequency =  charCounts.get(c) ;
            matchingIndex += (frequency*(frequency-1))/(n*(n-1));
        }

        return matchingIndex;
    }
    public static String compareDoubles(double num1, double num2) {
        double roundedNum1 = Math.round(num1 * 10000.0) / 10000.0;
        double roundedNum2 = Math.round(num2 * 10000.0) / 10000.0;
        String str = "";

        if (roundedNum1 == roundedNum2) {
           return str = roundedNum1 + " дорівнює індексу збігу для російських текстів, що я взяв з Вікіпедії - " + roundedNum2;
        }

        if (roundedNum1 > roundedNum2) {
            return str = roundedNum1 + " більше за індекс збігу для російських текстів, що я взяв з Вікіпедії - " + roundedNum2;
        }

        if (roundedNum1 < roundedNum2) {
            return str = roundedNum1 + " менший за індекс збігу для російських текстів, що я взяв з Вікіпедії - " + roundedNum2;
        }
        else {
            return str;
        }
    }
}
