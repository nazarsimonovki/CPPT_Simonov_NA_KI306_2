package KI306.Simonov.Lab6;

import java.util.ArrayList;

/**
 * Параметризований клас Safe (Сейф).
 * Зберігає об'єкти типу T, який має бути підтипом Valuable.
 * @param <T> Тип об'єктів, що зберігаються (має реалізовувати Valuable)
 */
public class Safe<T extends Valuable> {
    private ArrayList<T> items;

    /**
     * Конструктор. Створює порожній сейф.
     */
    public Safe() {
        items = new ArrayList<>();
    }

    /**
     * Додавання елементу в сейф.
     * @param item Предмет для додавання
     */
    public void addItem(T item) {
        items.add(item);
        System.out.print("Покладено в сейф: ");
        item.print();
    }

    /**
     * Видалення елементу з сейфа за індексом.
     * @param index Індекс елементу
     */
    public void removeItem(int index) {
        if (index >= 0 && index < items.size()) {
            items.remove(index);
        }
    }

    /**
     * Отримання елементу (без видалення).
     * @param index Індекс елементу
     * @return Предмет або null
     */
    public T getItem(int index) {
        if (index >= 0 && index < items.size()) {
            return items.get(index);
        }
        return null;
    }

    /**
     * Пошук максимального елементу.
     * Знаходить найдорожчий предмет у сейфі.
     * @return Найдорожчий предмет або null, якщо сейф порожній
     */
    public T findMax() {
        if (items.isEmpty()) {
            return null;
        }

        T maxItem = items.get(0);
        for (T item : items) {
            if (item.compareTo(maxItem) > 0) {
                maxItem = item;
            }
        }
        return maxItem;
    }

    /**
     * Виводить вміст сейфа.
     */
    public void printContents() {
        System.out.println("\n--- Вміст сейфа ---");
        if (items.isEmpty()) {
            System.out.println("Сейф порожній.");
        } else {
            for (T item : items) {
                item.print();
            }
        }
        System.out.println("-------------------\n");
    }
}