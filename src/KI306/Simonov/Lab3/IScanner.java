package KI306.Simonov.Lab3;

import java.io.IOException;

/**
 * Інтерфейс {@code IScanner} визначає "контракт" для пристроїв,
 * що мають функцію сканування.
 * Він містить один метод для виконання сканування.
 */
public interface IScanner {

    /**
     * Імітує сканування документа.
     *
     * @param documentName Назва, під якою буде "збережено" скан.
     * @throws IOException Виникає при помилці логування або вводу/виводу.
     */
    void scan(String documentName) throws IOException;
}