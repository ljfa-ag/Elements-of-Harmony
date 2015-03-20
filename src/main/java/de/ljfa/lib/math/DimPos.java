package de.ljfa.lib.math;

import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.DimensionManager;

/** Represents a position in a specific dimension */
public class DimPos {
    public final int x, y, z, dim;
    
    public DimPos(int x, int y, int z, int dim) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.dim = dim;
    }
    
    /** Creates a DimPos from an {x, y, z, dim} array of coordinates. */
    public static DimPos fromArray(int[] arr) {
        return new DimPos(arr[0], arr[1], arr[2], arr[3]);
    }
    
    public static DimPos fromTile(TileEntity tile) {
        return new DimPos(tile.xCoord, tile.yCoord, tile.zCoord, tile.getWorldObj().provider.dimensionId);
    }
    
    public WorldServer getWorld() {
        WorldServer world = DimensionManager.getWorld(dim);
        if (world == null) {
            DimensionManager.initDimension(dim);
            world = DimensionManager.getWorld(dim);
        }
        return world;
    }
    
    public Block getBlock() {
        return getWorld().getBlock(x, y, z);
    }
    
    public int getMeta() {
        return getWorld().getBlockMetadata(x, y, z);
    }
    
    public TileEntity getTile() {
        return getWorld().getTileEntity(x, y, z);
    }
    
    public int[] toArray() {
        return new int[] {x, y, z, dim};
    }
    
    @Override
    public boolean equals(Object obj) {
        if(this == obj)
            return true;
        if(obj == null)
            return false;
        if(this.getClass() != obj.getClass())
            return false;
        DimPos other = (DimPos)obj;
        return dim == other.dim && x == other.x && z == other.z && y == other.y;
    }
    
    @Override
    public int hashCode() {
        final int prime = 37;
        int result = 1;
        result = prime * result + x;
        result = prime * result + y;
        result = prime * result + z;
        return result;
    }
    
    @Override
    public String toString() {
        return "(DIM" + dim + ", " + x + ", " + y + ", " + z + ")";
    }
}
