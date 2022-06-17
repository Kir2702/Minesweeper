# Minesweeper


Классический сапер. Стандартные правила.


![schem.png](https://github.com/Kir2702/myScreenshots/blob/main/Minesweeper.png)


[Скомпилированный jar](/Kir2702/Minesweeper/raw/main/out/artifacts/Minesweeper_jar/Minesweeper.jar)




## Принцип работы


![schem.png](https://github.com/Kir2702/myScreenshots/blob/main/MinesweeperSchem.png)

Приложение реализует паттерн проектирования «Фасад». Фасадным классом в нашем случае является Game.

Класс Game, получает на вход основные параметры (ROWS, COLS, BOMBS_COUNT), инициализируется один раз в классе Main и предоставляет JFrame единый интерфейс для управления Game.

Coord и Ranges выведены в отдельные классы так как реализуют часто используемую логику.



## TO DO:


	1. Вывести на панель счетчик бомб и флагов
	2. Вывести на панель статус игры в виде смайлика
	3. Реализовать возможность изменять переменные (ROWS, COLS, BOMBS_COUNT) во время работы приложения.
	
	
png взяты с ресурса https://prog-cpp.ru/miner/