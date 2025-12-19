import sys

print("--- Генератор Ромбовидної Матриці ---")

n_input = input("Введіть розмір квадратної матриці (ціле число): ")

if not n_input.isdigit():
    print("Помилка: Розмір має бути цілим числом.")
    sys.exit(1)

n = int(n_input)

char = input("Введіть ОДИН символ-заповнювач: ")

if len(char) != 1:
    print("Помилка: Необхідно ввести рівно один символ.")
    sys.exit(1)

print("\nСформований зубчатий масив:")

jagged_list = []
center = (n - 1) / 2
radius = n / 2
max_width = n * 2 - 1

for i in range(n):
    row_data = []
    for j in range(n):
        if abs(i - center) + abs(j - center) <= radius:
            row_data.append(char)
    jagged_list.append(row_data)

for row in jagged_list:
    row_str = "".join(row)
    left_padding = (max_width - len(row_str)) // 2
    print(" " * left_padding + row_str)