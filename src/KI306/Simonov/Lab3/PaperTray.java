package KI306.Simonov.Lab3;

/**
 * Клас {@code PaperTray} представляє стан лотка для паперу.
 * Це клас даних, що зберігає кількість аркушів.
 */
public class PaperTray {
    /**
     * Поточна кількість аркушів у лотку.
     */
    private int paperCount;

    /**
     * Створює лоток із заданою кількістю паперу.
     *
     * @param paperCount Початкова кількість аркушів.
     */
    public PaperTray(int paperCount) {
        this.paperCount = paperCount;
    }

    /**
     * Конструктор копіювання. Створює глибоку копію іншого об'єкта
     * {@code PaperTray}.
     *
     * @param other Об'єкт для копіювання.
     */
    public PaperTray(PaperTray other) {
        this.paperCount = other.paperCount;
    }

    /**
     * Повертає поточну кількість паперу.
     *
     * @return Кількість аркушів.
     */
    public int getPaperCount() {
        return paperCount;
    }

    /**
     * Додає папір до лотка.
     *
     * @param count Кількість аркушів для додавання.
     */
    public void addPaper(int count) {
        paperCount += count;
    }

    /**
     * Використовує (зменшує) папір з лотка.
     * Кількість паперу не може опуститися нижче 0.
     *
     * @param count Кількість аркушів для використання.
     */
    public void usePaper(int count) {
        paperCount = Math.max(paperCount - count, 0);
    }
}