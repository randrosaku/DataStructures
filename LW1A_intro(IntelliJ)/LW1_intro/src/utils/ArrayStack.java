package utils;
import java.util.ArrayList;
import interfaces.Stack;

public class ArrayStack<E> implements Stack<E> {

    private ArrayList<E> array;

    public ArrayStack() {
        array = new ArrayList<>();
    }

    public int size() {
        return array.size();
    }

    public E get(int index) {
        return array.get(index);
    }

    @Override
    public E pop() {
        if(!isEmpty()) {
            E obj = array.get(array.size()-1);
            array.remove(array.size()-1);
            return obj;
        }
        return  null;
    }

    @Override
    public void push(E item) {

        array.add(item);
    }

    @Override
    public E peak() {
        if(!isEmpty()) {
            return array.get(array.size()-1);
        }
        return null;
    }

    @Override
    public boolean isEmpty() {

        return array.size() == 0;
    }
}
