package ljfa.elofharmony.util;

import java.util.Random;

public class LjfaMathHelper {
    /** Generates a symmetrically triangular distributed integer between min and max (inclusive).
     * 
     * The expected value is (min+max)/2.
     */
    public static int triangularInt(Random rand, int min, int max) {
        int span = max-min;
        return min + rand.nextInt(span/2 + 1) + rand.nextInt((span+1)/2 + 1);
    }
    
    /** Generates a symmetrically triangular distributed double in ]min, max[.
     * 
     * The modus is at (min+max)/2.
     */
    public static double triangularDouble(Random rand, double min, double max) {
        return min + 0.5*(max-min)*(rand.nextDouble() - rand.nextDouble() + 1.0);
    }
}
