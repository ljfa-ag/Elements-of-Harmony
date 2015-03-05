package ljfa.elofharmony.util;

import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;

/** Represents a position in a specific dimension */
public class DimPos {
    public int x, y, z, dim;
    
    public DimPos(int x, int y, int z, int dim) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.dim = dim;
    }
    
    public DimPos(int x, int y, int z) {
        this(x, y, z, 0);
    }
    
    public World getWorld() {
        return WorldProvider.getProviderForDimension(dim).worldObj;
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
