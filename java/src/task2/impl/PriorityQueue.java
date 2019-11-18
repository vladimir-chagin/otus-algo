package task2.impl;

public class PriorityQueue<T> {

    private SortedArray<PriorityList<T>> array;
    private int size;

    private PriorityList<T> tmpList = new PriorityList<>(0);

    public PriorityQueue() {
        array = new SortedArray<>();
        size = 0;
    }

    public void enqueue(int priority, T item) {
        tmpList.setPriority(priority);

        int idx = array.indexOf(tmpList);

        final PriorityList<T> queue = idx < 0 ? new PriorityList<>(priority) : array.get(idx);
        if (idx < 0) {
            array.add(queue, ~idx);
        }
        queue.add(item);
        size += 1;
    }

    public T dequeue() {
        if (size <= 0) {
            throw new RuntimeException("Queue is empty");
        }

        PriorityList<T> queue = array.get(0);
        T item = queue.removeFirst();
        size -= 1;
        if (queue.isEmpty()) {
            array.removeFirst();
        }

        return item;
    }

    public boolean isEmpty() {
        return size <= 0;
    }

    public void clear() {
        for (int i = 0; i < array.size(); i += 1) {
            array.get(i).clear();
        }
        array.clear();
    }
}
