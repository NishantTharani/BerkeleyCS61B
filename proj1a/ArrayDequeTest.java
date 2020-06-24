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

    public static void main(String[] args) {
        addGetTest();
        resizeNullTest();
    }
}
