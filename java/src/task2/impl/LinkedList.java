package task2.impl;

public class LinkedList<T> implements IArray<T> {

    private Node<T> head;
    private int size;

    public LinkedList() {
        clear();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void add(T item) {
        add(item, size);
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
            prevNode.setNext(new Node<T>(item, prevNode.getNext()));
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
        if (index >= size) {
            throw new RuntimeException("Invalid index: " + index + "; size = " + size);
        }

        if (index == 0) {
            Node<T> node = head;
            head = head.getNext();
            size -= 1;
            return node.getItem();
        }

        Node<T> prevNode = getNode(index - 1);
        Node<T> node = prevNode.getNext();

        prevNode.setNext(node.getNext());

//        node.setNext(null);
        size -= 1;

        return node.getItem();
    }

    @Override
    public T removeFirst() {
        return remove(0);
    }

    @Override
    public T removeLast() {
        if (size <= 0) {
            throw new RuntimeException("List is empty");
        }

        Node<T> node;
        if (size == 1) {
            node = head;
            head = null;
        } else {
            Node<T> preLast = getNode(size - 2);
            Node<T> last = preLast.getNext();
            preLast.setNext(null);
            node = last;
        }

        size -= 1;
        return node.getItem();
    }

    @Override
    public int indexOf(T item) {
        int cnt = 0;
        Node<T> node = head;
        while(node != null) {
            if (node.getItem() == item) {
                return cnt;
            }
            node = node.getNext();
        }

        return -1;
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public void clear() {
        head = null;
        size = 0;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }

    private Node<T> getNode(int index) {
        int cnt = 0;
        Node<T> node = head;
        while(cnt < index) {
            node = node.getNext();
            cnt += 1;
        }

        if (cnt == index) {
            return node;
        }

        throw new RuntimeException("Invalid index: " + index + "; size: " + size);
    }


}
