package synthesizer;
import java.util.Iterator;

/**
 * Concrete implementation of a bounded queue, using an array that
 * wraps around if elements are enqueued/dequeued from either end.
 * @param <T> - generic type of the data structure
 * @author nish
 */
public class ArrayRingBuffer<T> extends AbstractBoundedQueue<T>  {
    /** Index for the next dequeue or peek. */
    private int first;

    /** Index for the next enqueue. */
    private int last;

    /** Array for storing the buffer data. */
    private T[] rb;

    /**
     * This private class implements the Iterator interface, allowing us to
     * iterate over elements in the queue.
     */
    private class ArrayRingBufferIterator implements Iterator<T> {

        /**
         * Checks if there are any elements left to iterate over.
         * @returns bool: true if there are elements left to iterate over,
         *                  else false
         */
        public boolean hasNext() {
            return !isEmpty();
        }

        /**
         * Iterates over the next element by dequeueing it and returning it.
         * @returns T: next element
         */
        public T next() {
            return dequeue();
        }
    }

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     * @param capacity - maximum size of the new ArrayRingBuffer
     */
    public ArrayRingBuffer(int capacity) {
        rb = (T[]) new Object[capacity];
        first = 0;
        last = 0;
        fillCount = 0;
        this.capacity = capacity;
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow"). Exceptions
     * covered Monday.
     * @param x - item to be enqueued
     */
    public void enqueue(T x) {
        if (isFull()) {
            throw new RuntimeException("Ring Buffer Overflow");
        }

        rb[last] = x;

        last += 1;
        if (last == capacity) {
            last = 0;
        }

        fillCount += 1;
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow"). Exceptions
     * covered Monday.
     * @return T - olded item in the ring buffer
     */
    public T dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("Ring Buffer Underflow");
        }

        T item = rb[first];

        first += 1;
        if (first == capacity) {
            first = 0;
        }

        fillCount -= 1;
        return item;
    }

    /**
     * Return oldest item, but don't remove it.
     */
    public T peek() {
        return rb[first];
    }

    /**
     * Returns the iterator for this queue.
     * @return the iterator object for this queue
     */
    @Override
    public Iterator<T> iterator() {
        return new ArrayRingBufferIterator();
    }
}
