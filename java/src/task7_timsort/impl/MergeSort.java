package task7_timsort.impl;

public class MergeSort {

    public static void sort(final int[] array) {
        sort(array, 0, array.length - 1);
    }

    public static final int[] MIN_PARTS = new int[] { 32/*, 64, 128, 256, 512, 1024*/ };

    public static void sortMixed(final int[] array, final int minPart) {
        sortMixed(array, 0, array.length-1, minPart);
    }

    public static void sortMixed(final int[] array, final int l, final int h, final int minPart) {
        if (l >= h) {
            return;
        }

        if (h - l <= minPart) {
            QuickSort.quickSort(array, l, h);
            return;
        }

        final int m = l + (h - l) / 2;
        sort(array, l, m);
        sort(array, m + 1, h);
        merge(array, l, m, h);
    }

    public static void sort(final int[] array, final int l, final int h) {
        if (l >= h) {
            return;
        }

        final int m = l + (h - l) / 2;
        sort(array, l, m);
        sort(array, m + 1, h);
        merge(array, l, m, h);
    }

    private static void merge(final int[] array, int l, int m, int h) {
        if (l >= h) {
            return;
        }

        final int[] buff = new int[h - l + 1];
        int buffIdx = 0;
        int li = l;
        int ri = m + 1;

        while(li <= m && ri <= h) {
            if (array[li] <= array[ri]) {
                buff[buffIdx++] = array[li++];
            } else {
                buff[buffIdx++] = array[ri++];
            }
        }

        while(li <= m) {
            buff[buffIdx++] = array[li++];
        }

        while(ri <= h) {
            buff[buffIdx++] = array[ri++];
        }

        System.arraycopy(buff, 0, array, l, buff.length);
    }

}
