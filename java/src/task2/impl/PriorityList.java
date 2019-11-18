package task2.impl;

public class PriorityList<T> extends DoubleLinkedList<T> implements Comparable<PriorityList<T>> {
    private int priority;

    public PriorityList(final int priority) {
        this.priority = priority;
    }

    public void setPriority(int p) {
        this.priority = p;
    }

    @Override
    public int compareTo(PriorityList<T> o) {
        return Integer.compare(priority, o.priority);
    }

    @Override
    public String toString() {
        return "p=" + priority + "; " + super.toString();
    }
}
