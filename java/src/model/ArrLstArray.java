package model;

public class ArrLstArray<T> implements IArray<T> {
    private java.util.ArrayList<T> array;

    public ArrLstArray() {
        array = new java.util.ArrayList<T>();
    }

    @Override
    public int size() {
        return array.size();
    }

    @Override
    public void add(T item) {
        array.add(item);
    }

    @Override
    public void add(T item, int index) {
        array.add(index, item);
    }

    @Override
    public void addFirst(T item) {
        array.add(0, item);
    }

    @Override
    public T get(int index) {
        return array.get(index);
    }

    @Override
    public T remove(int index) {
        return array.remove(index);
    }

    @Override
    public T removeFirst() {
        return array.remove(0);
    }

    @Override
    public T removeLast() {
        return array.remove(array.size() - 1);
    }

    @Override
    public boolean isEmpty() {
        return array.size() <= 0;
    }

    @Override
    public String toString() {
        return getClass().getName();
    }
}
