package de.ljfa.lib.util;

import net.minecraft.world.WorldServer;
import net.minecraftforge.common.DimensionManager;

public class Utils {
    /** Gets a world by dimension ID. Loads the dimension if needed. */
    public static WorldServer getOrInitWorld(int dimensionID) {
        WorldServer world = DimensionManager.getWorld(dimensionID);
        if (world == null) {
            DimensionManager.initDimension(dimensionID);
            world = DimensionManager.getWorld(dimensionID);
        }
        return world;
    }

}
