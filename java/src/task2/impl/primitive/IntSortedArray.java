package task2.impl.primitive;

import util.IntU;

import java.util.Arrays;

public class IntSortedArray {
    private int[] array;
    private int size;
    private int factor;


    public IntSortedArray(int initialCapacity) {
        this(initialCapacity, 50);
    }

    public IntSortedArray(int initialCapacity, int factor) {
        this.array = new int[initialCapacity];
        this.factor = factor;
        size = 0;
    }

    public void add(int value) {
        int i = indexOf(value);
        if (i < 0) {
            i = ~i;
        }

        add(i, value);
    }

    public void add(int i, int value) {
        if (i < 0 || i > size) {
            throw new RuntimeException("Invalid index: " + i + "; size: " + size);
        }

        if (array.length <= size) {
            int increaseCapacityBy = array.length * factor / 100 + 1;
            array = IntU.increaseArray(array, increaseCapacityBy, i);
        } else {
            IntU.shiftItemsFromIndex(array, i, size);
        }

        array[i] = value;
        size += 1;
    }

    public int indexOf(int value) {
        if (size <= 0) {
            return ~0;
        }

        int l = 0;
        int r = size - 1;

        while(l <= r) {
            int m = (r + l) / 2;
            if (array[m] < value) {
                l = m + 1;
            } else if (array[m] > value) {
                r = m - 1;
            } else {
                return m;
            }
        }

        return ~l;
    }

    public int remove(int i) {
        if (i < 0 || i >= size()) {
            throw new RuntimeException("Invalid index: " + i + "; size: " + size);
        }

        final int item = array[i];

        if ((array.length - size) > (size * factor / 100 + 1)) {
            int decreaseCapacityBy = size * factor / 100;
            array = IntU.removeItemAndDecreaseCapacity(array, i, size, decreaseCapacityBy);
        } else {
            IntU.removeItem(array, i, size);
        }

        size -= 1;
        return item;
    }

    public int removeFirst() {
        return remove(0);
    }

    public int removeLast() {
        return remove(size - 1);
    }

    public int size() {
        return size;
    }

    @Override
    public String toString() {
        return size + ": " + Arrays.toString(array);
    }
}
