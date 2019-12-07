package task6_heapsort;

import task5.impl.Sort;
import task6_heapsort.impl.HeapSort;
import util.Performance;
import util.U;

import java.nio.file.Files;
import java.nio.file.Paths;

public class Program {
    /*
    * Напишите, какие пункты вы сделали, сколько на это ушло времени,
приложите ссылку на ваш код и на заполненную таблицу.

Как сравнивать производительность.
1. Выбрать подходящий размер массива, чтобы алгоритм работал 5-20 секунд.
2. Создать два массива выбранного размера:
а. случайный;
в. отсортированный, в котором перемешано 5% элементов.
3. Замерять время работы алгоритмов HeapSort и ShellSort для каждого массива, заполняя табличку. Таблицу приложить к коду.
4. Сделать вывод, какой алгоритм соритровки лучше.
Критерии оценки: +4 байта за реализацию алгоритма HeapSort
+4 байтов за создание и публикацию таблицы.
+2 байта за вывод, какой алгоритм сортировки лучше.
    * */

    private static final double[] shuffles = new double[] { 0, 0.05, 1 };
    private static final int count = 50_000_000;

    private static Integer[] createArray(int len, double mix) {
        Integer[] array = new Integer[len];
        U.fillArrayWithRandomIntegers(array);
        U.shuffleArray(array, mix);
        return array;
    }


    public static void main(String[] args) {
        testSort();
//        Integer[] array = createArray(10, 1);
//        System.out.println(U.arrayToString(array));
//        HeapSort.sort(array);
//        System.out.println(U.arrayToString(array));
    }

    private static void testSort() {

        StringBuilder report = new StringBuilder();
        report.append("array size: " + count).append("\r\n\r\n");

        report.append("Shuffle %|ShellSort|HeapSort").append("\r\n");
        report.append("---|---|---").append("\r\n");

        for (int i = 0; i < shuffles.length; i += 1) {
            final Integer[] array = createArray(count, shuffles[i]);
            final Integer[] arrayShell = new Integer[array.length];

            System.arraycopy(array, 0, arrayShell, 0, array.length);
            final Integer[] arrayHeap = new Integer[array.length];
            System.arraycopy(array, 0, arrayHeap, 0, array.length);

            final long durationShell = Performance.measure(() -> {
                Sort.shell(arrayShell, Sort.Knuth);
            });

            final long durationHeap = Performance.measure(() -> {
                HeapSort.sort(arrayHeap);
            });

            report.append(shuffles[i]).append("|");
            report.append(durationShell + "ms").append("|");
            report.append(durationHeap + "ms").append("\r\n");
        }

        final String output = report.toString();
        System.out.println(output);
        try {
            Files.write(Paths.get("java", "src", "task6_heapsort", "report_generated.md"), output.getBytes());
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

}
