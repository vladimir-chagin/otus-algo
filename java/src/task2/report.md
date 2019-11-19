# Arrays
Name|Add|Add(i)|Add(0)|Get(i)|Remove(i)|Remove(n)|Remove(0)
---|---|---|---|---|---|---|---
**100 iterations** | 
**SingleArray**|0ms|3ms|1ms|0ms|0ms|0ms|0ms
**VectorArray**|0ms|3ms|0ms|0ms|0ms|0ms|0ms
**FactorArray**|0ms|0ms|0ms|0ms|0ms|0ms|0ms
**ArrLstArray***|0ms|1ms|0ms|0ms|0ms|0ms|0ms
**SortedArray**|0ms|0ms|0ms|0ms|1ms|0ms|0ms
**MatrixArray**|0ms|1ms|0ms|0ms|0ms|0ms|0ms
**LinkedList**|1ms|1ms|0ms|0ms|0ms|1ms|0ms
**DoubleLinkedList**|0ms|0ms|0ms|1ms|0ms|0ms|0ms
**1000 iterations** | 
**SingleArray**|2ms|3ms|1ms|0ms|1ms|2ms|1ms
**VectorArray**|0ms|1ms|0ms|0ms|1ms|0ms|1ms
**FactorArray**|0ms|0ms|1ms|0ms|1ms|0ms|0ms
**ArrLstArray**|1ms|1ms|0ms|0ms|1ms|0ms|0ms
**SortedArray**|2ms|2ms|2ms|2ms|1ms|2ms|1ms
**MatrixArray**|1ms|5ms|1ms|0ms|5ms|2ms|5ms
**LinkedList**|1ms|1ms|0ms|1ms|1ms|2ms|0ms
**DoubleLinkedList**|0ms|4ms|1ms|2ms|1ms|0ms|0ms
**10000 iterations** | 
**SingleArray**|46ms|72ms|44ms|0ms|33ms|102ms|51ms
**VectorArray**|5ms|8ms|9ms|0ms|7ms|3ms|12ms
**FactorArray**|1ms|2ms|1ms|0ms|4ms|1ms|7ms
**ArrLstArray**|1ms|5ms|7ms|1ms|4ms|1ms|6ms
**SortedArray**|38ms|42ms|145ms|0ms|38ms|33ms|34ms
**MatrixArray**|1ms|183ms|11ms|1ms|175ms|44ms|231ms
**LinkedList**|122ms|246ms|1ms|138ms|124ms|129ms|0ms
**DoubleLinkedList**|1ms|122ms|0ms|92ms|75ms|1ms|0ms
**20000 iterations** | 
**SingleArray**|395ms|151ms|176ms|1ms|125ms|129ms|124ms
**VectorArray**|13ms|26ms|34ms|0ms|25ms|12ms|49ms
**FactorArray**|1ms|1ms|1ms|0ms|11ms|1ms|27ms
**ArrLstArray**|1ms|14ms|27ms|1ms|13ms|0ms|25ms
**SortedArray**|124ms|132ms|140ms|0ms|125ms|122ms|126ms
**MatrixArray**|2ms|344ms|83ms|1ms|652ms|199ms|1270ms
**LinkedList**|534ms|1301ms|1ms|484ms|519ms|514ms|0ms
**DoubleLinkedList**|0ms|636ms|1ms|384ms|316ms|0ms|0ms
**50000 iterations** | 
**SingleArray**|805ms|792ms|800ms|0ms|800ms|777ms|892ms
**VectorArray**|74ms|161ms|229ms|0ms|157ms|76ms|232ms
**FactorArray**|2ms|4ms|2ms|1ms|87ms|1ms|175ms
**ArrLstArray**|2ms|85ms|177ms|0ms|87ms|0ms|173ms
**SortedArray**|802ms|776ms|847ms|0ms|746ms|799ms|808ms
**MatrixArray**|8ms|2784ms|560ms|1ms|4071ms|1229ms|7704ms
**LinkedList**|3596ms|9947ms|2ms|3577ms|3612ms|3619ms|0ms
**DoubleLinkedList**|2ms|4993ms|2ms|2535ms|2145ms|0ms|1ms
**100000 iterations** | 
**SingleArray**|3578ms|3817ms|3341ms|1ms|3396ms|3719ms|3672ms
**VectorArray**|326ms|636ms|1010ms|0ms|643ms|315ms|1020ms
**FactorArray**|4ms|6ms|4ms|1ms|366ms|2ms|792ms
**ArrLstArray**|4ms|361ms|796ms|0ms|358ms|0ms|791ms
**SortedArray**|3187ms|3332ms|3273ms|0ms|3626ms|3701ms|4002ms
**MatrixArray**|34ms|10192ms|3320ms|1ms|19533ms|5299ms|38634ms
**LinkedList**|18747ms|69424ms|3ms|17366ms|18709ms|17591ms|1ms
**DoubleLinkedList**|6ms|26780ms|4ms|11678ms|10990ms|1ms|1ms

