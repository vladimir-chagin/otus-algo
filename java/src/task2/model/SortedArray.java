package task2.model;

public class SortedArray<T extends Object & Comparable<T>> extends SingleArray<T> {

    public SortedArray() {
        super();
    }

    @Override
    public void add(T item) {
        int idx = indexOf(item);
        if (idx < 0) {
            idx = ~idx;
        }
        add(item, idx);
    }

    public int indexOf(T item) {
        if (array.length <= 0) {
            return ~0;
        }

        int l = 0;
        int r = array.length - 1;

        while(l <= r) {
            int m = (r + l) / 2;
            Comparable tmpItem = array[m];
            int compareResult = tmpItem.compareTo(item);
            if (compareResult < 0) {
                l = m + 1;
            } else if (compareResult > 0) {
                r = m - 1;
            } else {
                return m;
            }
        }

        return ~l;
    }
}
