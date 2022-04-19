package interfaces;

public interface Stack<E>{

    E pop(); // deleting and returning the first element in the stack

    void push(E item); // add a new element to the front of the stack

    E peak(); // return the first element in the stack

    boolean isEmpty(); // checking if the stack is empty
}
