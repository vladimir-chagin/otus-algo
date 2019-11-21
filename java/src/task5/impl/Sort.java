package task5.impl;

public class Sort {

    public static final int[] Knuth = new int[] { 1, 4, 13, 40, 121, 364, 1093, 3280, 9841, 29524, 88573, 265720, 797161, 2391484 };
    public static final int[] Hibbard = new int[] { 1, 3, 7, 15, 31, 63, 127, 255, 511, 1023, 2047, 4095, 8191, 16383, 32767, 65535, 131071, 262143, 524287, 1048575, 2097151, 4194303 };
    public static final int[] Papernov = new int[] {1, 3, 5, 9, 17, 33, 65, 129, 257, 513, 1025, 2049, 4097, 8193, 16385, 32769, 65537, 131073, 262145, 524289, 1048577, 2097153, 4194305, 8388609};
    public static final int[] Sedgewick = new int[] { 1, 8, 23, 77, 281, 1073, 4193, 16577, 65921, 262913, 1050113, 4197377 };

    private static int findGapIx(int[] gaps, int n) {
        int idx = gaps.length - 1;
        for (; idx >= 0 && gaps[idx] * 2 > n; idx -= 1);
        return idx;
    }

    public static <T extends Comparable<T>> void shell(T[] array) {
        shell(array, 3);
    }

    public static <T extends Comparable<T>> void shell(T[] array, int d) {
        final int len = array.length;
        int h = 1;

        while(h < len / d) {
            h = d * h + 1;
        }

        while (h > 0) {
            /* sort-by-insertion in increments of h */
            for (int i = h; i < len; i += 1) {
                T tmp = array[i];
                int j = i - h;
                for (; j >= 0 && array[j].compareTo(tmp) > 0; j -= h) {
                    array[j + h] = array[j];
                }

                array[j + h] = tmp;
            }

            h /= d;
        }
    }

    public static <T extends Comparable<T>> void shell(T[] array, int[] gaps) {
        final int len = array.length;
        int t = findGapIx(gaps, len);

        while (t >= 0) {
            /* sort-by-insertion in increments of h */
            int h = gaps[t];
            for (int i = h; i < len; i += 1) {
                T tmp = array[i];
                int j = i - h;
                for (; j >= 0 && array[j].compareTo(tmp) > 0; j -= h) {
                    array[j + h] = array[j];
                }

                array[j + h] = tmp;
            }
            t -= 1;
        }
    }
}
