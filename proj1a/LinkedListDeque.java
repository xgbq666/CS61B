public class LinkedListDeque<T> {
    private int size;
    private LinkedNode sentinel;

    public class LinkedNode {
        private T value;
        private LinkedNode prev;
        private LinkedNode next;
    }

    public LinkedListDeque() {
        size = 0;
        sentinel = new LinkedNode();
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
    }

    public void addFirst(T item) {
        if (size == 0) {
            sentinel.next = new LinkedNode();
            sentinel.next.prev = sentinel;
            sentinel.next.value = item;
            sentinel.next.next = sentinel;
            sentinel.prev = sentinel.next;
        } else {
            sentinel.next.prev = new LinkedNode();
            sentinel.next.prev.prev = sentinel;
            sentinel.next.prev.value = item;
            sentinel.next.prev.next = sentinel.next;
            sentinel.next = sentinel.next.prev;
        }
        size += 1;
    }

    public void addLast(T item) {
        if (size == 0) {
            sentinel.next = new LinkedNode();
            sentinel.next.prev = sentinel;
            sentinel.next.value = item;
            sentinel.next.next = sentinel;
            sentinel.prev = sentinel.next;
        } else {
            sentinel.prev.next = new LinkedNode();
            sentinel.prev.next.prev = sentinel.prev;
            sentinel.prev.next.value = item;
            sentinel.prev.next.next = sentinel;
            sentinel.prev = sentinel.prev.next;
        }
        size += 1;
    }

    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        LinkedNode temp = sentinel.next;
        for (int i = 0; i < size; i += 1) {
            System.out.println(temp.value);
            temp = temp.next;
        }
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        T value = sentinel.next.value;
        sentinel.next.next.prev = sentinel;
        sentinel.next = sentinel.next.next;
        size -= 1;
        return value;
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }
        T value = sentinel.prev.value;
        sentinel.prev.prev.next = sentinel;
        sentinel.prev = sentinel.prev.prev;
        size -= 1;
        return value;
    }

    public T get(int index) {
        if (index >= size) {
            return null;
        }
        LinkedNode temp = sentinel;
        for (int i = 0; i <= index; i += 1) {
            temp = temp.next;
        }
        return temp.value;
    }

    private T getRecursiveHelper(int index, LinkedNode node) {
        if (index == 0) {
            return node.value;
        }
        return getRecursiveHelper(index - 1, node.next);
    }

    public T getRecursive(int index) {
        if (index >= size) {
            return null;
        }
        return getRecursiveHelper(index, sentinel.next);
    }
}
