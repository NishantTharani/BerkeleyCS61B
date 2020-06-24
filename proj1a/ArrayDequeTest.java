import java.lang.reflect.Array;

public class ArrayDequeTest {
    public static void addGetTest() {
        ArrayDeque<Integer> aD = new ArrayDeque<>();
        for (Integer i = 0; i < 16; i++) {
            aD.addLast(i);
        }
        Integer three = aD.get(3);
        Integer fifteen = aD.get(15);
    }

    public static void resizeNullTest() {
        ArrayDeque<Integer> aD = new ArrayDeque<>();
        for (Integer i = 0; i < 8; i++) {
            aD.addFirst(i);
        }

        Integer first = aD.removeFirst();
        Integer last = aD.removeLast();
    }

    public static void randomAddRemoveTest1() {
        ArrayDeque<Integer> A = new ArrayDeque<>();
        A.addFirst(0);
        A.addFirst(1);
        A.addFirst(2);
        A.addFirst(3);
        int zero = A.removeLast();
        A.addFirst(5);
        A.addFirst(9);
        A.addFirst(10);
        int one = A.removeLast();
    }

    public static void main(String[] args) {
        addGetTest();
        resizeNullTest();
        randomAddRemoveTest1();
    }
}
