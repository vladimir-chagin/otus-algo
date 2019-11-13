package model;

public class SingleArray<T> extends AbstractArray<T> {

    public SingleArray () {
        super(0);
    }

    @Override
    public int size() {
        return array.length;
    }

    @Override
    public void add(T item) {
        array = U.increaseArray(array, 1);
        array[array.length - 1] = item;
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
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= size()) {
            throw new RuntimeException("Invalid index: " + index + "; size: " + size());
        }

        final T item = array[index];

        array = U.reduceArray(array, 1, index);

        return item;
    }

}
