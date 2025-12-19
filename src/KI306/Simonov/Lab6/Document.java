package KI306.Simonov.Lab6;

/**
 * Клас Document представляє важливі документи.
 * Реалізує інтерфейс Valuable.
 */
public class Document implements Valuable {
    private String title;
    private int importanceLevel; // Рівень важливості (еквівалент вартості)

    public Document(String title, int importanceLevel) {
        this.title = title;
        this.importanceLevel = importanceLevel;
    }

    public int getValue() {
        return importanceLevel;
    }

    public int compareTo(Valuable other) {
        return Integer.compare(this.importanceLevel, other.getValue());
    }

    public void print() {
        System.out.println("Документ: '" + title + "', Важливість: " + importanceLevel);
    }
}