public class ArrayDeque<T> {
    private T[] arraylist;
    private int size;
    private int nextfirst;
    private int nextlast;
    private int hold;

    public ArrayDeque() {
        arraylist = (T[]) new Object[8];
        size = 0;
        nextfirst = 7;
        nextlast = 0;
        hold = 8;
    }

    private void resize(int newsize) {
        T[] newArraylist = (T[]) new Object[newsize];
        for (int i = 0; i < size; i += 1) {
            if (nextfirst + i + 1 >= hold) {
                newArraylist[i] = arraylist[nextfirst + i + 1 - hold];
            } else {
                newArraylist[i] = arraylist[nextfirst + i + 1];
            }
        }
        hold = newsize;
        nextfirst = hold - 1;
        nextlast = size;
        arraylist = newArraylist;
    }

    public void addFirst(T item) {
        if (size == hold) {
            resize(4 * size);
        }

        arraylist[nextfirst] = item;
        nextfirst -= 1;
        if (nextfirst < 0) {
            nextfirst = hold - 1;
        }
        size += 1;
    }

    public void addLast(T item) {
        if (size == hold) {
            resize(4 * size);
        }

        arraylist[nextlast] = item;
        nextlast += 1;
        if (nextlast >= hold) {
            nextlast = 0;
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
        for (int i = nextfirst + 1; i != nextlast; i += 1) {
            if (i == hold) {
                i = 0;
            }
            System.out.print(arraylist[i] + " ");
        }
        System.out.println("");
    }

    public T removeFirst() {
        nextfirst += 1;
        if (nextfirst == hold) {
            nextfirst = 0;
        }
        size -= 1;
        T temp = arraylist[nextfirst];
        if ((double) size / (double) hold <= 0.25 && hold >= 16) {
            resize(hold / 2);
        }
        return temp;
    }

    public T removeLast() {
        nextlast -= 1;
        if (nextlast == -1) {
            nextlast = hold - 1;
        }
        size -= 1;
        T temp = arraylist[nextlast];
        if ((double) size / (double) hold <= 0.25 && hold >= 16) {
            resize(hold / 2);
        }
        return temp;
    }

    public T get(int index) {
        if (index >= hold) {
            return null;
        }
        return arraylist[index];
    }

}
