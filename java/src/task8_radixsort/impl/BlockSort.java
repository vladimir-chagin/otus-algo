package task8_radixsort.impl;

import task2.impl.primitive.IntSortedArray;
import util.IntU;

public class BlockSort {
    public static void sort(final int[] array, final int k) {
        final int m = array[IntU.indexOfMax(array)];
        IntSortedArray[] buckets = new IntSortedArray[k+1];

        for (int i = 0; i < array.length; i += 1) {
            int bucketIdx = array[i]*k/m;
            if (buckets[bucketIdx] == null) {
                buckets[bucketIdx] = new IntSortedArray(array.length/k + 1);
            }
            buckets[bucketIdx].add(array[i]);
        }

        int i = 0;
        for (IntSortedArray bucket : buckets) {
            if (bucket == null) {
                continue;
            }

            for(int j = 0; j < bucket.size(); j += 1) {
                array[i++] = bucket.get(j);
            }
        }
    }
}
