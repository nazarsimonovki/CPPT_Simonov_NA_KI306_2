package KI306.Simonov.Lab3;

/**
 * Клас {@code PrintJob} представляє окреме завдання для друку.
 * Це клас даних, що зберігає назву документа та кількість сторінок.
 */
public class PrintJob {
    /**
     * Назва документа.
     */
    private String name;

    /**
     * Кількість сторінок у документі.
     */
    private int pages;

    /**
     * Створює нове завдання друку.
     *
     * @param name  Назва документа.
     * @param pages Кількість сторінок.
     */
    public PrintJob(String name, int pages) {
        this.name = name;
        this.pages = pages;
    }

    /**
     * Повертає назву документа.
     *
     * @return Назва документа.
     */
    public String getName() {
        return name;
    }

    /**
     * Повертає кількість сторінок у завданні.
     *
     * @return Кількість сторінок.
     */
    public int getPages() {
        return pages;
    }
}
