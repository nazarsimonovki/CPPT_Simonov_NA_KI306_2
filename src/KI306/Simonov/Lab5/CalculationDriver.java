package KI306.Simonov.Lab5;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.Scanner;
/**
 * Клас {@code CalculationDriver} є програмою-драйвером,
 * що тестує клас {@link EquationCalculator} та записує
 * результати у файл "results.txt".
 */
public class CalculationDriver {
    /**
     * Головний метод (точка входу) програми.
     *
     * @param args Аргументи командного рядка (не використовуються).
     */
    public static void main(String[] args) {

        try (Scanner scanner = new Scanner(System.in);
             PrintWriter writer = new PrintWriter(new FileWriter("results.txt", true)))
        {
            System.out.print("Введіть значення x (в радіанах): ");
            double x = scanner.nextDouble();

            EquationCalculator calculator = new EquationCalculator();
            double result = 0;

            Fio obj = new Fio();

            try {
                result = calculator.calculate(x);

                String successMessage = "При x = " + x + ", y = " + result;

                System.out.println(successMessage);
                writer.println(successMessage);

            } catch (CalculationException e) {
                String errorMessage = "ПОМИЛКА ОБЧИСЛЕННЯ: " + e.getMessage();

                System.err.println(errorMessage);
                writer.println(errorMessage);

            }

            obj.writeResTxt("textRes.txt", result);
            obj.readResTxt("textRes.txt");
            System.out.println("Результат з текстового файлу: " + obj.getResult());

            obj.writeResBin("BinRes.bin", result);
            obj.readResBin("BinRes.bin");
            System.out.println("Результат з двійкового файлу: " + obj.getResult());

        } catch (InputMismatchException e) {
            String errorMessage = "ПОМИЛКА ВВОДУ: Введено нечислове значення.";

            System.err.println(errorMessage);

        } catch (IOException e) {
            System.err.println("ПОМИЛКА ФАЙЛУ: " + e.getMessage());
        }
    }
}