package utils;
import java.util.ArrayList;
import interfaces.Queue;

public class ArrayQueue<E> implements Queue<E> {

    private ArrayList<E> array;

    public ArrayQueue() {

        array = new ArrayList<>();
    }

    public int size() {

        return array.size();
    }

    public E get(int index) {

        return array.get(index);
    }

    @Override
    public void enqueue(E item) {

        array.add(item);
    }

    @Override
    public E dequeue() {
        if(!isEmpty()) {
            E obj = array.get(0);
            array.remove(0);
            return obj;
        }
        return null;
    }

    @Override
    public E peak() {

        return !isEmpty() ? array.get(0) : null;
    }

    @Override
    public boolean isEmpty() {

        return array.size() == 0;
    }
}
