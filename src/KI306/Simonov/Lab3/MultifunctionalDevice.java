package KI306.Simonov.Lab3;

import java.io.IOException;

/**
 * Клас {@code MultifunctionalDevice} (БФП) реалізує предметну область
 * "Багатофункціональний пристрій".
 *
 * Він розширює {@link Printer} (спадкування)
 * та реалізує інтерфейс {@link IScanner} (імплементація).
 *
 * Він успадковує всі можливості принтера та додає
 * функціонал сканування та копіювання.
 */
public class MultifunctionalDevice extends Printer implements IScanner {

    /**
     * Конструктор за замовчуванням.
     * Викликає конструктор батьківського класу (Printer) за замовчуванням.
     * @throws IOException
     */
    public MultifunctionalDevice() throws IOException {
        super();
        log("БФП створено (за замовчуванням).");
    }

    /**
     * Конструктор із параметрами.
     * Викликає конструктор батьківського класу (Printer) з параметрами.
     * @param cartridge Картридж для встановлення.
     * @param tray Лоток для паперу.
     * @throws IOException
     */
    public MultifunctionalDevice(InkCartridge cartridge, PaperTray tray) throws IOException {
        super(cartridge, tray);
        log("БФП створено (з параметрами).");
    }


    /**
     * {@inheritDoc}
     * Реалізує абстрактний метод getDeviceType() з суперкласу Printer.
     */
    public String getDeviceType() {
        return "Багатофункціональний пристрій (БФП)";
    }


    /**
     * {@inheritDoc}
     * Реалізує метод scan() з інтерфейсу IScanner.
     * Проводить сканування та логує дію.
     */
    public void scan(String documentName) throws IOException {
        log("Розпочато сканування...");
        log("Документ '" + documentName + "' успішно відскановано.");
    }

    /**
     * Імітує процес копіювання документа.
     * Внутрішньо, це комбінація сканування та друку.
     *
     * @param documentName Назва документа для копії.
     * @param pages        Кількість сторінок/копій.
     * @throws IOException
     */
    public void copy(String documentName, int pages) throws IOException {
        log("Отримано завдання на копіювання: " + documentName);

        scan(documentName + "_scan");

        newPrintJob(documentName + "_copy", pages);

        print();
    }
}