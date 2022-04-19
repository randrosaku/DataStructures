package utils;
import java.util.LinkedList;
import interfaces.Queue;

public class LinkedListQueue<E> implements Queue<E> {

    private LinkedList<E> linkedList;

    public LinkedListQueue() {

        linkedList = new LinkedList<>();
    }

    public int size() {

        return linkedList.size();
    }

    public E get(int index) {
        return linkedList.get(index);
    }

    @Override
    public void enqueue(E item) {

        linkedList.add(item);
    }

    @Override
    public E dequeue() {
        if(!isEmpty()){
            E obj = linkedList.get(0);
            linkedList.remove(0);
            return obj;
        }
        return null;
    }

    @Override
    public E peak() {

        return !isEmpty() ? linkedList.get(0) : null;
    }

    @Override
    public boolean isEmpty() {

        return linkedList.size() == 0;
    }
}
