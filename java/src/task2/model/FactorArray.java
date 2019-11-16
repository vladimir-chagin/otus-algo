package task2.model;

import util.U;

public class FactorArray<T> extends AbstractArray<T> {
    private final int factor;

    public FactorArray(int factor, int initLength) {
        super(initLength);
        this.factor = factor;
    }

    public FactorArray() {
        this(50, 10);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void add(T item) {
        if (size == array.length) {
            array = U.increaseArray(array, size * factor / 100 + 1);
        }

        array[size] = item;
        size += 1;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T get(int index) {
        return array[index];
    }

    @Override
    public void add(T item, int index) {
        if (index < 0 || index > array.length) {
            throw new RuntimeException("Invalid index: " + index + "; size: " + size());
        }

        if (size == array.length) {
            final int increaseCapacityBy = array.length * factor / 100 + 1;
            array = U.increaseArray(array, increaseCapacityBy, index);
        }

        array[index] = item;
        size += 1;
    }

    @Override
    public T remove(int index) {

        if (index < 0 || index >= size()) {
            throw new RuntimeException("Invalid index: " + index + "; size: " + size);
        }

        final T item = array[index];

        if ((array.length - size) > (size * factor / 100 + 1)) {
            array = U.removeItemAndDecreaseCapacity(array, index, size);
        } else {
            U.removeItem(array, index, size);
        }

        size -= 1;
        return item;
    }

}
