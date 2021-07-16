package hw3.hash;

import java.util.ArrayList;
import java.util.List;

public class OomageTestUtility {
    public static boolean haveNiceHashCodeSpread(List<Oomage> oomages, int M) {
        int[] bucketSizes = new int[M];
        int min = oomages.size() / 50;
        int max = (int) (oomages.size() / 2.5);

        for (Oomage oomage : oomages) {
            int bucket = (oomage.hashCode() & 0x7FFFFFFF) % M;
            bucketSizes[bucket]++;
        }

        for (int bucket : bucketSizes) {
            if (bucket < min || bucket > max) {
                return false;
            }
        }

        return true;
    }
}
