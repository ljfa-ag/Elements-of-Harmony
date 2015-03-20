package de.ljfa.lib.math;

import java.util.Random;

import net.minecraft.entity.Entity;

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
    
    /** Computes the Manhattan distance between two points */
    public static double dist1(double x1, double y1, double z1, double x2, double y2, double z2) {
        return Math.abs(x1-x2) + Math.abs(y1-y2) + Math.abs(z1-z2);
    }
    
    /** Computes the entity's Manhattan distance from the point */
    public static double dist1(Entity ent, double x2, double y2, double z2) {
        return dist1(ent.posX, ent.posY, ent.posZ, x2, y2, z2);
    }
    
    /** Computes the squared euclidean distance between two points */
    public static double dist2sq(double x1, double y1, double z1, double x2, double y2, double z2) {
        return (x1-x2)*(x1-x2) + (y1-y2)*(y1-y2) + (z1-z2)*(z1-z2);
    }
    
    /** Computes the entity's squared euclidean distance from the point */
    public static double dist2sq(Entity ent, double x2, double y2, double z2) {
        return dist2sq(ent.posX, ent.posY, ent.posZ, x2, y2, z2);
    }
    
    /** Computes the euclidean distance between two points */
    public static double dist2(double x1, double y1, double z1, double x2, double y2, double z2) {
        return Math.sqrt(dist2sq(x1, y1, z1, x2, y2, z2));
    }
    
    /** Computes the entity's euclidean distance from the point */
    public static double dist2(Entity ent, double x2, double y2, double z2) {
        return dist2(ent.posX, ent.posY, ent.posZ, x2, y2, z2);
    }
    
    /** Computes the maximum norm distance between two points */
    public static double distInf(double x1, double y1, double z1, double x2, double y2, double z2) {
        return Math.max(Math.abs(x1-x2), Math.max(Math.abs(y1-y2), Math.abs(z1-z2)));
    }
    
    /** Computes the entity's maximum norm distance from the point */
    public static double distInf(Entity ent, double x2, double y2, double z2) {
        return distInf(ent.posX, ent.posY, ent.posZ, x2, y2, z2);
    }
}
