package de.ljfa.lib.math;

import net.minecraft.entity.Entity;

public class MetricHelper {
    /** Computes the Manhattan distance between two points */
    public static double dist1(double x1, double y1, double z1, double x2, double y2, double z2) {
        return Math.abs(x1-x2) + Math.abs(y1-y2) + Math.abs(z1-z2);
    }
    
    /** Computes the entity's Manhattan distance from the point */
    public static double dist1(Entity ent, double x2, double y2, double z2) {
        return dist1(ent.posX, ent.posY, ent.posZ, x2, y2, z2);
    }
    
    /** Computes the entity's Manhattan distance from the DimPos, or Infinity if different dimension */
    public static double dist1(Entity ent, DimPos pos) {
        if(ent.dimension == pos.dim) 
            return dist1(ent.posX, ent.posY, ent.posZ, pos.x+0.5, pos.y+0.5, pos.z+0.5);
        else
            return Double.POSITIVE_INFINITY;
    }
    
    /** Computes the squared euclidean distance between two points */
    public static double dist2sq(double x1, double y1, double z1, double x2, double y2, double z2) {
        return (x1-x2)*(x1-x2) + (y1-y2)*(y1-y2) + (z1-z2)*(z1-z2);
    }
    
    /** Computes the entity's squared euclidean distance from the point */
    public static double dist2sq(Entity ent, double x2, double y2, double z2) {
        return dist2sq(ent.posX, ent.posY, ent.posZ, x2, y2, z2);
    }
    
    /** Computes the entity's squared euclidean distance from the DimPos, or Infinity if different dimension */
    public static double dist2sq(Entity ent, DimPos pos) {
        if(ent.dimension == pos.dim) 
            return dist2sq(ent.posX, ent.posY, ent.posZ, pos.x+0.5, pos.y+0.5, pos.z+0.5);
        else
            return Double.POSITIVE_INFINITY;
    }
    
    /** Computes the euclidean distance between two points */
    public static double dist2(double x1, double y1, double z1, double x2, double y2, double z2) {
        return Math.sqrt(dist2sq(x1, y1, z1, x2, y2, z2));
    }
    
    /** Computes the entity's euclidean distance from the point */
    public static double dist2(Entity ent, double x2, double y2, double z2) {
        return dist2(ent.posX, ent.posY, ent.posZ, x2, y2, z2);
    }
    
    /** Computes the entity's euclidean distance from the point, or Infinity if different dimension */
    public static double dist2(Entity ent, DimPos pos) {
        if(ent.dimension == pos.dim) 
            return dist2(ent.posX, ent.posY, ent.posZ, pos.x+0.5, pos.y+0.5, pos.z+0.5);
        else
            return Double.POSITIVE_INFINITY;
    }
    
    /** Computes the maximum norm distance between two points */
    public static double distInf(double x1, double y1, double z1, double x2, double y2, double z2) {
        return Math.max(Math.abs(x1-x2), Math.max(Math.abs(y1-y2), Math.abs(z1-z2)));
    }
    
    /** Computes the entity's maximum norm distance from the point */
    public static double distInf(Entity ent, double x2, double y2, double z2) {
        return distInf(ent.posX, ent.posY, ent.posZ, x2, y2, z2);
    }
    
    /** Computes the entity's maximum norm distance from the DimPos, or Infinity if different dimension */
    public static double distInf(Entity ent, DimPos pos) {
        if(ent.dimension == pos.dim) 
            return distInf(ent.posX, ent.posY, ent.posZ, pos.x+0.5, pos.y+0.5, pos.z+0.5);
        else
            return Double.POSITIVE_INFINITY;
    }
}
