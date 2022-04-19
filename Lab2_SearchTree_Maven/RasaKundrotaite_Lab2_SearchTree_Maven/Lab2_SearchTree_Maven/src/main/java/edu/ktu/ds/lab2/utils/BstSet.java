package edu.ktu.ds.lab2.utils;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Stack;

/**
 * Sorted object collections - realization of a set with a binary search tree.
 *
 * @param <E> Set element type. The Comparable <E> interface must be satisfied, or
 *            an object satisfying the Comparator <E> interface must be passed through the
 *            class constructor.
 *
 * @author darius.matulis@ktu.lt
 * @užduotis Review and clarify the methods provided.
 */
public class BstSet<E extends Comparable<E>> implements SortedSet<E>, Cloneable {

    // Tree root node
    protected BstNode<E> root = null;
    // Tree size
    protected int size = 0;
    // Arrow to comparator
    protected Comparator<? super E> c = null;

    /**
     * A set object is created for BS-tree keys using Comparable <E>
     */
    public BstSet() {
        this.c = Comparator.naturalOrder();
    }

    /**
     * A set object is created for BS-tree keys using Comparator <E>
     *
     * @param c Comparator
     */
    public BstSet(Comparator<? super E> c) {
        this.c = c;
    }

    /**
     * Checking that the set is empty.
     *
     * @return Returns true if the set is empty.
     */
    @Override
    public boolean isEmpty() {
        return root == null;
    }

    /**
     * @return Returns the number of items in the set.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * The set is cleared.
     */
    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    /**
     * Checks if an element exists in the set.
     *
     * @param element - Set element.
     * @return Returns true if an element exists in the set.
     */
    @Override
    public boolean contains(E element) {
        if (element == null) {
            throw new IllegalArgumentException("Element is null in contains(E element)");
        }

        return get(element) != null;
    }

    /**
     * It is checked whether all the elements of both sets exist in the set
     *
     * @param set set
     * @return
     */
    @Override
    public boolean containsAll(Set<E> set) {
        if(set == null) {
            throw new UnsupportedOperationException("The given tree is empty.");
        }
        if(root == null) {
            throw new UnsupportedOperationException("The tree is empty.");
        }
        boolean containsAll = true;
        for(E element : set){
            if(!this.contains(element)){
                containsAll = false;
            }
        }
        return containsAll;
    }

    /**
     * A new element is added to the set.
     *
     * @param element - Set element.
     */
    @Override
    public void add(E element) {
        if (element == null) {
            throw new IllegalArgumentException("Element is null in add(E element)");
        }

        root = addRecursive(element, root);
    }

    /**
     * The elements of both sets are added to the existing set, if both sets have the same element, it is not placed.
     *
     * @param set additional set
     */
    @Override
    public void addAll(Set<E> set) {
        if(set == null) {
            throw new UnsupportedOperationException("The given tree is empty.");
        }
        if(root == null) {
            throw new UnsupportedOperationException("The tree is empty.");
        }

        for(E element : set){
            if(!this.contains(element)){
                this.add(element);
            }
        }
    }

    private BstNode<E> addRecursive(E element, BstNode<E> node) {
        if (node == null) {
            size++;
            return new BstNode<>(element);
        }

        int cmp = c.compare(element, node.element);

        if (cmp < 0) {
            node.left = addRecursive(element, node.left);
        } else if (cmp > 0) {
            node.right = addRecursive(element, node.right);
        }

        return node;
    }

    /**
     * Removes an item from a set.
     *
     * @param element - Set element.
     */
    @Override
    public void remove(E element) {
        if (element == null) {
            throw new IllegalArgumentException("Element is null in remove(E element)");
        }
        root = removeRecursive(element, root);
    }

    /**
     * Only the elements that are in the set are left in the set.
     *
     * @param set aibė
     */
    @Override
    public void retainAll(Set<E> set) {
        if(set == null) {
            throw new UnsupportedOperationException("The given tree is empty.");
        }
        if(root == null) {
            throw new UnsupportedOperationException("The tree is empty.");
        }

        for(Object element : this.toArray()){
            if(!set.contains((E) element)){
                this.remove((E) element);
            }
        }
    }

    private BstNode<E> get(BstNode<E> node, boolean findMax) {
        BstNode<E> parent = null;
        while (node != null) {
            parent = node;
            node = (findMax) ? node.right : node.left;
        }
        return parent;
    }

    BstNode<E> getMin(BstNode<E> node) {
        return get(node, false);
    }

