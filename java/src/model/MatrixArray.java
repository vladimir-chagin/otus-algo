package model;

public class MatrixArray<T> extends AbstractArray<T> {
    private static final int VECTOR = 10;
    private final int vector;

    private IArray<IArray<T>> array;

    public MatrixArray(int vector) {
        super(0);
        this.vector = vector;
        array = new SingleArray<>();
    }

    public MatrixArray() {
        this(VECTOR);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void add(T item) {
        if (size == array.size() * vector) {
            addSubArray();
        }

        array.get(size / vector).add(item);
        size += 1;
    }

    @Override
    public T get(int index) {
        final int vIndex = index / size;
        final int hIndex = index % vector;

        return array.get(vIndex).get(hIndex);
    }

    @Override
    public void add(T item, int index) {
        final int vIndex = index / size;
        final int hIndex = index % vector;

        T tmpItem = item;

        for (int i = vIndex; i <= array.size(); i += 1) {
            if (array.size() == i) {
                addSubArray();
            }

            IArray<T> hArr = array.get(i);

            if (hArr.size() < vector) {
                hArr.add(item, hIndex);
                break;
            }

            if (hArr.size() == vector) {
                T tmpItem2 = hArr.removeLast();

                if (i == vIndex) {
                    hArr.add(tmpItem, hIndex);
                } else {
                    hArr.addFirst(tmpItem);
                }
                tmpItem = tmpItem2;
                continue;
            }

            hArr.addFirst(tmpItem);
            break;
        }

        size += 1;
        //TODO: move items to right
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new RuntimeException("Invalid index: " + index + "; size: " + size);
        }

        final int vIndex = index / size;
        final int hIndex = index % vector;

        final IArray<T> hArray = array.get(vIndex);
        final T removedItem = hArray.remove(hIndex);

        if (vIndex < size - 1) {
            for (int i = vIndex; i < size - 1; i += 1) {
                final IArray<T> arr1 = array.get(i);
                final IArray<T> arr2 = array.get(i + 1);

                final T item = arr2.removeFirst();
                arr1.add(item);
            }
        }

        if (hArray.isEmpty()) {
            array.removeLast();
        }

        return removedItem;
    }

    private final void addSubArray() {
        array.add(new VectorArray<T>(vector));
    }
}
