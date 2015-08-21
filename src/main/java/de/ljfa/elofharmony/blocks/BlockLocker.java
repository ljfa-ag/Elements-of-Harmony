package de.ljfa.elofharmony.blocks;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import de.ljfa.elofharmony.ElementsOfHarmony;
import de.ljfa.elofharmony.gui.EohGuiHandler.GuiIDs;
import de.ljfa.elofharmony.tile.TileLocker;
import de.ljfa.lib.inventory.InvUtils;

public class BlockLocker extends BlockBase implements ITileEntityProvider {

    public BlockLocker() {
        super("locker", Material.wood);
        setHardness(2.0f);
        setResistance(20.0f);
        setStepSound(soundTypeWood);
        setDefaultState(blockState.getBaseState());
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return new TileLocker();
    }
    
    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player,
            EnumFacing side, float hitX, float hitY, float hitZ) {
        if(!world.isRemote) {
            player.openGui(ElementsOfHarmony.instance, GuiIDs.locker.ordinal(), world, pos.getX(), pos.getY(), pos.getZ());
        }
        return true;
    }

    @Override
    public void breakBlock(World world, BlockPos pos, IBlockState state) {
        if(!world.isRemote)
            InvUtils.spillInventory(world, pos);
        super.breakBlock(world, pos, state);
    }
    
    @Override
    public boolean hasComparatorInputOverride() {
        return true;
    }
    
    //TODO: Take into account that armor doesn't stack
    @Override
    public int getComparatorInputOverride(World world, BlockPos pos) {
        return Container.calcRedstoneFromInventory((IInventory)world.getTileEntity(pos));
    }
}
