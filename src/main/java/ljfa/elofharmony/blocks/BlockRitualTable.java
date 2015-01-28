package ljfa.elofharmony.blocks;

import java.util.Random;

import ljfa.elofharmony.handlers.ChallengeHandler;
import ljfa.elofharmony.items.ItemTwilicane;
import ljfa.elofharmony.items.ModItems;
import ljfa.elofharmony.tile.TileInventoryBase;
import ljfa.elofharmony.tile.TileRitualTable;
import ljfa.elofharmony.util.LjfaMathHelper;
import ljfa.elofharmony.util.LogHelper;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
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
