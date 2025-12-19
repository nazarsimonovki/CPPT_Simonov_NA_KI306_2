package KI306.Simonov.Lab3;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

/**
 * АБСТРАКТНИЙ клас {@code Printer} імітує роботу фізичного пристрою друку.
 * Він слугує базою (суперкласом) для інших пристроїв друку.
 * Він керує внутрішніми компонентами, обробляє завдання друку та веде журнал.
 * Клас реалізує {@link AutoCloseable} для автоматичного керування ресурсами.
 */
public abstract class Printer implements AutoCloseable {

    /** Внутрішній компонент, що представляє чорнильний картридж. */
    private InkCartridge cartridge;

    /** Внутрішній компонент, що представляє лоток для паперу. */
    private PaperTray tray;

    /** Поточне завдання друку, яке очікує на виконання. */
    private PrintJob currentJob;

    /** Об'єкт для запису журналу операцій у файл. */
    private FileWriter log;

    /**
     * Конструктор із параметрами (з глибоким копіюванням).
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

        log("Абстрактний пристрій ініціалізовано (з параметрами).");
    }

    /**
     * Конструктор за замовчуванням.
     * Викликає інший конструктор для ініціалізації зі стандартними компонентами.
     * @throws IOException
     */
    public Printer() throws IOException {
        this(new InkCartridge("Чорний", 100), new PaperTray(100));
        log("Абстрактний пристрій ініціалізовано (за замовчуванням).");
    }


    /**
     * Абстрактний метод, який повертає
     * тип пристрою.
     *
     * @return Рядок, що описує тип пристрою.
     */
    public abstract String getDeviceType();



    /**
     * Записує поточний стан пристрою у файл журналу.
     *
     * @throws IOException якщо виникає помилка під час запису в журнал.
     */
    public void showStatus() throws IOException {
        log("--- Стан пристрою ---");
        log(" - Тип: " + getDeviceType()); // Виклик абстрактного методу
        log(" - Рівень чорнила: " + getInkLevel() + "%");
        log(" - Папір у лотку: " + getPaperCount());
        if (currentJob != null)
            log(" - Поточне завдання: " + currentJob.getName());
        else
            log(" - Завдань немає.");
        log("---------------------");
    }

    /**
     * Створює нове завдання друку.
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
        log("Залишилось паперу: " + getPaperCount());

        currentJob = null;
    }

    /**
     * Внутрішній метод для запису повідомлень у файл журналу.
     * 'protected' надає доступ до цього методу підкласам.
     *
     * @param message Повідомлення для запису.
     * @throws IOException якщо виникає помилка під час запису у файл.
     */
    protected void log(String message) throws IOException {
        log.write(LocalDateTime.now() + " | " + message + "\n");
        log.flush();
    }

    public int getInkLevel() {
        return cartridge.getInkLevel();
    }

    public int getPaperCount() {
        return tray.getPaperCount();
    }

    /**
     * Закриває файловий ресурс ({@link FileWriter}).
     *
     * @throws IOException якщо виникає помилка під час закриття файлу журналу.
     */
    public void close() throws IOException {
        log("Пристрій вимкнено. Лог закрито.");
        log.close();
    }
}