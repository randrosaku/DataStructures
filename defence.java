public void add(int index, T element) {
        if(index < 0 || index > count) {
            throw new IndexOutOfBoundsException();
        }

        Node<T> newItem = new Node<T>(element, null);
        Node current = head;
        if(index == 0)
        {
            Node temp = head;
            head = newItem;
            head.next = temp;
        } else {
            if(current != null) {
                for(int i = 1; i < index; i++) {
                    current = current.getNext();
                }
            }
            newItem.setNext(current.getNext());
            current.setNext(newItem);
        }
        count++;
    }

public boolean addAll(LinkedList<? extends T> c) {
            boolean changed = false;

            for(T item : c) {
                Node<T> newNode = new Node<>(item, null);
                if(head != null) {
                    tail.next = newNode;
                    tail = tail.next;
                } else {
                    head = newNode;
                    tail = head;
                }
                count++;
                changed = true;
            }
            return changed;
        }

public boolean addAll(int index, LinkedList<? extends T> c) {
    if(index < 0 || index > count-1) {
        throw new IndexOutOfBoundsException("Index out of bounds.");
    }
    if(c.count == 0) {
        return false;
    }
    LinkedList<T> list = (LinkedList<T>) c;
    int i = 0;
    var previous = head;
    var last = head;

    for(Node<T> d1 = head; d1 != null; d1 = d1.next) {
        if(i < index) {
            previous = d1;
        }
        if(i == index) {
            last = d1;
        }
        i++;
    }
    if(index == 0) {
        last = head;
        head = list.head;
    } else {
    previous.next = list.head;
    }
    list.tail.next = last;
    count += c.count;

    return true;
}

public boolean contains(Object o) {
        boolean contains = false;
        for(Node<T> d1 = head; d1 != null; d1 = d1.next) {
            if(d1.data.equals(o)) {
                contains = true;
                break;
            } else {
                contains = false;
            }
        }
        return contains;
    }

boolean containsAll(LinkedList<?> c) {
  for(Object item : c) {
    if(!contains(item)) { return false;}
  } else { return true; }
}

public boolean equals(Object o) {
        if(o == null){
            return false;
        } else if(getClass() != o.getClass()) {
            return false;
        } else {
            LinkedList<T> otherList = (LinkedList<T>)o;
            if(count != otherList.count) {
                return false;
            } else {
                Node<T> position = head;
                Node<T> otherPosition = otherList.head;
                while(position != null) {
                    if(!(position.data.equals(otherPosition.data))) {
                        return false;
                    } else {
                        position = position.next;
                        otherPosition = otherPosition.next;
                    }
                }
                return true;
            }
        }
    }

public int indexOf(Object o){
        int index = -1;
        int i = 0;
        for(Node<T> d1 = head; d1 != null; d1 = d1.next) {
            if(d1.data.equals(o)) {
                index = i;
                break;
            }
            i++;
        }
        return index;
    }

public int lastIndexOf(Object o) {
    int index = -1;
    int i = 0;
    for(Node<T> d1 = head; d1 != null; d1 = d1.next) {
        if(d1.data.equals(o)) {
            index = i;
        }
        i++;
    }
    return index;
}

public T remove(int index) {
        if(head == null) return null;
        if(index < 0 || index > count-1) {
            throw new IndexOutOfBoundsException("Index out of bounds.");
        }

        boolean remove = false;
        T data = null;
        var previous = head;
        int i = 0, j = 0;

        if(index == 0)
        {
            data = head.data;
            head = head.next;
            count--;
            return data;
        }

        for(Node<T> d1 = head; d1 != null; d1 = d1.next) {
            if(i < index) {
                previous = d1;
            }
            i++;
        }
        for(Node<T> d1 = head; d1 != null; d1 = d1.next){
            if (j == index)
            {
                data = d1.data;
                remove = true;
                break;
            }
            j++;
        }
        if(remove) {
            previous.next = previous.next.next;
            count--;
        }

        return data;
    }

