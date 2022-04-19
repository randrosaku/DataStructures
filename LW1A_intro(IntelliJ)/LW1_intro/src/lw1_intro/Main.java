/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lw1_intro;

import models.Student;
import utils.*;

import java.util.Iterator;

public class Main {

    public static void main(String[] args) {
        String filePath = "input_data.txt";

        Array<Student> students_arr = new Array<Student>();

        DataReader.readFromFile(filePath, students_arr);
        LinkedList<Student> students_llist = new LinkedList<Student>();

// After implementing the LinkedList class and commenting out, we should get the following
// same results as for Class Array
        DataReader.readFromFile(filePath, students_llist);

        //System.out.println("\n============Tests with linked list implementation============\n");
        performTests(students_llist);


        System.out.println("-----------------");
        System.out.println(students_llist.removeFirst());
        System.out.println("-----------------");
        performTests(students_llist);


    }

    private static void performTests(List<Student> students) {
        System.out.println("Students list:");

        for(Student student : students){
            System.out.println(student);
        }

    }

    private static void testArrayQueue() {
        ArrayQueue<Integer> arrayQueue = new ArrayQueue<>();
        arrayQueue.enqueue(1);
        arrayQueue.enqueue(6);
        arrayQueue.enqueue(9);
        arrayQueue.enqueue(3);

        System.out.println("\nArray queue: ");
        for (int i = 0; i < arrayQueue.size(); i++){
            System.out.println(arrayQueue.get(i));
        }
        System.out.println("\nRemove and return the first element: ");
        System.out.println(arrayQueue.dequeue());
        System.out.println("\nReturn the current first element: ");
        System.out.println(arrayQueue.peak());

        if(arrayQueue.isEmpty())
            System.out.println("\nThe Array queue is empty.");
        else {
            System.out.println("\nThe Array queue is not empty.");
        }
    }

    private static void testLinkedListQueue() {
        LinkedListQueue<String> linkedListQueue = new LinkedListQueue<>();
        linkedListQueue.enqueue("London");
        linkedListQueue.enqueue("New York");
        linkedListQueue.enqueue("Vilnius");
        linkedListQueue.enqueue("Rome");

        System.out.println("\nLinkedList queue: ");
        for (int i = 0; i < linkedListQueue.size(); i++){
            System.out.println(linkedListQueue.get(i));
        }
        System.out.println("\nRemove and return the first element: ");
        System.out.println(linkedListQueue.dequeue());
        System.out.println("\nReturn the current first element: ");
        System.out.println(linkedListQueue.peak());

        if(linkedListQueue.isEmpty())
            System.out.println("\nThe LinkedList queue is empty.");
        else {
        System.out.println("\nThe LinkedList queue is not empty.");
        }
    }

    private static void testLinkedListStack() {
        LinkedListStack<Double> linkedListStack = new LinkedListStack<>();
        linkedListStack.push(0.8);
        linkedListStack.push(1.14);
        linkedListStack.push(2.3);
        linkedListStack.push(15.0);

        System.out.println("\nLinkedList stack: ");
        for (int i = 0; i < linkedListStack.size(); i++){
            System.out.println(linkedListStack.get(i));
        }
        System.out.println("\nRemove and return the first element: ");
        System.out.println(linkedListStack.pop());
        System.out.println("\nReturn the current first element: ");
        System.out.println(linkedListStack.peak());

        if(linkedListStack.isEmpty())
            System.out.println("\nThe LinkedList stack is empty.");
        else {
        System.out.println("\nThe LinkedList stack is not empty.");
        }
    }

    private static void testArrayStack() {
        ArrayStack<Integer> arrayStack = new ArrayStack<>();
        arrayStack.push(8);
        arrayStack.push(14);
        arrayStack.push(23);
        arrayStack.push(150);

        System.out.println("\nArray stack: ");
        for (int i = 0; i < arrayStack.size(); i++){
            System.out.println(arrayStack.get(i));
        }
        System.out.println("\nRemove and return the first element: ");
        System.out.println(arrayStack.pop());
        System.out.println(arrayStack.pop());
        System.out.println(arrayStack.pop());
        System.out.println(arrayStack.pop());
        System.out.println("\nReturn the current first element: ");
        System.out.println(arrayStack.peak());

        if(arrayStack.isEmpty())
            System.out.println("\nThe Array stack is empty.");
        else {
            System.out.println("\nThe Array stack is not empty.");
        }
    }

    /*private static <T> boolean addAll(int index, LinkedList<T> c) {

        if(index == 0) {
            return false;
        }
        int i = 0;

        for(T item : c)
        {
            if(i >= index) {
                return false;
            } else {
                c.add(item);
            }
            i++;
        }
        return true;
    }*/
}
