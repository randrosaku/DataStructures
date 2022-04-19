package utils;

import java.util.Iterator;
/*
Implement all interface methods based on a linked list.
Do not use the java class LinkedList <E>, try to write all the logic yourself.
Additional methods and variables can be developed as needed.
*/
public class LinkedList<T> implements List<T> {

    private class Node<T> {

        public T data;
        public Node<T> next;

        public Node(T data, Node<T> address) {
            this.data = data;
            this.next = address;
        }

        public void setNext(Node<T> newNext) {
            this.next = newNext;
        }

        public Node<T> getNext() {
            return next;
        }

        public void setData(T newData) {
            this.data = newData;
        }
    }

    private Node<T> head;
    private Node<T> tail;
    private Node<T> d;

    public int count;

    public LinkedList() {

        this.head = null;
        this.tail = null;
        this.d = null;
    }

    @Override
    public void add(T item) {

        if(head != null) {
            tail.next = new Node<T>(item, null);
            tail = tail.next;
        } else {
            head = new Node<T>(item, null);
            tail = head;
        }
        count++;
    }

    @Override
    public T get(int index) {

        var data = head.data;
        var node = head;

        for(int i = 0; i < index; i++) {
            node = node.next;
            data = node.data;
        }
        return (index < count && index >= 0) ? data : null;
    }

    @Override
    public boolean remove(T item) {

        boolean remove = false;
        if(head == null) return remove;

        var current = head;
        if(current.data.equals(item)) {
            remove = true;
            head = current.next;
            tail = head;
            count--;
        } else {
            for(Node<T> d1 = head; d1 != null; d1 = d1.next){
                if (d1.next != null && item.equals(d1.next.data))
                {
                    current = d1;
                    remove = true;
                    break;
                }
            }
            if(remove) {
                current.next = current.next.next;
                count--;
            }
        }
        return remove;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < count && get(currentIndex) != null;
            }

            @Override
            public T next() {
                return get(currentIndex++);
            }
        };
    }

    public T removeFirst() {
        if(head == null) return null;

        var data = head.data;
        head = head.next;
        count--;

        return data;
    }
}
