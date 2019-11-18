package task2.impl;

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
        if (size == array.size() * vector) {
            addSubArray();
        }

        if (index > size) {
            throw new RuntimeException("Invalid index: " + index + "; size: " + size);
        }

        final int vIndex = index / vector;
        final int hIndex = index % vector;

        T tmpItem = item;

        for (int i = vIndex; i < array.size(); i += 1) {
            IArray<T> hArr = array.get(i);

            if (i == vIndex) {
                if (hArr.size() == vector) {
                    T tmpItem2 = hArr.removeLast();
                    hArr.add(tmpItem, hIndex);
                    tmpItem = tmpItem2;
                } else {
                    hArr.add(item, hIndex);
                    break;
                }
            }

            if (hArr.size() == vector) {
                T tmpItem2 = hArr.removeLast();
                hArr.addFirst(item);
                tmpItem = tmpItem2;
            } else {
                hArr.addFirst(tmpItem);
                break;
            }
        }

        size += 1;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new RuntimeException("Invalid index: " + index + "; size: " + size);
        }

        final int vIndex = index / vector;
        final int hIndex = index % vector;

        final IArray<T> hArray = array.get(vIndex);
        final T removedItem = hArray.remove(hIndex);

        if (vIndex < array.size() - 1) {
            for (int i = vIndex; i < array.size() - 1; i += 1) {
                final IArray<T> arr1 = array.get(i);
                final IArray<T> arr2 = array.get(i + 1);
                if (!arr2.isEmpty()) {
                    final T item = arr2.removeFirst();
                    arr1.add(item);
                } else {
                    array.remove(i + 1);
                    break;
                }
            }
        }

        size -= 1;
        return removedItem;
    }

    private final void addSubArray() {
        array.add(new VectorArray<T>(vector));
    }

    public void print() {
        System.out.println("--------------------------");
        for (int i = 0; i < array.size(); i += 1) {
            IArray<T> arr = array.get(i);
            ((AbstractArray)arr).print();
        }

    }
}
