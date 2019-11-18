package task2.impl;

public class Queue<T> {
    private IArray<T> list;

    public Queue() {
        list = new DoubleLinkedList<T>();
    }

    public void enqueue(T item) {
        list.addFirst(item);
    }

    public T dequeue() {
        return list.removeFirst();
    }

    boolean isEmpty() {
        return list.isEmpty();
    }
}
