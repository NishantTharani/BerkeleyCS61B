package synthesizer;
import java.util.Iterator;

/**
 * A generic interface for a queue of fixed size.
 * @author Nish
 */
public interface BoundedQueue<T> extends Iterable<T> {
    /**
     * Gets the maximum number of elements that the queue can hold.
     * @return int: capacity of the queue.
     */
    int capacity();

    /**
     * Gets the number of elements currently in the queue.
     * @return num elements in the queue.
     */
    int fillCount();

    /**
     * Adds 'x' to the back of the queue.
     * @param x - element to add to the back of the queue.
     */
    void enqueue(T x);

    /**
     * Returns the element at the front of the queue, and removes it.
     */
    T dequeue();

    /**
     * Returns the element at the front of the queue, without removing it.
     */
    T peek();

    /**
     * Returns the iterator for this data structure.
     * @return Iterator<T> for this data structure
     */
    Iterator<T> iterator();

    /**
     * Returns true if the queue is empty, else false.
     */
    default boolean isEmpty() {
        return fillCount() == 0;
    }

    /**
     * Returns true if the queue is full, else false.
     */
    default boolean isFull() {
        return fillCount() == capacity();
    }
}
