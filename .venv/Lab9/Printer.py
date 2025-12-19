"""
Модуль, що містить базовий клас принтера.

Визначає основні властивості (модель, колір, папір) та
методи для друку та обслуговування.
"""


class Printer:
    """
    Представляє сутність принтера.

    Attributes:
        model (str): Назва моделі принтера.
        colored (str): Тип друку (кольоровий/монохромний).
        resolution (int): Роздільна здатність друку (DPI).
        _current_paper (int): Поточна кількість аркушів паперу (захищений атрибут).
    """

    def __init__(self, model, colored, resolution, paper):
        """
        Ініціалізує новий об'єкт принтера.

        Args:
            model (str): Назва моделі.
            colored (str): Тип палітри (наприклад, "monochrome", "RGB").
            resolution (int): Роздільна здатність у точках на дюйм.
            paper (int): Початкова кількість паперу.
        """
        self._model = model
        self._colored = colored
        self._resolution = resolution
        self.__current_paper = paper

    def print_documents(self, document: str, pages: int):
        """
        Імітує процес друку документа.

        Перевіряє наявність паперу. Якщо паперу достатньо — зменшує його кількість.

        Args:
            document (str): Назва документа.
            pages (int): Кількість сторінок для друку.
        """
        if self.__current_paper >= pages:
            self.__current_paper -= pages
            print(f"[{self._model}] Друкується документ: '{document}', {pages} сторінок...")
            print(f"[{self._model}] Друк завершено. Залишилось паперу: {self.__current_paper}")
        else:
            print(f"[{self._model}] ПОМИЛКА: Недостатня кількість паперу для друку '{document}'!")

    def add_paper(self, amount: int):
        """
        Додає папір у лоток принтера.

        Args:
            amount (int): Кількість аркушів для додавання.
        """
        self.__current_paper += amount
        print(f"[{self._model}] Додано {amount} аркушів. Поточна кількість: {self.__current_paper}")

    def get_status(self) -> str:
        """
        Повертає форматований рядок зі станом принтера.

        Returns:
            str: Інформація про модель, палітру, DPI та залишок паперу.
        """
        return (f"Принтер {self._model}:\n"
                f"  - Кольорова палітра: {self._colored}\n"
                f"  - Роздільна здатність: {self._resolution} DPI\n"
                f"  - Кількість паперу: {self.__current_paper}")