public boolean remove(Object o) {
        boolean remove = false;
        boolean contains = false;
        if(head == null) return remove;

        for(Node<T> d1 = head; d1 != null; d1 = d1.next) {
            if(d1.data.equals(o)) {
                contains = true;
                break;
            }
        }
        if(contains) {
            var current = head;
            if(current.data.equals(o)) {
                remove = true;
                head = current.next;
                tail = head;
                count--;
            } else {
                for(Node<T> d1 = head; d1 != null; d1 = d1.next){
                    if (d1.next != null && o.equals(d1.next.data))
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
        }

        return remove;
    }

public boolean removeAll(LinkedList<?> c){
    LinkedList<T> list = (LinkedList<T>) c;
    var current = head;
    boolean remove = false;

    if(head == null) {
        return remove;
    }

    for(Node<T> d1 = head; d1 != null; d1 = d1.next) {
        for(Node<T> d2 = list.head; d2!=null; d2 = d2.next) {
            if(head!= null && head.data.equals(d2.data)) {
                head = head.next;
                count--;
                remove = true;
            } else if(d1.next != null && d1.data.equals(d2.data)) {
                d1 = d1.next;
                count--;
                remove = true;
            } else if (d1.next == null && d1.data.equals(d2.data)) {
                tail = null;
                count--;
                remove = true;
            } else if(d1.next != null && d1.next.data.equals(d2.data)) {
                d1.next = d1.next.next;
                count--;
                remove = true;
            }
        }
    }
    return remove;
}

boolean retainAll(LinkedList<?> c) {
  if(c == null) System.out.print(throw new NullPointerException());
  Iterator iter = iterator();

  boolean found = false;
  while(iter.hasNext()) {
    if(!c.contains(iter.next)) {
      iter.remove();
      found = true;
    }
  }
  return found;
}

public T set(int index, T element) {
        T data = null;
        int i = 0, j = 0;

        if(index < 0 || index > count-1) {
            throw new IndexOutOfBoundsException("Index out of bounds.");
        }
        if(head == null) return data;

        for(Node<T> d1 = head; d1 != null; d1 = d1.next) {
            if(j == index) {
                data = d1.data;
                d1.data = element;
            }
            j++;
        }
        return data;
    }

public List<T> subList(int fromIndex, int toIndex) {
    List<T> newList = new LinkedList<T>();
    int i = 0;

    if(fromIndex > count-1 || fromIndex < 0 || toIndex > count-1 || toIndex < 0) {
        throw new IndexOutOfBoundsException("Index out of bounds.");
    }
    if(head == null) return newList;

    for(Node<T> d1 = head; d1 != null; d1 = d1.next) {
        if(i >= fromIndex && i < toIndex) {
            newList.add(d1.data);
        }
        i++;
    }
    return newList;
}

public void addFirst(T e){
        Node<T> newItem = new Node<>(e, null);
        Node temp = head;
        head = newItem;
        head.next = temp;
        count++;
    }

public void addLast(T e) {
        if(head != null) {
            tail.next = new Node<T>(e, null);
            tail = tail.next;
        } else {
            head = new Node<T>(e, null);
            tail = head;
        }
        count++;
    }

public T removeFirst(){
        if(head == null) return null;

        var data = head.data;
        head = head.next;
        count--;
        return data;
    }

public boolean removeFirstOccurence(Object o) {
        boolean remove = false;
        if(head == null) return remove;

        var current = head;
        if(current.data.equals(o)) {
            remove = true;
            head = current.next;
            tail = head;
            count--;
        } else {
            for(Node<T> d1 = head; d1 != null; d1 = d1.next){
                if (d1.next != null && o.equals(d1.next.data))
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

public T removeLast(){
    if(tail == null) return null;

    var data = tail.data;
    tail = null;
    count--;
    return data;
}

public boolean removeLastOccurence(Object o) {
        boolean remove = false;
        if(head == null) return remove;

        var current = head;
        if(current.data.equals(o)) {
            remove = true;
            head = current.next;
            tail = head;
            count--;
        } else {
            for(Node<T> d1 = head; d1 != null; d1 = d1.next){
                if (d1.next != null && o.equals(d1.next.data))
                {
                    current = d1;
                    remove = true;
                }
            }
            if(remove) {
                current.next = current.next.next;
                count--;
            }
        }
        return remove;
    }

public void removeRange(int fromIndex, int toIndex) {
    if(fromIndex > count-1 || fromIndex < 0 || toIndex > count-1 || toIndex < 0) {
        throw new IndexOutOfBoundsException("Index out of bounds.");
    }
    if(head == null) return;

    int i = 0;
    int removeCount = toIndex-fromIndex;
    var previous = head;
    var last = head;
    var temp = head;

    if(removeCount == count-1) {
        for(Node<T> d1 = head; d1 != null; d1 = d1.next) {
            head = temp.next;
            count--;
        }
        return;
    }

    for(Node<T> d1 = head; d1 != null; d1 = d1.next) {
        if(i < fromIndex) {
            previous = d1;
        }
        if(i < toIndex) {
            last = d1;
        }
        i++;
    }

    if(fromIndex == 0) {
        head = last.next;
    } else {
    previous.next = last.next;
    }

    count -= removeCount;
}
