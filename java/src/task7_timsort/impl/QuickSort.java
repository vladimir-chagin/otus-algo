package task7_timsort.impl;

public class QuickSort {

    public static void sort(final int[] array) {
        quickSort(array, 0, array.length - 1);
    }

    public static void quickSort(int[] array, int l, int h) {
        if (l >= h) {
            return;
        }

        final int m = partition(array, l, h);
        quickSort(array, l, m - 1);
        quickSort(array, m + 1, h);
    }

    private static int partition(final int[] array, final int l, final int h) {
        int i = l - 1;
        final int pivot = array[h];

        for (int j = l; j <= h; j += 1) {
            if (array[j] <= pivot) {
                i += 1;
                int x = array[j];
                array[j] = array[i];
                array[i] = x;
            }
        }

        return i;
    }

}