    BstNode<E> getMax(BstNode<E> node) {
        return get(node, true);
    }

    BstNode<E> removeMax(BstNode<E> node) {
        if (node == null) {
            return null;
        } else if (node.right != null) {
            node.right = removeMax(node.right);
            return node;
        } else {
            return node.left;
        }
    }

    private BstNode<E> removeRecursive(E element, BstNode<E> node) {
        if (node == null) {
            return node;
        }
        // searching for a node in a tree that we will delete
        int cmp = c.compare(element, node.element);

        if (cmp < 0) {
            node.left = removeRecursive(element, node.left);
        } else if (cmp > 0) {
            node.right = removeRecursive(element, node.right);
        } else if (node.left != null && node.right != null) {

            // node has two children. Searching for the largest node in the left sub-tree
            BstNode<E> nodeMax = getMax(node.left);
            // the largest key element data is moved to the deletable element node. The node is updated.
            node.element = nodeMax.element;
            // find and remove the max key element node
            node.left = removeMax(node.left);
            size--;
            // other
        } else {
            node = (node.left != null) ? node.left : node.right;
            size--;
        }

        return node;
    }

    BstNode<E> get(E element) {
        if (element == null) {
            throw new IllegalArgumentException("Element is null in get(E element)");
        }

        BstNode<E> node = root;
        while (node != null) {
            int cmp = c.compare(element, node.element);

            if (cmp < 0) {
                node = node.left;
            } else if (cmp > 0) {
                node = node.right;
            } else {
                return node;
            }
        }

        return null;
    }

    /**
     * Returns an array of set elements.
     *
     * @return Returns an array of set elements.
     */
    @Override
    public Object[] toArray() {
        int i = 0;
        Object[] array = new Object[size];
        for (Object o : this) {
            array[i++] = o;
        }
        return array;
    }

    /**
     * Output of the set elements to the String string in Inorder order. Sets
     * items are output in ascending order by key.
     *
     * @return element string
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (E element : this) {
            sb.append(element.toString()).append(System.lineSeparator());
        }
        return sb.toString();
    }

    /**
     * Representation of a tree by symbols, see: unicode.org/charts/PDF/U2500.pdf
     * These are 4 possible terminal characters at the end of the tree branch
     */
    private static final String[] term = {"\u2500", "\u2534", "\u252C", "\u253C"};
    private static final String rightEdge = "\u250C";
    private static final String leftEdge = "\u2514";
    private static final String endEdge = "\u25CF";
    private static final String vertical = "\u2502  ";
    private String horizontal;

    /* An additional method of deriving a set of elements into a single String string.
     * The string is formed by pushing the elements away from the edge,
     * depending on the level of the element in the tree. Can be used for printing to a screen or file
     * by studying the operation of tree algorithms.
     *
     * @author E. Karčiauskas
     */
    @Override
    public String toVisualizedString(String dataCodeDelimiter) {
        horizontal = term[0] + term[0];
        return root == null ? ">" + horizontal
                : toTreeDraw(root, ">", "", dataCodeDelimiter);
    }

    private String toTreeDraw(BstNode<E> node, String edge, String indent, String dataCodeDelimiter) {
        if (node == null) {
            return "";
        }
        String step = (edge.equals(leftEdge)) ? vertical : "   ";
        StringBuilder sb = new StringBuilder();
        sb.append(toTreeDraw(node.right, rightEdge, indent + step, dataCodeDelimiter));
        int t = (node.right != null) ? 1 : 0;
        t = (node.left != null) ? t + 2 : t;
        sb.append(indent).append(edge).append(horizontal).append(term[t]).append(endEdge).append(
                split(node.element.toString(), dataCodeDelimiter)).append(System.lineSeparator());
        step = (edge.equals(rightEdge)) ? vertical : "   ";
        sb.append(toTreeDraw(node.left, leftEdge, indent + step, dataCodeDelimiter));
        return sb.toString();
    }

    private String split(String s, String dataCodeDelimiter) {
        int k = s.indexOf(dataCodeDelimiter);
        if (k <= 0) {
            return s;
        }
        return s.substring(0, k);
    }

    /**
     * Creates and returns a copy of the set.
     *
     * @return Copy of the set.
     * @throws java.lang.CloneNotSupportedException
     */
    @Override
    public Object clone() throws CloneNotSupportedException {
        BstSet<E> cl = (BstSet<E>) super.clone();
        if (root == null) {
            return cl;
        }
        cl.root = cloneRecursive(root);
        cl.size = this.size;
        return cl;
    }

