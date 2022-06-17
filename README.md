# Minesweeper


Классический сапер. Стандартные правила.


![schem.png](https://github.com/Kir2702/myScreenshots/blob/main/Minesweeper.png)


Скомпилированный jar: Minesweeper/out/artifacts/Minesweeper_jar/Minesweeper.jar


## Принцип работы


![schem.png](https://github.com/Kir2702/myScreenshots/blob/main/MinesweeperSchem.png)

Приложение реализует паттерн проектирования «Фасад». Фасадным классом в нашем случае является class Game.

Сlass Game, получает на вход основные параметры (ROWS, COLS, BOMBS_COUNT), инициализируется один раз в class Main и предоставляет JFrame единый интерфейс для управления Game.

Coord и Ranges вывел в отдельные классы согласно принципу DRY.



## TO DO:


	1. Вывести на панель счетчик бомб и флагов
	2. Вывести на панель статус игры в виде смайлика
	3. Реализовать возможность изменять переменные (ROWS, COLS, BOMBS_COUNT) во время работы приложения.
	
	
png взяты с ресурса https://prog-cpp.ru/miner/