n=100_000

Name|insert(n)|search(n/10)|remove(n/10)
:---|---:|---:|----:
BST Ordered|10151ms|1033ms|1029ms
BST Random|22ms|3ms|3ms
AVL Ordered|48ms|4ms|6ms
AVL Random|75ms|4ms|7ms

При добавлении в Бинарное Дерево Поиска(BST) чисел в порядке возрастания мы видим самый худший результат работы алгоритмов вставки/поиска/удаления.
Если числа были добавлены в псевдослучайном порядке - время работы наилучшее.
Для сбалансированного АВЛ-дерева(AVL) вставка в порядке возрастания работает немного медленее чем вставка псевдослучайных чисел в BST из-за дополнительного постоянного времени, необходимого для выполнения балансировки.   
В то же время вставка псевдослучайных чисел в AVL-дерево работает немного медленне чем вставка упорядоченной последовательности чисел. Это может быть связано с тем что при вставке чисел в порядке возрастания будут выполняться только малые левые повороты, а при вставке псевдослучаных чисел повороты более разнообразны.
