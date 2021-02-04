package es.datastructur.synthesizer;
import java.util.Iterator;

public class ArrayRingBuffer<T> implements BoundedQueue<T>, Iterable<T> {
    /* Index for the next dequeue or peek. */
    private int first;
    /* Index for the next enqueue. */
    private int last;
    /* Variable for the fillCount. */
    private int fillCount;
    /* Array for storing the buffer data. */
    private T[] rb;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        first = 0;
        last = 0;
        fillCount = 0;
        rb = (T[]) new Object[capacity];
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow").
     */
    @Override
    public void enqueue(T x) {
        if (isFull()) {
            throw new RuntimeException("Ring buffer overflow");
        } else {
            rb[first] = x;
            if (first == capacity() - 1) {
                first = 0;
            } else {
                first += 1;
            }
            fillCount += 1;
        }
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    @Override
    public T dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("Ring buffer underflow");
        } else {
            T temp = rb[last];
            if (last == capacity() - 1) {
                last = 0;
            } else {
                last += 1;
            }
            fillCount -= 1;
            return temp;
        }
    }

    /**
     * Return oldest item, but don't remove it. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    @Override
    public T peek() {
        if (isEmpty()) {
            throw new RuntimeException("Ring buffer underflow");
        } else {
            return rb[last];
        }
    }

    @Override
    public int capacity() {
        return rb.length;
    }

    @Override
    public int fillCount() {
        return fillCount;
    }

    @Override
    public Iterator<T> iterator() {
        return new MyIterator();
    }

    private class MyIterator implements Iterator<T> {
        private int wispos;

        MyIterator() {
            wispos = last;
        }

        @Override
        public boolean hasNext() {
            return wispos != first;
        }

        @Override
        public T next() {
            T temp = rb[wispos];
            if (wispos == capacity() - 1) {
                wispos = 0;
            } else {
                wispos += 1;
            }
            return temp;
        }

    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (o.getClass() != this.getClass()) {
            return false;
        }
        ArrayRingBuffer<T> arrayRingBuffer = (ArrayRingBuffer<T>) o;
        if (first != arrayRingBuffer.first) {
            return false;
        }
        if (last != arrayRingBuffer.first) {
            return false;
        }
        if (capacity() != arrayRingBuffer.capacity()) {
            return false;
        }
        if (fillCount != arrayRingBuffer.fillCount) {
            return false;
        }
        int temp = last;
        for (int i = 0; i < fillCount; i += 1) {
            if (rb[temp] != arrayRingBuffer.rb[temp]) {
                return false;
            }
            if (temp == capacity() - 1) {
                temp = 0;
            } else {
                temp += 1;
            }
        }
        return true;
    }
}
