package task6_heapsort.impl;

import util.U;

public class HeapSort {

    public static<T extends Comparable<T>> void sort(T[] array) {
        buildMaxHeap(array);
        int size = array.length;
        for (int i = array.length - 1; i > 0; i -= 1) {
            U.swap(array, 0, i);
            size -= 1;
            maxHeapify(array, 0, size);
        }
    }

    private static<T extends Comparable<T>> void buildMaxHeap(T[] array) {
        for (int i = (array.length - 1) / 2; i >= 0; i -= 1) {
            maxHeapify(array, i, array.length);
        }
    }

    private static<T extends Comparable<T>> void maxHeapify(T[] array, int i, int size) {
        int l = left(i);
        int r = right(i);
        int max = i;

        if (l < size && array[l].compareTo(array[max]) > 0) {
            max = l;
        }

        if (r < size && array[r].compareTo(array[max]) > 0) {
            max = r;
        }

        if (max != i) {
            U.swap(array, i, max);
            maxHeapify(array, max, size);
        }
    }

    private static int left(int idx) {
        return 2*idx + 1;
    }

    private static int right(int idx) {
        return 2 * idx + 2;
    }

    private static int parent(int idx) {
        return (idx - 1) / 2;
    }

}
