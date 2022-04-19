package utils;

public interface InterfaceStack<T> {
    T pop();
    void push(T element);
    T peak();
    boolean isEmpty();
}
