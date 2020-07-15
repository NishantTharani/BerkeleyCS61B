package synthesizer;

/**
 * Uses an ArrayRingBuffer to apply the Karplus-Strong algorithm to simulate the
 * playing of a guitar string.
 * @author nish
 */
public class GuitarString {

    /**
     * Constant SR value.
     */
    private static final int SR = 44100;

    /**
     * Constant decay factor.
     */
    private static final double DECAY = .996;


    /**
     *  Buffer for storing sound data.
     */
    private BoundedQueue<Double> buffer;

    /**
     * Create a guitar string of the given frequency.
     * @param frequency - frequency of the string to create
     */
    public GuitarString(double frequency) {
        int capacity = (int) Math.round(SR / frequency);
        buffer = new ArrayRingBuffer<Double>(capacity);
        while (!buffer.isFull()) {
            buffer.enqueue(0.);
        }
    }


    /**
     *  Pluck the guitar string by replacing the buffer with white noise.
     */
    public void pluck() {
        while (!buffer.isEmpty()) {
            buffer.dequeue();
        }

        while (!buffer.isFull()) {
            double r = Math.random() - 0.5;
            buffer.enqueue(r);
        }
    }

    /**
     * Advance the simulation one time step by performing one iteration of
     * the Karplus-Strong algorithm.
     */
    public void tic() {
        double first = buffer.dequeue();
        double second = buffer.peek();
        double result = DECAY * 0.5 * (first + second);
        buffer.enqueue(result);
    }

    /**
     * Return the double at the front of the buffer.
     * @return the double at the front of the buffer
     */
    public double sample() {
        return buffer.peek();
    }
}
