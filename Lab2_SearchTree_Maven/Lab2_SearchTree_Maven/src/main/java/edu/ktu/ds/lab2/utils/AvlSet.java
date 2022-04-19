package edu.ktu.ds.lab2.utils;

import java.util.Comparator;

/**
 * Sorted object collections - realization of a set with AVL-tree.
 *
 * @param <E> Set element type. The Comparable <E> interface must be satisfied, or
 *            an object satisfying the Comparator <E> interface must be passed
 *            through the class constructor.
 * 
 * @author darius.matulis@ktu.lt
 * @užduotis Review and clarify the methods provided.
 */
public class AvlSet<E extends Comparable<E>> extends BstSet<E>
        implements SortedSet<E> {

    public AvlSet() {
    }

    public AvlSet(Comparator<? super E> c) {
        super(c);
    }

    /**
     * A new element is added to the set.
     *
     * @param element
     */
    @Override
    public void add(E element) {
        if (element == null) {
            throw new IllegalArgumentException("Element is null in add(E element)");
        }
        root = addRecursive(element, (AVLNode<E>) root);

    }

    /**
     * The private recursive method is used in the add method;
     *
     * @param element
     * @param node
     * @return
     */
    private AVLNode<E> addRecursive(E element, AVLNode<E> node) {
        if (node == null) {
            size++;
            return new AVLNode<>(element);
        }
        int cmp = c.compare(element, node.element);

        if (cmp < 0) {
            node.setLeft(addRecursive(element, node.getLeft()));
            if ((height(node.getLeft()) - height(node.getRight())) == 2) {
                int cmp2 = c.compare(element, node.getLeft().element);
                node = (cmp2 < 0) ? rightRotation(node) : doubleRightRotation(node);
            }
        } else if (cmp > 0) {
            node.setRight(addRecursive(element, node.getRight()));
            if ((height(node.getRight()) - height(node.getLeft())) == 2) {
                int cmp2 = c.compare(node.getRight().element, element);
                node = (cmp2 < 0) ? leftRotation(node) : doubleLeftRotation(node);
            }
        }
        node.height = Math.max(height(node.getLeft()), height(node.getRight())) + 1;
        return node;
    }

    /**
     * Removes an item from a set.
     *
     * @param element
     */
    @Override
    public void remove(E element) {
        if (element == null) {
            throw new IllegalArgumentException("Element is null in remove(E element)");
        }

        root = removeRecursive(element, (AVLNode<E>) root);
    }

    private AVLNode<E> removeRecursive(E element, AVLNode<E> n) {

        if (n == null) {
            return n;
        }
        int cmp = c.compare(element, n.element);

        if (cmp < 0) {
            n.setLeft(removeRecursive(element, n.getLeft()));
            // balancing
            if ((height(n.getLeft()) - height(n.getRight())) == 2) {
                int cmp2 = c.compare(element, n.left.element);
                n = (cmp2 < 0) ? rightRotation(n) : doubleRightRotation(n);
            }
        } else if (cmp > 0) {
            n.setRight(removeRecursive(element, n.getRight()));
            // balancing
            if ((height(n.getRight()) - height(n.getLeft())) == 2) {
                int cmp2 = c.compare(n.right.element, element);
                n = (cmp2 < 0) ? leftRotation(n) : doubleLeftRotation(n);
            }
        } else if (n.getLeft() != null && n.getRight() != null) {
            n.element = ((AVLNode<E>) getMax(n.getLeft())).element;
            n.setLeft(removeMax(n.getLeft()));
            size--;
            // balancing
            if ((height(n.getLeft()) - height(n.getRight())) == 2) {
                int cmp2 = c.compare(element, n.left.element);
                n = (cmp2 < 0) ? rightRotation(n) : doubleRightRotation(n);
            }
            // other
        } else {
            n = (n.getLeft() != null) ? n.getLeft() : n.getRight();
            size--;
        }
        if (n != null) {
            n.height = Math.max(height(n.getLeft()), height(n.getRight())) + 1;
        }
        return n;
    }

    private AVLNode<E> removeMax(AVLNode<E> node) {
        if (node == null) {
            return null;
        } else if (node.right != null) {
            node.right = removeMax(node.right);
            return node;
        } else {
            return node.getLeft();
        }
    }

    // Additional private methods used to implement operations with the set
    // AVL-tree;

    //           n2
    //          /                n1
    //         n1      ==>      /  \
    //        /                n3  n2
    //       n3

    private AVLNode<E> rightRotation(AVLNode<E> n2) {
        AVLNode<E> n1 = n2.getLeft();
        n2.setLeft(n1.getRight());
        n1.setRight(n2);
        n2.height = Math.max(height(n2.getLeft()), height(n2.getRight())) + 1;
        n1.height = Math.max(height(n1.getLeft()), height(n2)) + 1;
        return n1;
    }

    private AVLNode<E> leftRotation(AVLNode<E> n1) {
        AVLNode<E> n2 = n1.getRight();
        n1.setRight(n2.getLeft());
        n2.setLeft(n1);
        n1.height = Math.max(height(n1.getLeft()), height(n1.getRight())) + 1;
        n2.height = Math.max(height(n2.getRight()), height(n1)) + 1;
        return n2;
    }

    //            n3               n3
    //           /                /                n2
    //          n1      ==>      n2      ==>      /  \
    //           \              /                n1  n3
    //            n2           n1
    //
    private AVLNode<E> doubleRightRotation(AVLNode<E> n3) {
        n3.left = leftRotation(n3.getLeft());
        return rightRotation(n3);
    }

    private AVLNode<E> doubleLeftRotation(AVLNode<E> n1) {
        n1.right = rightRotation(n1.getRight());
        return leftRotation(n1);
    }

    private int height(AVLNode<E> n) {
        return (n == null) ? -1 : n.height;
    }

    /**
     * Internal class of the collection node
     *
     * @param <N> node element data type
     */
    protected class AVLNode<N> extends BstNode<N> {

        protected int height;

        protected AVLNode(N element) {
            super(element);
            this.height = 0;
        }

        protected void setLeft(AVLNode<N> left) {
            this.left = left;
        }

        protected AVLNode<N> getLeft() {
            return (AVLNode<N>) left;
        }

        protected void setRight(AVLNode<N> right) {
            this.right = right;
        }

        protected AVLNode<N> getRight() {
            return (AVLNode<N>) right;
        }
    }
}
