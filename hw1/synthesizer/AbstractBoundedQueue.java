package synthesizer;

/**
 * An abstract implementation of the BoundedQueue interface.
 * One step closer to reality.
 * @author Nish
 */
public abstract class AbstractBoundedQueue<T> implements BoundedQueue<T> {
    /**
     * Number of elements currently in the bounded queue.
     */
    protected int fillCount;

    /**
     * Capacity of the bounded queue.
     */
    protected int capacity;

    /**
     * Returns the capacity of the queue.
     * @return int: the capacity of the queue
     */
    public int capacity() {
        return capacity;
    }

    /**
     * Returns the number of elements in the queue.
     * @return int: number of elements in the queue
     */
    public int fillCount() {
        return fillCount;
    }
}
