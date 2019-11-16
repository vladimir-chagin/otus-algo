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
        if (index < 0 || index > size) {
            throw new RuntimeException("Invalid add index");
        }

        if (array.length == size) {
            array = U.increaseArray(array, vector, index);
        } else {
            U.shiftItemsFromIndex(array, index, size);
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

        if (array.length - size + 1 >= vector && array.length > vector) {
            array = U.removeItemAndDecreaseCapacity(array, index, size);
        } else {
            U.removeItem(array, index, size);
        }

        size -= 1;
        return item;
    }

}
