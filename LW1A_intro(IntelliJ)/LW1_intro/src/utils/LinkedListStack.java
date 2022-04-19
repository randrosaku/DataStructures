package utils;
import java.util.LinkedList;
import interfaces.Stack;

public class LinkedListStack<E> implements Stack<E> {

    private LinkedList<E> linkedList;

    public LinkedListStack() {
        linkedList = new LinkedList<>();
    }

    public int size() {
        return linkedList.size();
    }

    public E get(int index) {
        return linkedList.get(index);
    }

    @Override
    public E pop() {
        if(!isEmpty()) {
            E obj = linkedList.get(linkedList.size()-1);
            linkedList.remove(linkedList.size()-1);
            return obj;
        }
        return null;
    }

    @Override
    public void push(E item) {

        linkedList.add(item);
    }

    @Override
    public E peak() {
        if(!isEmpty()) {
            return linkedList.get(linkedList.size()-1);
        }
        return null;
    }

    @Override
    public boolean isEmpty() {

        return linkedList.size() == 0;
    }
}
