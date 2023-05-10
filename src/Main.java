import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Integer number = 0;
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Введіть номер завдання \n1-Програма, яка проводить первинну фільтрацію тексту відповідно до заданого алфавіту та не враховує пробіл;\n" +
                    "2-Програма, яка проводить первинну фільтрацію тексту відповідно до заданого алфавіту та враховує пробіл;\n" +
                    "3-Обчислення частоти символів та біграм російської мови;\n" +
                    "4-Обчислення індексу відповідності тексту;\n" +
                    "5-Вихід з програми");
            if (scanner.hasNextInt()) {
                number = scanner.nextInt();


                if (number == 1) {
                    Task1.main();
                    break;
                }
                if (number == 2) {
                    Task1_2.main();
                    break;
                }
                if (number == 3) {
                    Task2.main();
                    break;
                }
                if (number == 4) {
                    Task3.main();
                    break;
                }
                if (number == 5) {
                    break;
                }
                else if (number>5) {
                    System.out.println("Неправильно введений номер програми. Спробуйте ще раз!");
                }
            }
            else{
                System.out.println("Неправильно введений тип змінної!");
            }
        }
    }
}