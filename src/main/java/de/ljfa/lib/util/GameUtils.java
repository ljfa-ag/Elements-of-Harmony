package de.ljfa.lib.util;

import net.minecraft.client.Minecraft;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class GameUtils {
    
    @SideOnly(Side.CLIENT)
    public static int getRenderDistance() {
        int chunks = Minecraft.getMinecraft().gameSettings.renderDistanceChunks;
        if(chunks <= 0)
            chunks = 10;
        return 16 * chunks;
    }
    
    @SideOnly(Side.CLIENT)
    public static int getRenderDistanceSq() {
        int dist = getRenderDistance();
        return dist*dist;
    }
}
