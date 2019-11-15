package task2.model;

public class SortedArray<T extends IPrioritable> extends SingleArray<T> {

    public SortedArray() {
        super();
    }

    @Override
    public void add(T item) {
        int idx = indexOf(item.getPriority());
        if (idx < 0) {
            idx = ~idx;
        }
        add(item, idx);
    }


    public int indexOf(int priority) {
        int l = 0;
        int r = array.length - 1;

        while(l <= r) {
            int m = (r + l) / 2;
            IPrioritable tmpItem = array[m];
            int compareResult = Integer.compare(tmpItem.getPriority(), priority);
            if (compareResult == 0) {
                return m;
            } else if (compareResult < 0) {
                l = m + 1;
            } else if (compareResult > 0) {
                r = m - 1;
            }
        }

        return ~r;
    }
}
