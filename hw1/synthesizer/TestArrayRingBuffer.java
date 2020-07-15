package synthesizer;
import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        ArrayRingBuffer<ArrayRingBuffer<Integer>> ArbOuter = new ArrayRingBuffer<>(3);
        for (int i = 0; i < 3; i++) {
            ArrayRingBuffer<Integer> ArbInner = new ArrayRingBuffer<>(3);
            for (int j = 0; j < 3; j++) {
                ArbInner.enqueue((j * i) + 1);
            }
            ArbOuter.enqueue(ArbInner);
        }

        int[] actual = new int[9];
        int idx = 0;
        for (ArrayRingBuffer<Integer> inner : ArbOuter) {
            for (Integer i : inner) {
                actual[idx] = i;
                idx += 1;
            }
        }

        int[] expected = new int[]{1, 1, 1, 1, 2, 3, 1, 3, 5};

        org.junit.Assert.assertArrayEquals(expected, actual);
    }

    /** Calls tests for ArrayRingBuffer. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestArrayRingBuffer.class);
    }
} 
