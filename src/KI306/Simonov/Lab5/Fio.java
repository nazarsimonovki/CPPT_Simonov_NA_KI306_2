package KI306.Simonov.Lab5;

import java.io.*;
import java.util.Scanner;

public class Fio {

    double number;
    /**
     * Метод записує результат у текстовий файл.
     * Використовує клас PrintWriter.
     * @param fName Ім'я файлу
     * @param TempRes Число яке записуємо у файл.
     * @throws FileNotFoundException
     */
    public void writeResTxt(String fName, double TempRes) throws FileNotFoundException {
        PrintWriter f = new PrintWriter(fName);
        f.printf("%f", TempRes);
        f.close();
    }

    /**
     * Метод читає результат з текстового файлу.
     * Використовує клас Scanner.
     * @param fName Ім'я файлу
     */
    public void readResTxt(String fName) throws FileNotFoundException {
        File f = new File(fName);
        Scanner s = new Scanner(f);
        s.useLocale(java.util.Locale.getDefault());
        number = s.nextDouble();
        s.close();
    }

    /**
     * Метод записує результат у двійковий файл.
     * Використовує DataOutputStream.
     * @param fName Ім'я файлу
     * @throws FileNotFoundException
     * @throws IOException
     */
    public void writeResBin(String fName, double TempRes) throws FileNotFoundException, IOException {
        DataOutputStream f = new DataOutputStream(new FileOutputStream(fName));
        f.writeDouble(TempRes);
        f.close();
    }

    /**
     * Метод читає результат з двійкового файлу.
     * Використовує DataInputStream.
     * @param fName Ім'я файлу
     * @throws FileNotFoundException
     * @throws IOException
     */
    public void readResBin(String fName) throws FileNotFoundException, IOException {

            DataInputStream f = new DataInputStream(new FileInputStream(fName));
            number = f.readDouble();
            f.close();
    }

    /**
     * Геттер для отримання поточного результату.
     * @return Поточний результат
     */
    public double getResult(){
        return number;
    }

}
