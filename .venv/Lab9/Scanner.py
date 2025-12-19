"""
Модуль для класу Сканер, який успадковується від Принтера..
"""
from Lab9.Printer import Printer


class Scanner(Printer):
    """
    Представляє багатофункціональний пристрій (БФП) з функціями сканера.
    Успадковує всі методи класу Printer.

    Attributes:
        scanner_resolution (int): Роздільна здатність сканування (DPI).
    """

    def __init__(self, model, colored, resolution, paper, scanner_resolution):
        """
        Ініціалізує сканер, викликаючи конструктор батьківського класу Printer.

        Args:
            model (str): Назва моделі.
            colored (str): Тип палітри.
            resolution (int): DPI принтера.
            paper (int): Кількість паперу.
            scanner_resolution (int): DPI сканера (нове поле).
        """
        super().__init__(model, colored, resolution, paper)
        self._scanner_resolution = scanner_resolution

    def scan_document(self, document_name: str):
        """
        Імітує процес сканування документа.

        Args:
            document_name (str): Ім'я файлу, який сканується.
        """
        print(f"[{self._model}] Сканування '{document_name}' з роздільною здатністю {self._scanner_resolution} DPI...")
        print(f"[{self._model}] Сканування завершено. Файл збережено.")

    def make_copy(self, pages):
        """
        Імітує процес копіювання (сканування + друк).

        Args:
            pages (int): Кількість сторінок для копіювання.
        """
        print(f"[{self._model}] -- Початок копіювання --")
        # Крок 1: Сканування у тимчасовий буфер
        self.scan_document("Temp_copy_buffer")

        self.print_documents("Копія документа", pages)

        print(f"[{self._model}] -- Копіювання завершено --")

    def get_info(self) -> str:
        """
        Повертає розширену інформацію про пристрій (Принтер + Сканер).

        Returns:
            str: Об'єднаний рядок статусу.
        """
        base_status = self.get_status()
        return f"{base_status}\n  - Сканер: {self._scanner_resolution} DPI",    print(f"{base_status}")