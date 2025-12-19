package KI306.Simonov.Lab3;

/**
 * Клас {@code InkCartridge} представляє стан чорнильного картриджа.
 * Це клас даних, що зберігає колір та рівень чорнила.
 */
public class InkCartridge {
    /**
     * Колір чорнила (наприклад, "Чорний").
     */
    private String color;

    /**
     * Рівень чорнила у відсотках (0-100).
     */
    private int inkLevel;

    /**
     * Створює новий картридж із заданими параметрами.
     *
     * @param color    Колір чорнила.
     * @param inkLevel Початковий рівень чорнила.
     */
    public InkCartridge(String color, int inkLevel) {
        this.color = color;
        this.inkLevel = inkLevel;
    }

    /**
     * Конструктор копіювання. Створює глибоку копію іншого об'єкта
     * {@code InkCartridge}.
     *
     * @param other Об'єкт для копіювання.
     */
    public InkCartridge(InkCartridge other) {
        this.color = other.color;
        this.inkLevel = other.inkLevel;
    }

    /**
     * Повертає колір картриджа.
     *
     * @return Рядок, що представляє колір.
     */
    public String getColor() {
        return color;
    }

    /**
     * Повертає поточний рівень чорнила.
     *
     * @return Рівень чорнила у відсотках.
     */
    public int getInkLevel() {
        return inkLevel;
    }

    /**
     * Зменшує рівень чорнила на вказану кількість.
     * Рівень чорнила не може опуститися нижче 0.
     *
     * @param amount Кількість чорнила для використання.
     */
    public void useInk(int amount) {
        inkLevel = Math.max(inkLevel - amount, 0);
    }
}
