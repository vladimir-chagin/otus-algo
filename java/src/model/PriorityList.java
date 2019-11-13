package model;

public class PriorityList<T> extends DoubleLinkedList<T> implements IPrioritable {
    private int priority;

    public PriorityList(final int priority) {
        this.priority = priority;
    }

    public int getPriority() {
        return priority;
    }
}
