array size: 50_000_000

Shuffle %|ShellSort|HeapSort
---|---|---
0.0|2732ms|14134ms
0.05|6238ms|15909ms
1.0|77582ms|71143ms


Таблица показывает сравнительный результат работы алгоритмов сортировки ShellSort и HeapSort.
Столбец Shuffle % отображает кол-во(в процентах) перемешанных элементов массива.
Оба алгоритма сортируют полностью идентичные массивы.
Из таблицы видно что ShellSort работает быстрее HeapSort на отсортированном и почти-отсортированном(перемешани 5% элементов) массиве.
Для полностью перемешанного массива HeapSort работает немного быстрее, но ниже будет такая же таблица для массива из 100 млн элементов.

array size: 100_000_000

Shuffle %|ShellSort|HeapSort
---|---|---
0.0|5321ms|27899ms
0.05|12321ms|32634ms
1.0|192978ms|160007ms

Мы видим что для полностью перемешанного массива ситуация уже другая.
Размерность массива выросла в 2 раза, а вот время работы ShellSort выросло в 2.5 раза и время работы HeapSort выросло в 2.25 раза.
Видно что в этом случае время работы ShellSort растет быстрее.

Вывод: какой алгоритм лучше зависит конкретной задачи для которой использует та или иная сортировка.
Для частично отсортированных массивов лучше работает сортировка ShellSort. Для неотсортированных массивов большого размера быстрее будет работать HeapSort.
