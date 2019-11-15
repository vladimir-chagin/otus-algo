package task2.model;

public class SpaceArray<T> extends AbstractArray<T> {

    private IArray<IArray<T>> array;
    private int size;
    private int blockSize = 2;

    public SpaceArray() {
        super(0);
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public void add(T item) {

    }

    @Override
    public void add(T item, int index) {

    }

    @Override
    public T get(int index) {
        return null;
    }

    @Override
    public T remove(int index) {
        return null;
    }
}
