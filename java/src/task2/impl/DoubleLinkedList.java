package task2.impl;

public class DoubleLinkedList<T> implements IArray<T> {

    private Node<T> head;
    private Node<T> tail;

    private int size;

    public DoubleLinkedList() {
        clear();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void add(T item) {
        if (head == null) {
            head = tail = new Node<>(item);
        } else {
            final Node<T> node = new Node<T>(item, null, tail);
            tail.setNext(node);
            node.setPrev(tail);
            tail = node;
        }
        size += 1;
    }

    @Override
    public void add(T item, int index) {
        if (index < 0 || index > size) {
            throw new RuntimeException("Invalid index");
        }

        if (index == 0) {
            addFirst(item);
            return;
        }

        if (index == size) {
            add(item);
            return;
        }

        Node<T> prevNode = getNode(index -1);
        Node<T> nextNode = prevNode.getNext();

        Node<T> newNode = new Node<>(item, nextNode, prevNode);

        prevNode.setNext(newNode);
        nextNode.setPrev(newNode);
        size += 1;
    }

    @Override
    public void addFirst(T item) {
        if (head == null) {
            head = tail = new Node<>(item);
        } else {
            final Node<T> node = new Node<>(item, head);
            head.setPrev(node);
            head = node;
        }
        size += 1;
    }

    @Override
    public T get(int index) {
        return getNode(index).getItem();
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new RuntimeException("Invalid index: " + index + "; size: " + size);
        }

        Node<T> node = getNode(index);

        Node<T> prevNode = node.getPrev();
        Node<T> nextNode = node.getNext();


        if (prevNode == null && nextNode == null) {
            head = tail = null;
        } else if (prevNode == null) {
            nextNode.setPrev(null);
            head = nextNode;
        } else if (nextNode == null) {
            prevNode.setNext(null);
            tail = prevNode;
        } else {    //prev and next node are not null
            prevNode.setNext(nextNode);
            nextNode.setPrev(prevNode);
        }

        node.setNext(null);
        node.setPrev(null);

        size -= 1;
        return node.getItem();
    }

    @Override
    public T removeFirst() {
        return remove(0);
    }

    @Override
    public T removeLast() {
        return remove(size - 1);
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int indexOf(T item) {
        throw new RuntimeException("Not implemented");
    }

    private Node<T> getNode(final int index) {
        if (index < 0 || index > size) {
            throw new RuntimeException("Invalid index: " + index + "; size: " + size);
        }

        int forwardIdx = 0;
        int backwardIndex = size - 1;

        Node<T> headNode = head;
        Node<T> tailNode = tail;

        while(forwardIdx < index && backwardIndex > index) {
            if (headNode.hasNext()) {
                headNode = headNode.getNext();
                forwardIdx += 1;
            }
            if (tailNode.hasPrev()) {
                tailNode = tailNode.getPrev();
                backwardIndex -= 1;
            }
        }

        if (forwardIdx == index) {
            return headNode;
        }

        if (backwardIndex == index) {
            return tailNode;
        }

        throw new RuntimeException("Item not found");
    }

    @Override
    public void clear() {
        head = tail = null;
        size = 0;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
//        StringBuilder b = new StringBuilder();
//        b.append("S=").append(size);
//        b.append("[");
//        Node<T> n = head;
//        int cnt = 0;
//        while(n != null) {
//            b.append(n.getItem());
//            if (cnt < size - 1) {
//                b.append(", ");
//            }
//            n = n.getNext();
//            cnt += 1;
//        }
//        b.append("]");
//        return b.toString();
    }
}
