/*
Implementing an AList as per ch2.5 of the cs61b textbook.
NOT part of the project.
 */

/**
 * An implementation of a list of ints that uses an Array under the hood.
 */
public class AList<ItemType> {

    private int size;
    private ItemType[] array;
    private final double RFACTOR = 1.1;

    /**
     * Initializes the AList with a maximum size of 100.
     */
    public AList() {
        size = 0;
        array = (ItemType []) new Object[100];
    }

    public void addLast(ItemType i) {
        // If the array is at capacity, resize it by creating a new one
        if (size == array.length) {
            resize((int) (size * RFACTOR));
        }
        array[size] = i;
        size += 1;
    }

    /**
     * Resizes the array to be of the given size.
     */
    public void resize(int newSize) {
        ItemType[] newArray = (ItemType []) new Object[newSize];
        System.arraycopy(array, 0, newArray, 0, size);
        array = newArray;
    }

    /**
     * Returns the last int in the list.
     * @return i - last int in the list.
     */
    public ItemType getLast() {
        return array[size - 1];
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
        ItemType i = array[size - 1];
        size -= 1;

        // If less than 25% of the array is used, resize it to half its size
        if ((double) size / array.length < 0.25) {
            resize((int) array.length / 2);
        }

        return i;
    }

    public ItemType get(int idx) {
        return array[idx];
    }

    public int size() {
        return size;
    }

}
