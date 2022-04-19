package interfaces;

public interface Queue<E> {

    void enqueue(E item); // adding new element to the end of the queue

    E dequeue(); // delete and return the first element in the queue

    E peak(); // return the first element in the queue

    boolean isEmpty(); // checking if the queue is empty
}
