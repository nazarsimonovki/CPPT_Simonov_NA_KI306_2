"""
Головний скрипт для демонстрації роботи класів Printer та Scanner.
"""
from sys import excepthook

from Lab9.Printer import Printer
from Lab9.Scanner import Scanner

def main():
    try:
        print("=== ПРИНТЕР ===")
        simple_printer = Printer("HP LaserJet", "monochrome", 600, 100)

        print(simple_printer.get_status())
        simple_printer.print_documents("Лабраторні роботи (1-4)",50)
        simple_printer.print_documents("Курсова",60)  # Тут має бути помилка (нестача паперу)
        simple_printer.add_paper(50)
        simple_printer.print_documents("Курсова",60)
        print("\n")

        print("=== СКАНЕР ===")
        scanner = Scanner("Canon Pixma G3411", "CMYK", 900, 50, 900)
        print(scanner.get_info())
        scanner.scan_document("Contract.pdf")
        scanner.make_copy(5)

    except ImportError as e:
        print(f"Помилка імпорту: {e}")

main()