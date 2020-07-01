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
    }

    @Test
    public void testInequalChars() {
        assertFalse(offByOne.equalChars('a', 'm'));
        assertFalse(offByOne.equalChars('A', 'A'));
        assertFalse(offByOne.equalChars(';', '!'));
    }
}
