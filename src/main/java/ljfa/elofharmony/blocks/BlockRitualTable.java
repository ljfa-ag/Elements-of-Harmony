package ljfa.elofharmony.blocks;

import java.util.Random;

import ljfa.elofharmony.tile.TileInventoryBase;
import ljfa.elofharmony.tile.TileRitualTable;
import ljfa.elofharmony.util.LjfaMathHelper;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockRitualTable extends EohBlock implements ITileEntityProvider {
    public BlockRitualTable() {
        super("ritual_table", Material.wood);
        setHardness(2.0f);
        setResistance(20.0f);
        setStepSound(soundTypeWood);
    }
    
    /*@Override
    public boolean isOpaqueCube() {
        return false;
    }*/

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return new TileRitualTable();
    }
    
    @Override
    public void breakBlock(World world, int x, int y, int z, Block block, int meta) {
        TileEntity te = world.getTileEntity(x, y, z);
        if(te instanceof TileInventoryBase)
            ((TileInventoryBase)te).spillItems();
        world.removeTileEntity(x, y, z);
        super.breakBlock(world, x, y, z, block, meta);
    }
    
    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player,
            int side, float fx, float fy, float fz) {
        if(world.isRemote)
            return true;
        Random rand = world.rand;

        double mean = 300.0, sigma = 20.0;
        
        double dist = mean + sigma * rand.nextGaussian();
        double angle = 2 * Math.PI * rand.nextDouble();
        
        int tpx = (int)(dist * Math.sin(angle));
        int tpz = (int)(dist * Math.cos(angle));
        int tpy = world.getTopSolidOrLiquidBlock(tpx, tpz);
        
        player.setPositionAndUpdate(tpx + 0.5, tpy + 2.0, tpz + 0.5);
        
        return true;
    }
    
    /*@SideOnly(Side.CLIENT)
    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }
    
    @SideOnly(Side.CLIENT)
    @Override
    public int getRenderType() {
        return 0;
    }*/
}
