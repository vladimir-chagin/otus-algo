package task8_radixsort.impl;

public class CountingSort {
    public static void sort(final int [] array, final int k) {
        final int[] counts = new int[k + 1];
        for (int a : array) {
            counts[a] += 1;
        }

        for (int i = 1; i < k; i += 1) {
            counts[i] += counts[i-1];
        }
        final int[] tmp = new int[array.length];
        System.arraycopy(array, 0, tmp, 0, array.length);

        for (int i = tmp.length - 1; i >= 0; i -= 1) {
            int idx = --counts[tmp[i]];
            array[idx] = tmp[i];
        }
    }
}
