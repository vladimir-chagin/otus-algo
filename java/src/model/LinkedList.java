package model;

public class LinkedList<T> implements IArray<T> {

    private Node<T> head;
    private int size;

    public LinkedList() {
        head = null;
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void add(T item) {
        add(item, 0);
    }

    @Override
    public void add(T item, int index) {
        if (index < 0 || index > size) {
            throw new RuntimeException("Invalid index: " + index);
        }

        if (index == 0) {
            head = new Node<T>(item, head);
        } else {
            Node<T> prevNode = getNode(index - 1);
            prevNode.setNext(new Node<T>(item, prevNode.getNext().getNext()));
        }

        size += 1;
    }

    @Override
    public void addFirst(T item) {
        head = new Node<T>(item, head);
    }

    @Override
    public T get(int index) {
        return getNode(index).getItem();
    }

    @Override
    public T remove(int index) {

        if (index == 0) {
            Node<T> node = head;
            head = head.getNext();
            size -=1;
            return node.getItem();
        }

        Node<T> prevNode = getNode(index - 1);
        Node<T> node = prevNode.getNext();

        prevNode.setNext(node.getNext());
        size -=1;
        return node.getItem();
    }

    @Override
    public T removeFirst() {
        return remove(0);
    }

    @Override
    public T removeLast() {
        Node<T> preLast = getNode(size - 1);
        if (preLast != null) {
            Node<T> last = preLast.getNext();
            preLast.setNext(null);
            return last.getItem();
        }

        throw new RuntimeException("Invalid index");
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    private Node<T> getNode(int index) {
        int cnt = 0;
        Node<T> node = head;
        while(cnt < index && node.hasNext()) {
            node = node.getNext();
            cnt += 1;
        }

        if (cnt == index) {
            return node;
        }

        throw new RuntimeException("Invalid index: " + index + "; size: " + size);
    }
}
