import static org.junit.Assert.*;
import org.junit.Test;

public class TestArrayDequeGold {
    /**
     * Randomly adds and removes from the two ArrayDeque implementations
     * until an error is detected.
     */
    @Test
    public void testArrayDeque() {
        StudentArrayDeque<Integer> sad = new StudentArrayDeque<Integer>();
        ArrayDequeSolution<Integer> ad = new ArrayDequeSolution<Integer>();

        while (true) {
            int ceiling = StdRandom.uniform(100);
            String msg = "";
            for (int i = 0; i < ceiling; i++) {
                double addFirstOrLast = StdRandom.uniform();
                if (addFirstOrLast < 0.5) {
                    sad.addFirst(i);
                    ad.addFirst(i);
                    msg = msg.concat("addFirst(").concat(String.valueOf(i)).concat(")\n");
                }
                else {
                    sad.addLast(i);
                    sad.addLast(i);
                    msg = msg.concat("addLast(").concat(String.valueOf(i)).concat(")\n");
                }
            }
            // Now test if they agree
            for (int i = 0; i < ceiling; i++) {
                double remFirstOrLast = StdRandom.uniform();
                Integer adResult, sadResult;
                if (remFirstOrLast < 0.5) {
                    adResult = ad.removeFirst();
                    sadResult = sad.removeFirst();
                    msg = msg.concat("removeFirst()\n");
                }
                else {
                    adResult = ad.removeLast();
                    sadResult = sad.removeLast();
                    msg = msg.concat("removeLast()\n");
                }
                assertEquals(msg, adResult, sadResult);

            }
        }
    }
}
