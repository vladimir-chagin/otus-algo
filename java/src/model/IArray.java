package model;

public interface IArray<T> {
    // returns size
    int size();

    //adds item to the end
    void add(T item);

    //adds item into given position
    void add(T item, int index); // with shift to tail

    //adds item to the beginning
    void addFirst(T item);

    //returns item with index position
    T get(int index);

    //remove item and returns it
    T remove(int index);

    //removes the very first item and returns it
    T removeFirst();

    //removes the very last item and returns is
    T removeLast();

    //return true if no items, otherwise false
    boolean isEmpty();

}
