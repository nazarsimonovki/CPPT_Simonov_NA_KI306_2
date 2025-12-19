package KI306.Simonov.Lab6;

/**
 * Інтерфейс Valuable визначає контракт для об'єктів, які мають цінність.
 * Розширює Comparable, щоб об'єкти можна було порівнювати за вартістю.
 */
public interface Valuable extends Comparable<Valuable> {
    /**
     * Повертає вартість предмета.
     * @return вартість (ціле число)
     */
    int getValue();

    /**
     * Виводить інформацію про предмет.
     */
    void print();
}