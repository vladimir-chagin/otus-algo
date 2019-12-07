package task7_timsort;

import task7_timsort.impl.BufferedFileSort;
import task7_timsort.impl.FileSort;
import task7_timsort.impl.MergeSort;
import util.Performance;

public class Program {
    /*
    Отсортировать Миллиард чисел
    Цель: Программа для сортировки файла, в котором записано миллиард целых 16-битовых чисел.
    1. Сгенерировать бинарный файл, который содержит N целых, 16-битных чисел (от 0 до 65553), по 2 байта на каждое число.
    Подобрать N для вашего языка программирования: 1e6, 1e7, 1e8, 1e9 или даже больше.
    2. Написать алгоритм сортировки слиянием для сортировки файла - внешняя сортировка и замерить время его работы.
    3. Использовать для сортировки небольших кусков (32, 64, 128, 256, 512 или 1024 чисел) другой алгоритм сортировки на выбор (quick, heap, shell) и сравнить время сортировки "комбинированным" алгоритмом.
    4. Опубликовать таблицу сравнения быстродействия и сделать выводы.
    Написать, сколько ушло времени на выполнение 1, 2 и 3 пункта.
    Опубликовать ссылку на репозиторий, таблицу сравнения и вывод.
    Критерии оценки: +2 байта за генерацию файла
    +3 байта за алгоритм сортировки слиянием файла
    +3 байта за доп. алгоритм сортировки в памяти
    +2 байта за таблицу и вывод
    * */
    public static void main(String[] args) {
//        final StringBuilder report = new StringBuilder();
//        report.append("algorithm|duration").append("\r\n");
//
//        testSortFile(report, 0);
//        report.append("\r\n");
//        for(int i = 0; i < MergeSort.MIN_PARTS.length; i += 1) {
//            testSortFile(report, MergeSort.MIN_PARTS[i]);
//            report.append("\r\n");
//        }
//
//        System.out.println(report.toString());

        testFileMergeSort();
    }

    private static void testSortFile(StringBuilder s, final int part) {
        BufferedFileSort.generateFileWithRandomNumbers();


        final long duration = part == 0
                ? Performance.measure(() -> { BufferedFileSort.sortFileWithMerge(); })
                : Performance.measure(() -> { BufferedFileSort.sortFileWithMixedMerge(part); });

        s.append("merge(" + part + ")").append("|").append(duration + "ms");
    }

    private static void testFileMergeSort() {
        FileSort.generateFileWithRandomNumbers("numbers.bin", FileSort.NUMBERS_IN_FILE, FileSort.MIN_VALUE, FileSort.MAX_VALUE);
        FileSort.sort();
    }
}
