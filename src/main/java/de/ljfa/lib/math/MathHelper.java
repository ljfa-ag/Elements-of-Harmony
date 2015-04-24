package de.ljfa.lib.math;

import java.util.Random;

import net.minecraft.util.AxisAlignedBB;

public class MathHelper {

    /** Generates a symmetrically triangular distributed integer between min and max (inclusive).
     * The expected value is (min+max)/2.
     */
    public static int triangularInt(Random rand, int min, int max) {
        int span = max-min;
        return min + rand.nextInt(span/2 + 1) + rand.nextInt((span+1)/2 + 1);
    }

    /** Generates a symmetrically triangular distributed double in ]min, max[.
     * The modus is at (min+max)/2.
     */
    public static double triangularDouble(Random rand, double min, double max) {
        return min + 0.5*(max-min)*(rand.nextDouble() - rand.nextDouble() + 1.0);
    }

    /** Generates a triangular distributed double with expected value 0 and variance 1.
     * The range is ]-sqrt(6), sqrt(6)[
     */
    public static double stdTriangular(Random rand) {
        final double sqrt6 = Math.sqrt(6.0);
        return triangularDouble(rand, -sqrt6, sqrt6);
    }
    
    /** @return an AxisAlignedBB with the specified midpoint and the specified semi-axes */
    public static AxisAlignedBB boundingBoxAround(double xmid, double ymid, double zmid, double xrad, double yrad, double zrad) {
        return AxisAlignedBB.getBoundingBox(xmid-xrad, ymid-yrad, zmid-zrad,  xmid+xrad, ymid+yrad, zmid+zrad);
    }
    
    /** @return an AxisAlignedBB with the specified midpoint and the specified radius in all directions */
    public static AxisAlignedBB boundingBoxAround(double xmid, double ymid, double zmid, double radius) {
        return boundingBoxAround(xmid, ymid, zmid, radius, radius, radius);
    }

}
