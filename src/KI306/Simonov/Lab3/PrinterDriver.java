package KI306.Simonov.Lab3;

import java.io.IOException;

/**
 * Клас {@code PrinterDriver} містить точку входу (метод {@code main})
 * для демонстрації роботи класу {@link MultifunctionalDevice}.
 */
public class PrinterDriver {

    /**
     * Головний метод програми.
     *
     * @param args Аргументи командного рядка (не використовуються).
     */
    public static void main(String[] args) {


        try (MultifunctionalDevice mfp = new MultifunctionalDevice()) {

            mfp.showStatus();

            mfp.newPrintJob("Курсова робота.doc", 30);
            mfp.print();

            System.out.println("\n--- Тестування функцій БФП ---");

            mfp.scan("Паспорт.jpg");

            mfp.copy("Контракт.pdf", 5);

            System.out.println("\n--- Фінальний стан ---");
            mfp.showStatus();

        } catch (IOException e) {
            System.err.println("Сталася помилка під час роботи з пристроєм:");
            e.printStackTrace();
        }
    }
}