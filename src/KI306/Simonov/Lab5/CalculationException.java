/**
 * Пакет KI306.Simonov.Lab4 містить класи для лабораторної роботи №4,
 * що демонструє обчислення виразу з обробкою виняткових ситуацій
 * та записом результату у файл.
 */
package KI306.Simonov.Lab5;

/**
 * Клас {@code CalculationException} є користувацьким виключенням,
 * що виникає при помилках під час обчислення виразу.
 * (Наприклад, ділення на нуль або невизначеність функції).
 */
public class CalculationException extends Exception {

    /**
     * Конструктор за замовчуванням.
     */
    public CalculationException() {
        super();
    }

    /**
     * Конструктор, що приймає повідомлення про помилку.
     *
     * @param message Повідомлення, що описує причину виключення.
     */
    public CalculationException(String message) {
        super(message);
    }
}