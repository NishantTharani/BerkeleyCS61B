/*
Author: Nishant Tharani
Date: 22 June 2020
Description: A linked list implementation of a Deque
 */


/**
 * Implementation of a generic deque that uses a doubly linked list under the hood.
 */
public class LinkedListDeque<ItemType> {

    /**
     * Double facing nodes used to make up the list.
     *
     * Each node represents an item in the list, and contains:
     *      the value of the item
     *      a reference to the next item
     *      a reference to the previous item
     */
    public class Node {
        public Node prev;
        public Node next;
        public ItemType item;

        public Node(ItemType i, Node p, Node n) {
            prev = p;
            next = n;
            item = i;
        }

        /**
         * Constructor used to create a circular sentinel node when initialising
         * the deque. This node's previous and next references point to itself.
         */
        public Node(ItemType i) {
            prev = this;
            next = this;
            item = i;
        }
    }

    private Node sentinel;
    private int size;

    /**
     * Constructor for an empty deque.
     */
    public LinkedListDeque() {
        size = 0;
        sentinel = new Node(null);
    }

    // TODO - deep copy constructor

    /**
     * Adds an item to the front of the deque.
     * @param item - item to be added to the front of the deque.
     */
    public void addFirst(ItemType item) {
        Node secondNode = sentinel.next;
        size += 1;
        Node newSecondNode = new Node(item, sentinel, secondNode);
        sentinel.next = newSecondNode;
        secondNode.prev = newSecondNode;
    }

    /**
     * Adds an item to the end of the deque.
     * @param item - item to be added to the end of the deque.
     */
    public void addLast(ItemType item) {
        Node lastNode = sentinel.prev;
        size += 1;
        Node newLastNode = new Node(item, lastNode, sentinel);
        sentinel.prev = newLastNode;
        lastNode.next = newLastNode;
    }

    /**
     * Removes and returns the first item from the deque.
     * @return item - the first item of the deque, now removed.
     */
    public ItemType removeFirst() {
        Node firstNode = sentinel.next;
        sentinel.next = firstNode.next;
        size -= 1;
        return firstNode.item;
    }

    /**
     * Removes and returns the last item from the deque.
     * @return item - the last item of the deque, now removed.
     */
    public ItemType removeLast() {
        Node lastNode = sentinel.prev;
        sentinel.prev = lastNode.prev;
        size -= 1;
        return lastNode.item;
    }

    /**
     * Returns true if the deque is empty, false if not.
     */
    public boolean isEmpty() {
        return sentinel.next == sentinel;
    }

    /**
     * Returns the number of items in the deque.
     * @return size - number of items in the deque.
     */
    public int size() {
        return size;
    }

    /**
     * Returns the item at the specified index of the deque.
     * @param index - index of the deque to fetch item from.
     * @return - the item at specified index.
     */
    public ItemType get(int index) {
        Node currentNode = sentinel;

        // Simple loop to get us to the correct node
        for (int i = 0; i <= index; i++) {
            currentNode = currentNode.next;
        }

        return currentNode.item;
    }
}
