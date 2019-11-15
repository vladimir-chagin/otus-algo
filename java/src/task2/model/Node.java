package task2.model;

public class Node<T> {
    private Node next;
    private Node prev;

    private T item;

    public Node(T item) {
        this(item, null);
    }

    public Node(T item, Node<T> next) {
        this(item, next, null);
    }

    public Node(T item, Node<T> next, Node<T> prev) {
        this.item = item;
        this.next = next;
        this.prev = prev;
    }

    Node<T> getNext() {
        return next;
    }

    void setNext(Node<T> next) {
        this.next = next;
    }

    boolean hasNext() {
        return next != null;
    }

    Node<T> getPrev() {
        return prev;
    }

    void setPrev(Node<T> prev) {
        this.prev = prev;
    }

    boolean hasPrev() {
        return prev != null;
    }

    void setItem(T item) {
        this.item = item;
    }

    T getItem() {
        return item;
    }
}
