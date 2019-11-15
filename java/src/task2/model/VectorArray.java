package task2.model;

import util.U;

public class VectorArray<T> extends AbstractArray<T> {
    private final int vector;

    public VectorArray(int vector) {
        super(0);
        this.vector = vector;
    }

    public VectorArray() {
        this(10);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void add(T item) {
        if (size() == array.length) {
            array = U.increaseArray(array, vector);
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
        if (index < 0 || index > size()) {
            throw new RuntimeException("Invalid add index");
        }

        final int increaseBy = array.length == size() ? vector : 0;
        U.increaseArray(array, increaseBy, index);
        array[index] = item;
        size += 1;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= size()) {
            throw new RuntimeException("Invalid index: " + index + "; size: " + size());
        }

        final T item = array[index];

        final int reduceSizeBy = array.length - size() >=  vector ? vector : 0;
        if (reduceSizeBy == 0) {
            U.increaseArray(array, 0, index);
        } else {
            U.reduceArray(array, reduceSizeBy, index);
        }

        return item;
    }

}
