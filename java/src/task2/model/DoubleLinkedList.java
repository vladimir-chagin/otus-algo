package task2.model;

public class DoubleLinkedList<T> implements IArray<T> {

    private Node<T> head;
    private Node<T> tail;

    private int size;

    public DoubleLinkedList() {
        head = tail = null;
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void add(T item) {
        if (head == null) {
            head = tail = new Node<T>(item);
        } else {
            final Node<T> node = new Node<T>(item, null, tail);
            tail.setNext(node);
            tail = node;
        }
        size += 1;
    }

    @Override
    public void add(T item, int index) {
        if (index < 0 || index > size) {
            throw new RuntimeException("Invalid index");
        }

        Node<T> newNode = new Node(item);
        Node<T> node = index < size ? getNode(index) : null;

        //list is empty or add to the end;

        //list isEmpty or adding to he end of list
        if (node == null) {
            if (tail != null) {//list is not empty, adding to the end
                tail.setNext(newNode);
                newNode.setPrev(tail);
            } else {// no nodes in list
                head = tail = newNode;//list was empty
            }
            size += 1;
            return;
        }

        Node<T> prevNode = node.getPrev();

        if (prevNode != null) {
            newNode.setPrev(prevNode);
            prevNode.setNext(newNode);
            newNode.setNext(node);
            node.setPrev(newNode);
        } else {
            head.setPrev(newNode);
            newNode.setNext(head);
            head = newNode;
        }

        size += 1;
    }

    @Override
    public void addFirst(T item) {
        add(item, 0);
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

        if (prevNode != null) {
            prevNode.setNext(nextNode);
        } else {//current node is head
            head = nextNode;
        }

        if (nextNode != null) {
            nextNode.setPrev(prevNode);
        } else {//current node is tail
            tail = prevNode;
        }

        node.setPrev(null);
        node.setNext(null);
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
            if (tailNode.hasNext()) {
                tailNode = tailNode.getNext();
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

    public String toString() {
        StringBuilder b = new StringBuilder();
        b.append("S=").append(size);
        b.append("[");
        Node<T> n = head;
        int cnt = 0;
        while(n != null) {
            b.append(n.getItem());
            if (cnt < size - 1) {
                b.append(", ");
            }
            n = n.getNext();
            cnt += 1;
        }
        b.append("]");
        return b.toString();
    }
}
