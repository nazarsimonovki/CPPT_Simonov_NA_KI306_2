package KI306.Simonov.Lab2;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

/**
 * Клас {@code Printer} імітує роботу фізичного принтера.
 * Він керує внутрішніми компонентами, такими як картридж та лоток для паперу,
 * обробляє завдання друку та веде журнал усіх операцій.
 * Клас реалізує {@link AutoCloseable} для автоматичного керування ресурсами
 * (зокрема, файлом журналу) у блоках try-with-resources.
 */
public class Printer implements AutoCloseable {
    /**
     * Внутрішній компонент, що представляє чорнильний картридж.
     */
    private InkCartridge cartridge;
    /**
     * Внутрішній компонент, що представляє лоток для паперу.
     */
    private PaperTray tray;
    /**
     * Поточне завдання друку, яке очікує на виконання.
     * {@code null}, якщо завдань немає.
     */
    private PrintJob currentJob;
    /**
     * Об'єкт для запису журналу операцій у файл.
     */
    private FileWriter log;

    /**
     * Створює новий екземпляр принтера зі стандартними компонентами:
     * 100 одиниць чорнила та 100 аркушів паперу.
     * Ініціалізує {@link FileWriter} для запису у файл "logs/printer_log.txt".
     *
     * @throws IOException якщо виникає помилка під час створення
     * або відкриття файлу журналу.
     */
    public Printer() throws IOException {
        InkCartridge defaultCartridge = new InkCartridge("Чорний", 100);
        PaperTray defaultTray = new PaperTray(100);

        this.cartridge = defaultCartridge;
        this.tray = defaultTray;

        new java.io.File("logs").mkdirs();
        this.log = new FileWriter("logs/printer_log.txt", true);

        log("Принтер ініціалізовано.");
    }
    /**
     * Створює новий екземпляр принтера на основі наданих компонентів.
     * Виконує глибоке копіювання об'єктів {@code cartridge} та {@code tray}
     * для запобігання змінам стану ззовні.
     * Ініціалізує {@link FileWriter} для запису у файл "logs/printer_log.txt".
     *
     * @param cartridge Об'єкт картриджа, який буде скопійовано.
     * @param tray      Об'єкт лотка для паперу, який буде скопійовано.
     * @throws IOException якщо виникає помилка під час створення
     * або відкриття файлу журналу.
     */
    public Printer(InkCartridge cartridge, PaperTray tray) throws IOException {
        this.cartridge = new InkCartridge(cartridge);
        this.tray = new PaperTray(tray);
        this.currentJob = null;

        new java.io.File("logs").mkdirs();
        this.log = new FileWriter("logs/printer_log.txt", true);

        log("Принтер ініціалізовано (з параметрами).");
    }
    /**
     * Замінює поточний картридж новим.
     * Виконує глибоке копіювання наданого об'єкта картриджа.
     *
     * @param newCartridge Новий картридж для встановлення.
     * @throws IOException якщо виникає помилка під час запису в журнал.
     */
    public void replaceCartridge(InkCartridge newCartridge) throws IOException {
        log("Замінено картридж: " + newCartridge.getColor());
        this.cartridge = new InkCartridge(newCartridge);
    }
    /**
     * Додає вказану кількість аркушів паперу до лотка.
     *
     * @param count Кількість аркушів для додавання.
     * @throws IOException якщо виникає помилка під час запису в журнал.
     */
    public void addPaper(int count) throws IOException {
        tray.addPaper(count);
        log("Додано папір: " + count + " аркушів.");
    }
    /**
     * Створює нове завдання друку та ставить його в чергу.
     * Завдання не буде створено, якщо бракує чорнила або паперу
     * для виконання завдання.
     *
     * @param documentName Назва документа для друку.
     * @param pages        Кількість сторінок у документі.
     * @throws IOException якщо виникає помилка під час запису в журнал.
     */
    public void newPrintJob(String documentName, int pages) throws IOException {
        if (cartridge.getInkLevel() <= 0) {
            log("Помилка: закінчилося чорнило!");
            return;
        }
        if (tray.getPaperCount() < pages) {
            log("Помилка: недостатньо паперу для друку.");
            return;
        }
        this.currentJob = new PrintJob(documentName, pages);
        log("Створено завдання: " + documentName);
    }
    /**
     * Виконує поточне завдання друку.
     * Якщо завдання відсутнє, або бракує ресурсів (паперу),
     * друк не відбудеться.
     * Після успішного друку поточне завдання видаляється (стає {@code null}).
     *
     * @throws IOException якщо виникає помилка під час запису в журнал.
     */
    public void print() throws IOException {
        if (currentJob == null) {
            log("Немає завдання для друку.");
            return;
        }
        if (tray.getPaperCount() < currentJob.getPages()) {
            log("Недостатньо паперу для завдання.");
            return;
        }
        tray.usePaper(currentJob.getPages());
        cartridge.useInk(currentJob.getPages() * 2);

        log("Надруковано документ: " + currentJob.getName());
        log("Залишилось чорнила: " + cartridge.getInkLevel() + "%");
        log("Залишилось паперу: " + tray.getPaperCount());

        currentJob = null;
    }
    /**
     * Скасовує поточне завдання друку.
     * Якщо активного завдання немає, нічого не відбувається.
     *
     * @throws IOException якщо виникає помилка під час запису в журнал.
     */
    public void cancelJob() throws IOException {
        if (currentJob != null) {
            log("Скасовано завдання: " + currentJob.getName());
            currentJob = null;
        } else {
            log("Немає завдань для скасування.");
        }
    }
    /**
     * Записує поточний стан принтера (рівень чорнила, кількість паперу,
     * поточне завдання) у файл журналу.
     *
     * @throws IOException якщо виникає помилка під час запису в журнал.
     */
    public void showStatus() throws IOException {
        log("--- Стан принтера ---");
        log(" - Рівень чорнила: " + cartridge.getInkLevel() + "%");
        log(" - Папір у лотку: " + tray.getPaperCount());
        if (currentJob != null)
            log(" - Поточне завдання: " + currentJob.getName());
        else
            log(" - Завдань немає.");
        log("---------------------");
    }
    /**
     * Внутрішній метод для запису повідомлень у файл журналу.
     * Додає поточну дату та час до кожного повідомлення.
     *
     * @param message Повідомлення для запису.
     * @throws IOException якщо виникає помилка під час запису у файл.
     */
    private void log(String message) throws IOException {
        log.write(LocalDateTime.now() + " | " + message + "\n");
        log.flush();
    }
    /**
     * Закриває файловий ресурс ({@link FileWriter}).
     * Цей метод автоматично викликається при використанні
     * {@code Printer} у блоці try-with-resources.
     *
     * @throws IOException якщо виникає помилка під час закриття файлу журналу.
     */
    public void close() throws IOException {
        log("Принтер вимкнено. Лог закрито.");
        log.close();
    }
    /**
     * Повертає поточний рівень чорнила в картриджі.
     *
     * @return Рівень чорнила у відсотках (0-100).
     */
    public int getInkLevel() {
        return cartridge.getInkLevel();
    }
    /**
     * Повертає поточну кількість паперу в лотку.
     *
     * @return Кількість аркушів паперу.
     */
    public int getPaperCount() {
        return tray.getPaperCount();
    }
}


/**
 * Клас {@code InkCartridge} представляє стан чорнильного картриджа.
 * Це клас даних, що зберігає колір та рівень чорнила.
 */
class InkCartridge {
    /**
     * Колір чорнила.
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


/**
 * Клас {@code PaperTray} представляє стан лотка для паперу.
 * Це клас даних, що зберігає кількість аркушів.
 */
class PaperTray {
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


/**
 * Клас {@code PrintJob} представляє окреме завдання для друку.
 * Це клас даних, що зберігає назву документа та кількість сторінок.
 */
class PrintJob {
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