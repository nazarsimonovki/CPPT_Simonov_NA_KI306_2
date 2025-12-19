package KI306.Simonov.Lab2;

import java.io.IOException;

/**
 * Клас {@code PrinterDriver} містить точку входу (метод {@code main})
 * для демонстрації роботи класу {@link Printer}.
 * Він показує створення, використання та автоматичне закриття
 * ресурсу принтера.
 */
public class PrinterDriver {

    /**
     * Головний метод програми.
     * Створює екземпляр {@code Printer} у блоці try-with-resources
     * та виконує послідовність операцій для демонстрації його функціональності.
     * Обробляє {@link IOException}, яке може виникнути під час роботи з принтером.
     *
     * @param args Аргументи командного рядка (не використовуються).
     */
    public static void main(String[] args) {

        try (Printer printer = new Printer()) {

            printer.showStatus();

            printer.newPrintJob("Документ_1", 10);
            printer.print();

            printer.addPaper(50);
            printer.newPrintJob("Документ_2", 20);
            printer.print();

            printer.newPrintJob("Документ_3", 999);
            printer.print();

            printer.cancelJob();

            printer.showStatus();

        } catch (IOException e) {
            System.err.println("Сталася помилка під час роботи з принтером:");
            e.printStackTrace();
        }
    }
}
