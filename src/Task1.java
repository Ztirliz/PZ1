import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

// Не враховує пробіл
public class Task1 {
    public static void main() {
        String inputFilename = "horsemen.txt";
        String outputFilename = "output1.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFilename));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line=line.toLowerCase();
                StringBuilder output = new StringBuilder();
                for (int i = 0; i < line.length(); i++) {
                    char c = line.charAt(i);
                    if (Character.isAlphabetic(c)) {
                            output.append(c);

                    }
                }
                writer.write(output.toString());
            }
            System.out.println("Analysis complete. Results written to " +outputFilename);
        } catch (IOException e) {
            System.err.println("Помилка: " + e.getMessage());
        }
    }
}
