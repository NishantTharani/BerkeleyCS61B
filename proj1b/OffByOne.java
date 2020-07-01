public class OffByOne implements CharacterComparator {
    /**
     * Tests if two characters are different by exactly one.
     * @param x - first character to test
     * @param y - second character to test
     * @return bool - true if characters are different by one, else false
     */
    public boolean equalChars(char x, char y) {
        return Math.abs(x - y) == 1;
    }
}
