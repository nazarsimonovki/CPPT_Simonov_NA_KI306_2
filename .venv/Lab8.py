"""
Модуль для математичних обчислень та роботи з файлами.

Цей модуль виконує обчислення заданої математичної функції,
здійснює запис результатів у текстові та бінарні файли,
а також зчитує їх для перевірки.

Функція:
    y = ctg(x) / (sin(2x) + 4cos(x))
"""

import math
import struct
import sys
import os

def calculate(x: float) -> float:
    """
    Обчислює значення функції y = ctg(x) / (sin(2x) + 4cos(x)).

    Args:
        x (float): Вхідне значення аргументу функції (в радіанах).

    Returns:
        float: Результат обчислення функції.

    Raises:
        SystemExit: Якщо виникає помилка обчислення (ділення на нуль
        або вихід за межі області визначення), програма завершує роботу.
    """
    try:
        if math.tan(x) == 0:
            raise ValueError("Тангенс дорівнює 0, котангенс не існує.")

        ctg_x = 1.0 / math.tan(x)
        denominator = math.sin(2 * x) + 4 * math.cos(x)

        if denominator == 0:
            raise ZeroDivisionError("Знаменник дорівнює нулю.")

        y = ctg_x / denominator

    except Exception as e:
        print(f"Помилка обчислення: {e}")
        sys.exit(1)
    
    return y


def write_res_text(FileName: str, x: float) -> None:
    """
    Записує числове значення у текстовий файл.

    Args:
        FileName (str): Шлях або ім'я файлу для запису.
        x (float): Числове значення для збереження.

    Returns:
        None

    Errors:
        Виводить повідомлення про помилку OSError у консоль, якщо запис не вдався.
    """
    try:
        f = open(FileName, 'w', encoding="utf-8")
        f.write(str(x))
        print(f"Значення записано у текстовий файл: {FileName}")
    except OSError as e:
        print(f"Помилка запису у файл {FileName}: {e}")
    finally:
        f.close()


def read_res_text(FileName: str) -> None:
    """
    Зчитує числове значення з текстового файлу та виводить його у консоль.

    Args:
        FileName (str): Шлях до файлу, який потрібно прочитати.

    Returns:
        None

    Errors:
        Обробляє FileNotFoundError, ValueError (якщо вміст не є числом)
        та інші винятки, виводячи повідомлення у консоль.
    """
    try:
        if not os.path.exists(FileName):
            raise FileNotFoundError(f"Файл {FileName} не знайдено.")

        f = open(FileName, 'r', encoding="utf-8")
        res = f.read()
        print(f"Значення зчитане з текстового файлу {FileName}: {res}")
    except FileNotFoundError as e:
        print(e)
    except ValueError:
        print(f"Помилка: файл {FileName} містить нечислові дані.")
    except Exception as e:
        print(f"Непередбачена помилка при читанні {FileName}: {e}")
    finally:
        f.close()


def write_res_bin(FileName: str, x: float) -> None:
    """
    Записує числове значення у бінарний файл.

    Використовує модуль struct для пакування числа у формат double (8 байт).

    Args:
        FileName (str): Шлях або ім'я бінарного файлу.
        x (float): Числове значення для збереження.

    Returns:
        None
    """
    try:
        packed_data = struct.pack('d', x)
        f = open(FileName, 'wb')
        f.write(packed_data)
        print(f"Значення записано в бінарний файл: {FileName}")
    except Exception as e:
        print(f"Помилка запису у бінарний файл: {e}")
    finally:
        f.close()


def read_res_bin(FileName: str) -> None:
    """
    Зчитує числове значення double з бінарного файлу.

    Розпаковує байти, використовуючи модуль struct, та виводить результат.

    Args:
        FileName (str): Шлях до бінарного файлу.

    Returns:
        None

    Errors:
        Обробляє FileNotFoundError та struct.error (пошкоджені дані).
    """
    try:
        f = open(FileName, 'rb')
        res = f.read()
        unpacked_res = struct.unpack('d', res)[0]
        print(f"Значення зчитане з бінарного файлу {FileName}: {unpacked_res}")
    except FileNotFoundError as e:
        print(e)
    except struct.error:
        print(f"Помилка структури файлу {FileName}.")
    except Exception as e:
        print(f"Помилка при читанні бінарного файлу: {e}")
    finally:
        f.close()


# ====== Основна частина програми ======
if __name__ == "__main__":
    """
    Точка входу в програму.

    Виконує:
    1. Запит користувача на введення x.
    2. Обчислення функції.
    3. Запис та читання результату через текстовий та бінарний формати.
    """
    FTextName = "result.txt"
    FBinName = "bresult.bin"

    try:
        print("Обчислення виразу: y=ctg(x)/(sin(2x) + 4cos(x))")
        x_input = float(input("Введіть значення x: "))
        res = calculate(x_input)
        print("Результат обчислення: %f" % res)

        write_res_bin(FBinName, res)
        write_res_text(FTextName, res)
        read_res_bin(FBinName)
        read_res_text(FTextName)
    except ValueError:
        print("Помилка: Введено не коректне число.")