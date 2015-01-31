package ljfa.elofharmony.blocks;

import ljfa.elofharmony.items.ItemTwilicane;
import ljfa.elofharmony.tile.TileInventoryBase;
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
        TileEntity te = world.getTileEntity(x, y, z);
        if(te instanceof TileInventoryBase)
            ((TileInventoryBase)te).spillItems();
        world.removeTileEntity(x, y, z);
        super.breakBlock(world, x, y, z, block, meta);
    }
    
    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player,
            int side, float par7, float par8, float par9) {
        InventoryPlayer playerInv = player.inventory;
        TileEntity tile = world.getTileEntity(x, y, z);
        if(tile instanceof TileRitualTable) {
            int playerSlot = playerInv.currentItem;
            ItemStack playerStack = playerInv.getCurrentItem();
            if(playerStack != null && playerStack.getItem() instanceof ItemTwilicane)
                return false;
            
            TileRitualTable te = (TileRitualTable)tile;
            ItemStack tableStack = te.getStackInSlot(0);
            if(tableStack != null) {
                if(playerInv.addItemStackToInventory(tableStack)) {
                    te.setInventorySlotContents(0, null);
                    return true;
                } else
                    return false;
            } else {
                if(te.isItemValidForSlot(0, playerStack)) {
                    ItemStack playerSplitStack;
                    if(player.capabilities.isCreativeMode)
                        playerSplitStack = playerStack.copy();
                    else
                        playerSplitStack = playerInv.decrStackSize(playerSlot, 1);
                    
                    te.setInventorySlotContents(0, playerSplitStack);
                    return playerSplitStack != null;
                } else
                    return false;
            }
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
