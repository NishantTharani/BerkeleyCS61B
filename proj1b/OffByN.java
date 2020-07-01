/**
 * A class to test whether two provided characters differ by exactly N integers.
 */
public class OffByN implements CharacterComparator {
    private int offBy;

    /**
     * Creates a new comparator that will check whether
     * two characters differ by exactly N.
     * @param N
     */
    public OffByN(int N) {
        offBy = N;
    }

    /**
     * Checks whether two characters differ by offBy integers.
     * offBy is set when this class is constructed.
     * @param x - first character to test
     * @param y - second character to test
     * @return bool - true if the two characters differ by offBy, else false.
     */
    public boolean equalChars(char x, char y) {
        return Math.abs(x - y) == offBy;
    }
}
