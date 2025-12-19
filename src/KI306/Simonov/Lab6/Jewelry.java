package KI306.Simonov.Lab6;

/**
 * Клас Jewelry представляє коштовності.
 * Реалізує інтерфейс Valuable.
 */
public class Jewelry implements Valuable {
    private String name;
    private int price; // Вартість в умовних одиницях

    public Jewelry(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public int getValue() {
        return price;
    }

    public int compareTo(Valuable other) {
        return Integer.compare(this.price, other.getValue());
    }

    public void print() {
        System.out.println("Коштовність: " + name + ", Вартість: " + price + "$");
    }
}