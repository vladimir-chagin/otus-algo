package task3.impl;

public class Pow {
    /*
2. Алгоритм возведения в степень макс. 4 байта
2а. Итеративный (n умножений)
+1 байт 2b. Через степень двойки с домножением
+1 байт 2c. Через двоичное разложение показателя степени.
+2 байт Составить сравнительную таблицу времени работы алгоритмов для разных начальных данных.
Написать, какие пункты выполнены и сколько времени ушло на выполнение домашнего задания.
    * */

    public static long iterative(long a, long n) {
        long res = 1;

        for (long i = 1; i <= n; i += 1) {
            res *= a;
        }

        return res;
    }

    public static long powerOf2WithMultiply(long a, int n) {
        int k = 1;
        long res = 1;

        for ( ; k < n / 2; k *= 2) {
            res *= res;
        }

        for ( ; k <= n; k += 1) {
            res *= a;
        }

        return res;
    }

    public static long powersOf2(long a, long n) {
        int res = 1;

        while (n > 0) {
            if (n % 2 == 1) {
                res *= a;
            }
            a *= a;
            n /= 2;
        }

        return res;
    }

}
