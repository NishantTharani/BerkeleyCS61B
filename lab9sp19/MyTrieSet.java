import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyTrieSet implements TrieSet61B {

    private Node root;


    private class Node {
        Map<Character, Node> next;
        boolean isFinal;

        private Node(boolean isFinal) {
            this.isFinal = isFinal;
            next = new HashMap<>();
        }
    }

    public MyTrieSet() {
        clear();
    }


    @Override
    public void clear() {
        root = new Node(false);
    }

    @Override
    public boolean contains(String key) {
        Node curr = root;
        for (Character c : key.toCharArray()) {
            if (!curr.next.containsKey(c)) {
                return false;
            }
            curr = curr.next.get(c);
        }

        return curr.isFinal;
    }

    @Override
    public void add(String key) {
        if (key == null || key.length() < 1) {
            return;
        }

        Node curr = root;

        for (Character c : key.toCharArray()) {
            if (!curr.next.containsKey(c)) {
                curr.next.put(c, new Node(false));
            }
            curr = curr.next.get(c);
        }

        curr.isFinal = true;
    }

    @Override
    public List<String> keysWithPrefix(String prefix) {
        List<String> keys = new ArrayList<>();
        Node curr = root;

        for (Character c : prefix.toCharArray()) {
            if (!curr.next.containsKey(c)) {
                return keys;
            }
            curr = curr.next.get(c);
        }

        getKeysFrom(curr, keys, prefix);

        return keys;
    }

    private void getKeysFrom(Node start, List<String> keys, String base) {
        if (start.isFinal)
            keys.add(base);
        for (Character c : start.next.keySet()) {
            getKeysFrom(start.next.get(c), keys, base + c);
        }
    }

    @Override
    public String longestPrefixOf(String key) {
        StringBuilder longest = new StringBuilder();
        Node curr = root;
        for (Character c : key.toCharArray()) {
            if (curr.next.containsKey(c)) {
                longest.append(c);
                curr = curr.next.get(c);
            } else {
                break;
            }
        }
        return longest.toString();
    }
}
