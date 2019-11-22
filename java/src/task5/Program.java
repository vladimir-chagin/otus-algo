package task5;

import task5.impl.Sort;
import util.Performance;
import util.U;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

/*
* 1. Выбрать подходящий размер массива, чтобы алгоритм работал 5-20 секунд.
2. Создать три массива выбранного размера:
а. случайный;
б. отсортированный, в котором перемешаны 10% элементов;
в. отсортированный, в котором перемешано 5 элементов.
3. Составить несколько (не меньше 3) вариантов перебора последовательности шагов сортировки,
подробнее см.: https://en.wikipedia.org/wiki/Shellsort#Gap_sequences
4. Замерять время работы алгоритма для каждого массива и каждого варианта, заполняя табличку. Таблицу приложить к коду.
5. Сделать вывод, какая последовательность шагов на ваш взгляд является оптимальной.
* */

public class Program {
    private static final double[] sd = new double[] { 0, 0.000_002, 0.1, 1 };
    private static final int count = 10_000_000;

    private static Integer[] createArray(int len, double mix) {
        Integer[] array = new Integer[len];
        U.fillArrayWithRandomNumbers(array);
        U.shuffleArray(array, mix);
        return array;
    }

    private static void testSort() {

        final int[][] gapsArr = new int[][]{ Sort.Knuth, Sort.Hibbard, Sort.Papernov, Sort.Sedgewick };

        StringBuilder report = new StringBuilder();
        report.append("array size: " + count).append("\r\n");
        report.append("Shuffle %|Knut(3^k+1)|Hibbard(2^k-1)|Papernov(2^k+1)|Sedgewick(4^k+3*2^(k-1)+1)").append("\r\n");
        report.append("---|---|---|---|---").append("\r\n");

        for (int i = 0; i < sd.length; i += 1) {
            report.append(sd[i]).append("|");

            for (int j = 0; j < gapsArr.length; j += 1) {
                final int[] gaps = gapsArr[j];

                final Integer[] array = createArray(count, sd[i]);
                final long duration = Performance.measure(() -> {
                    Sort.shell(array, gaps);
                });
                report.append(duration + "ms").append("|");
            }
            report.append("\r\n");
        }

        final String output = report.toString();
        System.out.println(output);
        try {
            Files.write(Paths.get("java", "src", "task5", "report_generated.md"), output.getBytes());
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void testSameArray() {
        Integer[] array = createArray(count, 1);
        for (int i = 0; i < 10; i += 1) {
            final Integer[] arr = new Integer[array.length];
            System.arraycopy(array, 0, arr, 0, array.length);
            final long duration = Performance.measure(() -> {
                Sort.shell(arr);
            });
            System.out.println(duration);
        }
    }

    private static void testSameArray1() {
        final int[][] gaps = new int[][]{ Sort.Knuth, Sort.Hibbard, Sort.Papernov, Sort.Sedgewick };
        final Integer[] array = createArray(count, 1);
        for (int i = 0; i < gaps.length; i += 1) {
            final Integer[] arr = new Integer[array.length];
            System.arraycopy(array, 0, arr, 0, array.length);
            final int[] g = gaps[i];
            final long duration = Performance.measure(() -> {
                Sort.shell(arr, g);
            });
            System.out.println(duration);
        }

    }

    public static void main(String[] args) {
//        testSort();
        testSameArray1();
    }


}
