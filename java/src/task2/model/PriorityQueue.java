package task2.model;

public class PriorityQueue<T> {

    private SortedArray<PriorityList<T>> array;
    private int size;

    public PriorityQueue() {
        array = new SortedArray<>();
        size = 0;
    }

    public void enqueue(int priority, T item) {
        int idx = array.indexOf(priority);
        final PriorityList<T> queue = idx < 0 ? new PriorityList<T>(priority) : array.get(idx);
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


}