### Условные обозначения:
Add - добавление элемента в конец массива
Add(0) - добавление элемента в начало массива
Add(i) - добавление элемента в случайную позицию от 0 до size
Get(i) - получение элемента по индексу
Remove(i) - удаление элемента со случайной позиции то 0 до size-1
Remove(n) - удаление самого последнего элемента
Remove(0) - удаление самого первого элемента

## **SingleArray**
Самый простой массив, который занимает ровно столько памяти, сколько необходимо для хранения всех элементов.
При добавлении каждого нового элемента необходимо перевыделять память и копировать элементы, это сказывается на быстродействии.

## **VectorArray**
В этом массиве память выделяется реже, если в **SingleArray** память выделялась при каждом добавлении, то в этом случае память будет выделяться в *vector* раз реже. Можно сказать что сложность добавления будет O(n/vector).
Но тк vector=const то всеравно O(n).

## **FactorArray**
Размер выделяемой памяти удваивается. Это ускоряет быстродействие вставки(O(log n)), но увеличивает размер необходимой для работы памяти - 2n
## **ArrLstArray**(ArrayList wrapper)
Обертка над java.util.ArryList
Вставка в конец массива работает быстро, с увеличением размерности время растет, но не значительно.
А вот вставка в случаную позицию от 0 до size уже работает значительно медленнее, где-то O(4*n) == O(n).

## **SortedArray**
Массив, элементы в котором сортируются по мере добавления. Реализован на базе **SingleArray**, по-этому время работы сопоставимо. Вставка элемента в массив работает медленнее чем **SingleArray** тк что-бы вставить элемент в массив надо сначала найти индекс, куда этот элемент вставить.
## **MatrixArray**
Массив массивов.
Добавление в конец массива работает быстро за O(1), тк не нужно перекопировать все предыдущие элементы. Но вот вставка в начало уже работает значительно медленнее. Сложность O(n).
## **LinkedList**
Односвязный список. Добавление в конец списка работает за O(n). Добавление в начало списка работает за O(1). Добавление эоемента в случайную позицию - O(n)
## **DoubleLinkedList**
Двусвязный список. Особенности: вставка элементов в начало и конец списка работает за O(1), вставка в произвольное место - O(n/2)
Поиск/удаление - O(n/2)
Преимуществом списков является тот факт, что добавление в начало(Double-LinkedList) и в конец (DoubleLinkedList) работает за O(1)
Недостатки - нужна дополнительна память для хранения служебной информации - ссылок на следующие(предыдущие) элементы. Выделение памяти под каждый новый элемент занимает дополнительное время.
Возможная оптимизация - создать пулл объектов(Node<T>) для повторного переиспользования, это сократит время на выделение новой памяти.

# PriorityQueue
|---|Enqueue|Dequeue|
|---|---|---|
100 iterations|1ms|1ms|
1000 iterations|1ms|0ms|
10000 iterations|39ms|18ms|
20000 iterations|73ms|64ms|
50000 iterations|435ms|374ms|
100000 iterations|1629ms|1385ms|


Очередь с приоритетами. Похоже на **MatrixArray**. Но вместо **SingleArray** я использовал его оптимизированного брата **SortedArray**:)
Вставка и удаление работают примерно одинаково - O(n)
