package KI306.Simonov.Lab5;

import static java.lang.Math.*;

/**
 * Клас {@code EquationCalculator} реалізує метод для обчислення
 * виразу y = ctg(x) / (sin(2x) + 4cos(x)).
 *
 */
public class EquationCalculator {

    /**
     * Обчислює вираз y = ctg(x) / (sin(2x) + 4cos(x)).
     * <p>
     * Вираз перетворено до y = cos(x) / (sin(x) * (sin(2x) + 4cos(x)))
     * для коректної перевірки ділення на нуль.
     *
     * @param x Вхідний аргумент 'x' (в радіанах).
     * @return Результат обчислення виразу 'y'.
     * @throws CalculationException Виникає, якщо:
     * 1. sin(x) == 0 (котангенс не визначений).
     * 2. (sin(2x) + 4cos(x)) == 0 (ділення на нуль).
     */
    public double calculate(double x) throws CalculationException {

        double numerator = cos(x);

        double sin_x = sin(x);
        double complexPart = sin(2 * x) + 4 * cos(x);

        double epsilon = 1e-10;

        if (abs(sin_x) < epsilon) {
            throw new CalculationException(
                    "Помилка при x = " + x + ": sin(x) = 0. Котангенс не визначений.");
        }

        if (abs(complexPart) < epsilon) {
            throw new CalculationException(
                    "Помилка при x = " + x + ": (sin(2x) + 4cos(x)) = 0. Ділення на нуль.");
        }

        double denominator = sin_x * complexPart;

        if (abs(denominator) < epsilon) {
            throw new CalculationException(
                    "Помилка при x = " + x + ": Загальний знаменник дорівнює нулю.");
        }

        return numerator / denominator;
    }
}
