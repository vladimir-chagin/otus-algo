package task2.model;
import task2.model.*;

public class PriorityList<T> extends DoubleLinkedList<T> implements IPrioritable {
    private int priority;

    public PriorityList(final int priority) {
        this.priority = priority;
    }

    public int getPriority() {
        return priority;
    }
}
