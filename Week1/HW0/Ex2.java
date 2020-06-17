public class Ex2 {
    /** Returns the maximum value from m. */
    public static int max(int[] m) {
    	int max = 0;
    	int i = 0;
    	while (i < m.length) {
    		if (m[i] > max) {
    			max = m[i];
    		}
    		i += 1;
    	}
    	return max;
    }
    public static void main(String[] args) {
       int[] numbers = new int[]{9, 2, 15, 2, 22, 10, 6};      
       int max = max(numbers);
       System.out.println(max);
    }
}