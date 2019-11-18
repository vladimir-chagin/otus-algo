package task2.impl;

import util.U;

import java.util.Arrays;

public abstract class AbstractArray<T> implements IArray<T> {

    protected int size;
    protected T[] array;
    protected final int initialCapacity;

    AbstractArray(final int initialCapacity) {
        clear(this.initialCapacity = initialCapacity);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public final void addFirst(T item) {
        add(item, 0);
    }

    @Override
    public final T removeFirst() {
        return remove(0);
    }

    @Override
    public void add(T item) {
        add(item, size);
    }

    @Override
    public final T removeLast() {
        return remove(size() - 1);
    }

    @Override
    public final boolean isEmpty() {
        return size() <= 0;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }

    @Override
    public int indexOf(T item) {
        for (int i = 0; i < size; i += 1) {
            if (array[i] == item) {
                return i;
            }
        }

        return -1;
    }

    public void clear() {
        clear(initialCapacity);
    }

    private void clear(int initialCapacity) {
        size = 0;
        array = U.newArray(initialCapacity);
    }

    public void print() {
        System.out.println(Arrays.toString(array) + "." + array.length + "." + size);
    }
}
