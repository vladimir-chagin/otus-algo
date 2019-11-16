package task2.model;

import java.util.Arrays;

public abstract class AbstractArray<T> implements IArray<T> {

    protected int size;
    protected T[] array;

    AbstractArray(final int initialCapacity) {
        size = 0;
        array = (T[])new Object[initialCapacity];
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

    public void print() {
        System.out.println("L=" + array.length + "; S=" + size +"; " + Arrays.toString(array));
    }
}
