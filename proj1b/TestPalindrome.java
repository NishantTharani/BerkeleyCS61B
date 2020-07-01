import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testEmptyIsPalindrome() {
        assertTrue(palindrome.isPalindrome(""));
    }

    @Test
    public void testSingleIsPalindrome() {
        assertTrue(palindrome.isPalindrome("a"));
    }

    @Test
    public void testTrueIsPalindrome() {
        assertTrue(palindrome.isPalindrome("racecar"));
        assertTrue(palindrome.isPalindrome("noon"));
    }

    @Test
    public void testFalseIsPalindrome() {
        assertFalse(palindrome.isPalindrome("rancor"));
        assertFalse(palindrome.isPalindrome("Noon"));
        assertFalse(palindrome.isPalindrome("abracadabra"));
    }

    @Test
    public void testTrueCCPalindrome() {
        CharacterComparator cc = new OffByOne();
        assertTrue(palindrome.isPalindrome("", cc));
        assertTrue(palindrome.isPalindrome("a", cc));
        assertTrue(palindrome.isPalindrome("mopn", cc));
        assertTrue(palindrome.isPalindrome("sbdecar", cc));
    }

    @Test
    public void testFalseCCPalindrome() {
        CharacterComparator cc = new OffByOne();
        assertFalse(palindrome.isPalindrome("aa", cc));
        assertFalse(palindrome.isPalindrome("noon", cc));
        assertFalse(palindrome.isPalindrome("sbfecar", cc));
    }
}
