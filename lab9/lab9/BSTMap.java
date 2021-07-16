package lab9;

import javax.naming.OperationNotSupportedException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Stack;

/**
 * Implementation of interface Map61B with BST as core data structure.
 *
 * @author Nishant Tharani
 */

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {

    private class Node {
        /* (K, V) pair stored in this Node. */
        private K key;
        private V value;

        /* Children of this Node. */
        private Node left;
        private Node right;

        private Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    private Node root;  /* Root node of the tree. */
    private int size; /* The number of key-value pairs in the tree */

    /* Creates an empty BSTMap. */
    public BSTMap() {
        this.clear();
    }

    /* Removes all of the mappings from this map. */
    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    /** Returns the value mapped to by KEY in the subtree rooted in P.
     *  or null if this map contains no mapping for the key.
     */
    private V getHelper(K key, Node p) {
        if (p == null)
            return null;

        int result = key.compareTo(p.key);
        if (result == 0) {
            return p.value;
        } else if (result < 0) {
            return getHelper(key, p.left);
        } else {
            return getHelper(key, p.right);
        }
    }

    /** Returns the value to which the specified key is mapped, or null if this
     *  map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        return getHelper(key, root);
    }

    /** Returns a BSTMap rooted in p with (KEY, VALUE) added as a key-value mapping.
      * Or if p is null, it returns a one node BSTMap containing (KEY, VALUE).
     */
    private Node putHelper(K key, V value, Node p) {
        if (p == null) {
            size++;
            return new Node(key, value);
        }

        int result = key.compareTo(p.key);

        if (result == 0) {
            p.value = value;
            return p;
        } else if (result < 0) {
            p.left = putHelper(key, value, p.left);
            return p;
        } else {
            p.right = putHelper(key, value, p.right);
            return p;
        }
    }

    /** Inserts the key KEY
     *  If it is already present, updates value to be VALUE.
     */
    @Override
    public void put(K key, V value) {
        root = putHelper(key, value, root);  // Assignment is unnecessary I think
    }

    /* Returns the number of key-value mappings in this map. */
    @Override
    public int size() {
        return size;
    }

    //////////////// EVERYTHING BELOW THIS LINE IS OPTIONAL ////////////////

    /* Returns a Set view of the keys contained in this map. */
    @Override
    public Set<K> keySet() {
        Set<K> keys = new HashSet<>();
        Stack<Node> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            Node curr = stack.pop();
            if (curr != null) {
                stack.push(curr.left);
                stack.push(curr.right);
                keys.add(curr.key);
            }
        }

        return keys;
    }

    /** Removes KEY from the tree if present
     *  returns VALUE removed,
     *  null on failed removal.
     */
    @Override
    public V remove(K key) {
        Node parent = null;
        Node curr = root;
        int result = key.compareTo(curr.key);

        while (result != 0 && curr != null) {
            result = key.compareTo(curr.key);
            parent = curr;
            curr = result < 0 ? curr.left : curr.right;
        }

        if (curr == null)
            return null;

        V val = curr.value;
        Node left = curr.left;
        Node right = curr.right;
        Node newChild;
        Node newChildsParent;

        if (left == null && right == null) {
            updateParent(parent, curr, null);
        } else if (left == null) {
            updateParent(parent, curr, right);
        } else if (right == null) {
            updateParent(parent, curr, left);
        } else {
            newChildsParent = curr;
            newChild = curr.left;
            while (newChild.right != null) {
                newChildsParent = newChild;
                newChild = newChild.right;
            }
            curr.value = newChild.value;
            updateParent(newChildsParent, newChild, null);
        }

        size--;
        return val;
    }

    /** Updates parent to point to newChild instead of child
     * If parent is null then updates root to point to newChild
     * @param parent Node to update child pointer of
     * @param child Node to update parent pointer to
     * @param newChild New node to point to
     */
    private void updateParent(Node parent, Node child, Node newChild) {
        if (parent == null) {
            root = newChild;
        }
        else if (parent.left == child) {
            parent.left = newChild;
        } else if (parent.right == child) {
            parent.right = newChild;
        } else {
            throw new RuntimeException();
        }
    }

    /** Removes the key-value entry for the specified key only if it is
     *  currently mapped to the specified value.  Returns the VALUE removed,
     *  null on failed removal.
     **/
    @Override
    public V remove(K key, V value) {
        V actualVal = get(key);

        if (actualVal != value)
            return null;

        remove(key);
        size--;
        return actualVal;
    }

    @Override
    public Iterator<K> iterator() {
        Set<K> keySet = keySet();
        return keySet.iterator();
    }
}
