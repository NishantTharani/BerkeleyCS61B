import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByOne {
    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offByOne = new OffByOne();

    // Your tests go here.
    @Test
    public void testEqualChars() {
        assertTrue(offByOne.equalChars('a', 'b'));
        assertTrue(offByOne.equalChars('T', 'U'));
        assertTrue(offByOne.equalChars('&', '%'));
        assertTrue(offByOne.equalChars('Z', '['));
        assertTrue(offByOne.equalChars('`', 'a'));
        assertTrue(offByOne.equalChars('2', '1'));
        assertTrue(offByOne.equalChars('B', 'A'));
    }

    @Test
    public void testInequalChars() {
        assertFalse(offByOne.equalChars('a', 'm'));
        assertFalse(offByOne.equalChars('A', 'A'));
        assertFalse(offByOne.equalChars('A', 'C'));
        assertFalse(offByOne.equalChars('a', 'A'));
        assertFalse(offByOne.equalChars(';', '!'));
        assertFalse(offByOne.equalChars('M', 'A'));
        assertFalse((offByOne.equalChars('c', 'a')));
    }
}
