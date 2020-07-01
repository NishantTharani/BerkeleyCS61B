public class Palindrome {
    /**
     * Returns a Deque where the elements are characters appearing in the same
     * order as in the string 'word'.
     * @param word - the word used to create the Deque
     * @return deq - deque which contains characters of 'word' in the same order
     */
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> deq = new LinkedListDeque<Character>();

        for (int i = 0; i < word.length(); i++) {
            deq.addLast(word.charAt(i));
        }

        return deq;
    }

    /**
     * Tests if the provided word is a palindrome.
     * @param word - the word to check for palindrome-ness
     * @return bool - true if the word is a palindrome, false otherwise
     */
    public boolean isPalindrome(String word) {
        Deque<Character> deq = wordToDeque(word);
        Character first, last;
        while (deq.size() > 1) {
            first = deq.removeFirst();
            last = deq.removeLast();
            if (first != last) {
                return false;
            }
        }
        return true;
    }

    /**
     * Tests if the provided word is a palindrome with character equality
     * judged by cc.
     * @param word - word to test palindrome-ness of
     * @param cc - object used to check if two characters are equal
     * @return bool - true if the word is a palindrome, else false
     */
    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque<Character> deq = wordToDeque(word);
        Character first, last;
        while (deq.size() > 1) {
            first = deq.removeFirst();
            last = deq.removeLast();
            if (!cc.equalChars(first, last)) {
                return false;
            }
        }
        return true;
    }
}
