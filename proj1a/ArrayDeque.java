/*
Author: Nishant Tharani
Date: 23 June 2020
Description: An array-based implementation of a deque
 */

/**
 * Implementation of a deque that uses an Array under the hood.
 */
public class ArrayDeque<ItemType> {

    private int size;
    private final double RFACTOR = 1.25;
    private ItemType[] array;

    // These point to the index at which the next 'first element' and 'last element' should be added
    private int nextFirst;
    private int nextLast;

    /**
     * Initializes the deque with a maximum size of 8 to start with
     */
    public ArrayDeque() {
        size = 0;
        array = (ItemType [] ) new Object[8];
        nextFirst = 0;
        nextLast = 1;
    }

    /**
     * Initialises the deque as a deep copy of another one.
     */
    public ArrayDeque(ArrayDeque other) {
        size = 0;
        int otherSize = other.size();
        array = (ItemType [] ) new Object[otherSize];

        for (int i = 0; i < otherSize; i++) {
            ItemType item = (ItemType) other.removeFirst();
            addLast(item);
        }

    }

    /**
     * Resizes the underlying array to be newSize large.
     * Note that the nextFirst and nextLast pointers have to be updated.
     */
    public void resize(int newSize) {
        // Create new array
        ItemType[] newArray = (ItemType []) new Object[newSize];

        // Find position of the current first and last element
        int currFirst;
        int currLast;

        if (nextFirst == size - 1) {
            currFirst = 0;
        }
        else {
            currFirst = nextFirst + 1;
        }

        if (nextLast == 0) {
            currLast = size - 1;
        }
        else {
            currLast = nextLast - 1;
        }

        // If the underlying array has no 'wrap-around' it's a straightforward copy
        if (currLast >= currFirst) {
            System.arraycopy(array, currFirst, newArray, 0, size);
        }
        // If the underlying array has wrap-around then two copies are needed
        else {
            System.arraycopy(array, currFirst, newArray, 0, array.length - currFirst);
            System.arraycopy(array, 0, newArray, array.length - currFirst, currLast + 1);
        }

        // Update reference variables
        nextFirst = newSize - 1;
        nextLast = size;
        array = newArray;
        size = newSize;
    }

    public void addLast(ItemType i) {
        // If the array is at capacity, resize it by creating a new one
        if (size == array.length) {
            resize((int) (size * RFACTOR));
        }
        array[nextLast] = i;
        size += 1;

        // Increment nextLast by 1, or wrap around from the beginning if needed
        if (nextLast == size - 1) {
            nextLast = 0;
        }
        else {
            nextLast += 1;
        }
    }


    /**
     * Removes and returns the last int from the list.
     * Note that we don't have to actually change the array[i] to do this, just
     * decrement the size, as our implementation considers 'size' to be the effective
     * length of the list we've defined.
     *
     * @return last int in the list
     */
    public ItemType removeLast() {
        // Get the item and adjust the reference variables
        if (nextLast == 0) {
            nextLast = size - 1;
        }
        else {
            nextLast = nextLast - 1;
        }
        ItemType i = array[nextLast];
        array[nextLast] = null;
        size -= 1;

        // If less than 25% of the array is used, resize it to half its size
        if ((double) size / array.length < 0.25) {
            resize(array.length /2);
        }

        return i;
    }

    /**
     * Adds item i to the front of the deque and updates the nextFirst pointer
     * @param i - item to add to the front of the deque
     */
    public void addFirst(ItemType i) {
        if (size == array.length) {
            resize((int) ( size * RFACTOR));
        }

        array[nextFirst] = i;
        size += 1;

        // Decrement nextFirst by 1, with wraparound if necessary
        if (nextFirst == 0) {
            nextFirst = size - 1;
        }
        else {
            nextFirst -= 1;
        }
    }

    /**
     * Returns the first item in the deque and removes it by
     * updating the nextFirst pointer and setting the reference in the
     * underlying array to null.
     * @return ItemType i - the first item in the deque.
     */
    public ItemType removeFirst() {
        // Get the item and update reference variables
        if (nextFirst == size - 1) {
            nextFirst = 0;
        }
        else {
            nextFirst += 1;
        }
        ItemType i = array[nextFirst];
        array[nextFirst] = null;
        size -= 1;

        // Resize array if less than 25% is used
        if ((double) size / array.length < 0.25) {
            resize(array.length /2);
        }

        return i;
    }

    public ItemType get(int idx) {
        // Adjust index by nextFirst
        idx += nextFirst + 1;
        if (idx >= size) {
            idx -= size;
        }
        return array[idx];
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Prints a representation of the deque.
     */
    public void printDeque() {
        for (int i = 0; i < size; i++) {
            ItemType itm = array[i];
            System.out.print(itm);
            System.out.print(" ");
        }
        System.out.println();
    }

}