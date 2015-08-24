package de.ljfa.elofharmony.blocks;

import static de.ljfa.elofharmony.ElementsOfHarmony.logger;

import de.ljfa.elofharmony.tile.TileRitualTable;
import de.ljfa.lib.inventory.InvUtils;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockRitualTable extends BlockBase implements ITileEntityProvider {
    public static final PropertyBool RUNNING = PropertyBool.create("running");
    
    public BlockRitualTable() {
        super("ritual_table", Material.wood);
        setBlockBounds(0.0f, 0.0f, 0.0f, 1.0f, 0.75f, 1.0f);
        setHardness(2.0f);
        setResistance(20.0f);
        setStepSound(soundTypeWood);
        setDefaultState(blockState.getBaseState().withProperty(RUNNING, false));
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return new TileRitualTable();
    }
    
    @Override
    public void breakBlock(World world, BlockPos pos, IBlockState state) {
        if(!world.isRemote)
            InvUtils.spillInventory(world, pos);
        super.breakBlock(world, pos, state);
    }
    
    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player,
            EnumFacing side, float hitX, float hitY, float hitZ) {
        TileEntity tile = world.getTileEntity(pos);
        if(tile instanceof TileRitualTable) {
            return ((TileRitualTable)tile).onPlayerInteract(player);
        } else {
            logger.error("Missing or wrong tile entity at %s", pos);
            return false;
        }
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return 0;
    }
    
    @Override
    public IBlockState getActualState(IBlockState state, IBlockAccess world, BlockPos pos) {
        TileRitualTable te = (TileRitualTable)world.getTileEntity(pos);
        return state.withProperty(RUNNING, te.hasChallenge());
    }
    
    @Override
    protected BlockState createBlockState() {
        return new BlockState(this, RUNNING);
    }
}
