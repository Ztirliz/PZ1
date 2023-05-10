import java.io.*;

//Враховує пробіл
public class Task1_2 {

    public static void main() {
        String inputFilename = "horsemen.txt";
        String outputFilename = "output2.txt";

        try {
            // Відкриття файлу для читання
            BufferedReader reader = new BufferedReader(new FileReader(inputFilename));
            // Відкриття файлу для запису
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilename));

            // Зчитування рядків з файлу
            String line;
            while ((line = reader.readLine()) != null) {
                // Заміна символів окрім букв і пробілів на пробіл
                line = line.replaceAll("[^а-яА-Я\\s]", " ");
                // Заміна послідовностей пробілів на один пробіл
                line = line.replaceAll("\\s+", " ");
                // Видалення пробілів на початку та в кінці рядка
                line = line.trim();
                // Заміна прописних літер на стрічні
                line = line.toLowerCase();

                // Запис рядка у файл
                writer.write(line);
                writer.newLine();
            }

            // Закриття файлів
            reader.close();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Analysis complete. Results written to " +outputFilename);
    }
}