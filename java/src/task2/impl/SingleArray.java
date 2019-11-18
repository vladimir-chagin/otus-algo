package task2.impl;

import util.U;

public class SingleArray<T> extends AbstractArray<T> {

    public SingleArray () {
        super(0);
    }

    @Override
    public int size() {
        return array.length;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size()) {
            throw new ArrayIndexOutOfBoundsException("Invalid index");
        }

        return array[index];
    }

    @Override
    public void add(T item, int index) {
        if (index < 0 || index > array.length) {
            throw new RuntimeException("Invalid index: " + index + "; size: " + size());
        }

        array = U.increaseArray(array, 1, index);
        array[index] = item;
        size += 1;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= size()) {
            throw new RuntimeException("Invalid index: " + index + "; size: " + size());
        }

        final T item = array[index];

        array = U.removeItemAndDecreaseCapacity(array, index, array.length);
        size -= 1;

        return item;
    }

}
