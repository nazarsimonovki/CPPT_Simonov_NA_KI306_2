package KI306.Simonov.Lab6;

/**
 * Клас-драйвер для демонстрації роботи класу Safe.
 */
public class SafeDriver {
    public static void main(String[] args) {
        Safe<? super Valuable> mySafe = new Safe<Valuable>();

        System.out.println(">>> Заповнення сейфа:");

        mySafe.addItem(new Jewelry("Золота каблучка", 500));
        mySafe.addItem(new Jewelry("Діамантове кольє", 5000));

        mySafe.addItem(new Document("Акції Apple", 1200));
        mySafe.addItem(new Document("Заповіт", 9999)); // Дуже важливий документ
        mySafe.addItem(new Jewelry("Срібна ложка", 50));

        ((Safe<Valuable>)mySafe).printContents();

        Valuable mostValuable = ((Safe<Valuable>)mySafe).findMax();

        System.out.println(">>> Найцінніший предмет у сейфі:");
        if (mostValuable != null) {
            mostValuable.print();
        }

        ((Safe<Valuable>)mySafe).removeItem(4);

        System.out.println("\n>>> Після видалення заповіту:");
        ((Safe<Valuable>)mySafe).printContents();

    }
}