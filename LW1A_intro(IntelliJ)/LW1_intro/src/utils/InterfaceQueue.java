package utils;

public interface InterfaceQueue<T> {
    void enqueue(T element);
    T dequeue();
    T peak();
    boolean isEmpty();
}
