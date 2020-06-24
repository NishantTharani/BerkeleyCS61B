public class ArrayDequeTest {
    public static void addGetTest() {
        ArrayDeque<Integer> AD = new ArrayDeque<>();
        AD.addLast(0);
        AD.addLast(1);
        AD.addLast(2);
        Integer i = AD.get(2);
    }

    public static void resizeNullTest() {
        ArrayDeque<Integer> AD = new ArrayDeque<>();
        for (Integer i = 0; i < 8; i++) {
            AD.addFirst(i);
        }

        Integer first = AD.removeFirst();
        Integer last = AD.removeLast();
    }

    public static void main(String[] args) {
        addGetTest();
        resizeNullTest();
    }
}
