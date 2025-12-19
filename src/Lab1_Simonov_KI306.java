import java.io.*;
import java.util.*;

/**
 * Клас Lab1_Simonov_KI306 реалізує програму побудови ромба із заданого символу.
 * Програма створює зубчастий масив, який відображає ромб у квадратній матриці,
 * виводить результат на екран і записує у файл "MyFile.txt".
 * Лабораторна робота №1
 */
public class Lab1_Simonov_KI306 {

    /**
     * Точка входу в програму.
     *
     * @param args аргументи командного рядка (не використовуються)
     * @throws FileNotFoundException якщо не вдається створити файл
     */
    public static void main(String[] args) throws FileNotFoundException {

        int nRows;
        char[][] arr;
        String filler;

        Scanner in = new Scanner(System.in);
        File dataFile = new File("MyFile.txt");
        PrintWriter fout = new PrintWriter(dataFile);

        System.out.print("Введіть розмір квадратної матриці (непарне число): ");
        nRows = in.nextInt();
        in.nextLine(); // очищення буфера

        // Перевірка на коректність введення
        if (nRows < 1 || nRows % 2 == 0) {
            System.out.println("Помилка: розмір має бути непарним і більшим за 1!");
            fout.println("Помилка: розмір має бути непарним і більшим за 1!");
            fout.close();
            return;
        }

        System.out.print("Введіть символ-заповнювач: ");
        filler = in.nextLine();

        if (filler.length() != 1) {
            System.out.println("Помилка: потрібно ввести рівно один символ!");
            fout.println("Помилка: потрібно ввести рівно один символ!");
            fout.close();
            return;
        }

        char symbol = filler.charAt(0);
        arr = generateRombArray(nRows, symbol);

        // Виведення ромба на екран і у файл
        for (char[] row : arr) {
            for (char c : row) {
                System.out.print(c);
                fout.print(c);
            }
            System.out.println();
            fout.println();
        }

        fout.flush();
        fout.close();

        System.out.println("\nРомб успішно збережено у файл MyFile.txt");
    }

    /**
     * Метод створює зубчастий масив, що містить символи у формі ромба.
     *
     * @param n      розмір квадратної матриці (непарне число)
     * @param symbol символ-заповнювач
     * @return зубчастий масив, який містить ромб
     */
    public static char[][] generateRombArray(int n, char symbol) {
        int center = n / 2;
        char[][] romb = new char[n][];

        for (int i = 0; i < n; i++) {
            romb[i] = new char[n]; // кожен рядок довжиною n
            for (int j = 0; j < n; j++) {
                // формування ромба: відстань від центраyhd
                if (Math.abs(center - i) + Math.abs(center - j) <= center) {
                    romb[i][j] = symbol;
                } else {
                    romb[i][j] = ' ';
                }
            }
        }
        return romb;
    }
}

