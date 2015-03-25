package de.ljfa.lib.math;

import net.minecraft.entity.Entity;

public class MetricHelper {
    /** Computes the entity's distance from the point with the given metric */
    public static double d(Metric metr, Entity ent, double x2, double y2, double z2) {
        return metr.dist(ent.posX, ent.posY, ent.posZ, x2, y2, z2);
    }
    
    /** Computes the distance between the entities with the given metric */
    public static double d(Metric metr, Entity ent1, Entity ent2) {
        return metr.dist(ent1.posX, ent1.posY, ent1.posZ, ent2.posX, ent2.posY, ent2.posZ);
    }
    
    /** Computes the entity's distance from the DimPos with the given metric, or Infinity if different dimension */
    public static double d(Metric metr, Entity ent, DimPos pos) {
        if(ent.dimension == pos.dim) 
            return metr.dist(ent.posX, ent.posY, ent.posZ, pos.x+0.5, pos.y, pos.z+0.5);
        else
            return Double.POSITIVE_INFINITY;
    }
}