    private BstNode<E> cloneRecursive(BstNode<E> node) {
        if (node == null) {
            return null;
        }

        BstNode<E> clone = new BstNode<>(node.element);
        clone.left = cloneRecursive(node.left);
        clone.right = cloneRecursive(node.right);
        return clone;
    }

    /**
     * Returns a subset of the set to the element.
     *
     * @param element - Set element.
     * @return Returns a subset of the set to the element.
     */
    @Override
    public Set<E> headSet(E element) {
        if(element == null || root == null){
            return null;
        }
        Set<E> headset = new BstSet<>();
        Iterator<E> ite = this.iterator();
        BstNode<E> node = get(element);
        E el;
        while(!(el = ite.next()).equals(node.element)) {
            headset.add(el);
        }
        return headset;
    }

    /**
     * Returns a subset of the set from element element1 to element2.
     *
     * @param element1 - the original element of a subset of the set.
     * @param element2 - the end element of a subset of the set.
     * @return Returns a subset of the set from element element1 to element2.
     */
    @Override
    public Set<E> subSet(E element1, E element2) {
        Set<E> subset = new BstSet<>();
        Iterator<E> ite = this.iterator();
        E help = null;
        while(help != element1)
            help = ite.next();
        BstNode<E> node = get(element2);
        E data = help;
        subset.add(data);
        while(!data.equals(node.element)) {
            data = ite.next();
            subset.add(data);
        }
        return subset;
    }

    /**
     * Returns a subset of the set to the element.
     *
     * @param element - Set element.
     * @return Returns a subset of the set from the element.
     */
    @Override
    public Set<E> tailSet(E element) {
        if(element == null || root == null){
            return null;
        }

        Set<E> tailset = new BstSet<>();
        Iterator<E> ite = this.iterator();
        E data = null;
        while (data != element) {
            data = ite.next();
        }
        tailset.add(data);
        while(ite.hasNext()) {
            tailset.add(ite.next());
        }
        return tailset;
    }

    /**
     * Returns the direct iterator.
     *
     * @return Returns the direct iterator.
     */
    @Override
    public Iterator<E> iterator() {
        return new IteratorBst(true);
    }

    /**
     * Returns the reverse iterator.
     *
     * @return Returns the reverse iterator.
     */
    @Override
    public Iterator<E> descendingIterator() {
        return new IteratorBst(false);
    }

    /**
     * Internal object collection iterator class. Iterators: ascending and descending.
     * The collection is iterated by visiting each item once in in order.
     * All visited items are stored on the stack. The stack is used from the java.util package,
     * but you can create your own.

     */
    private class IteratorBst implements Iterator<E> {

        private Stack<BstNode<E>> stack = new Stack<>();
        // Specifies the direction of the iteration collection, true - ascending, false - descending
        private boolean ascending;
        // Specifies the parent of the current tree element. Required for disposal.
        private BstNode<E> parent = root;

        IteratorBst(boolean ascendingOrder) {
            this.ascending = ascendingOrder;
            this.toStack(root);
        }

        @Override
        public boolean hasNext() {
            return !stack.empty();
        }

        @Override
        public E next() {
            if (!stack.empty()) {
                // Returns the last item placed on the stack
                BstNode<E> n = stack.pop();
                // The top of the father is remembered. Need to remove () method
                parent = (!stack.empty()) ? stack.peek() : root;
                BstNode<E> node = (ascending) ? n.right : n.left;
                // The right-hand sub-tree n looks for the minimum element
                // and all items in the search path are placed in the stack
                toStack(node);
                return n.element;
            } else { // If the stack is empty
                return null;
            }
        }

        @Override
        public void remove() {
            if(stack.isEmpty()) return;
            E el = stack.pop().element;
            root = removeRecursive(el, root);
            size--;
        }

        private void toStack(BstNode<E> n) {
            while (n != null) {
                stack.push(n);
                n = (ascending) ? n.left : n.right;
            }
        }
    }

    /**
     * Internal class of the collection node
     *
     * @param <N> node element data type
     */
    protected class BstNode<N> {

        // Element
        protected N element;
        // Pointer to the left subtree
        protected BstNode<N> left;
        // Pointer to the right subtree
        protected BstNode<N> right;

        protected BstNode() {
        }

        protected BstNode(N element) {
            this.element = element;
            this.left = null;
            this.right = null;
        }
    }
}
