package de.ljfa.elofharmony.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import de.ljfa.elofharmony.ElementsOfHarmony;
import de.ljfa.elofharmony.gui.EohGuiHandler.GuiIDs;
import de.ljfa.elofharmony.tile.TileLocker;

public class BlockLocker extends BlockBase implements ITileEntityProvider {

    public BlockLocker() {
        super("locker", Material.wood);
        setHardness(2.0f);
        setResistance(20.0f);
        setStepSound(soundTypeWood);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return new TileLocker();
    }
    
    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player,
            int side, float hitX, float hitY, float hitZ) {
        if(!world.isRemote) {
            player.openGui(ElementsOfHarmony.instance, GuiIDs.locker.ordinal(), world, x, y, z);
        }
        return true;
    }

    @Override
    public void breakBlock(World world, int x, int y, int z, Block block, int meta) {
        if(!world.isRemote)
            BlockHelper.spillInventory(world, x, y, z);
        super.breakBlock(world, x, y, z, block, meta);
    }
    
    @Override
    public boolean hasComparatorInputOverride() {
        return true;
    }
    
    //TODO: Take into account that armor doesn't stack
    @Override
    public int getComparatorInputOverride(World world, int x, int y, int z, int side) {
        return Container.calcRedstoneFromInventory((IInventory)world.getTileEntity(x, y, z));
    }
}
