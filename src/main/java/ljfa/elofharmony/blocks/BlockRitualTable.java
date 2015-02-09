package ljfa.elofharmony.blocks;

import ljfa.elofharmony.items.ItemTwilicane;
import ljfa.elofharmony.tile.TileRitualTable;
import ljfa.elofharmony.util.LogHelper;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
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

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return new TileRitualTable();
    }
    
    @Override
    public void breakBlock(World world, int x, int y, int z, Block block, int meta) {
        TileEntity tile = world.getTileEntity(x, y, z);
        if(tile instanceof TileRitualTable) {
            TileRitualTable te = (TileRitualTable)tile;
            te.spillItems();
        }
        world.removeTileEntity(x, y, z);
        super.breakBlock(world, x, y, z, block, meta);
    }
    
    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player,
            int side, float par7, float par8, float par9) {
        TileEntity tile = world.getTileEntity(x, y, z);
        if(tile instanceof TileRitualTable) {
            ((TileRitualTable)tile).onPlayerInteract(player);
            return true;
        } else {
            LogHelper.error("Missing or wrong tile entity at (%d,%d,%d)", x, y, z);
            return false;
        }
    }
    
    /*public boolean isOpaqueCube() {
        return false;
    }
    
    @SideOnly(Side.CLIENT)
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
