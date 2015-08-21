package de.ljfa.elofharmony.blocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockPlanks.EnumType;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import de.ljfa.elofharmony.blocks.itemblock.ItemBlockLeavesFlutter;
import de.ljfa.elofharmony.items.ItemResource.ResourceType;
import de.ljfa.elofharmony.items.ModItems;

public class BlockLeavesFlutter extends BlockLeaves {
    private final String name = "leaves_flutter";
    
    private static final PropertyBool DECAYABLE = field_176237_a, CHECK_DECAY = field_176236_b;
    
    public BlockLeavesFlutter() {
        setDefaultState(blockState.getBaseState().withProperty(DECAYABLE, true));
        ModBlocks.register(this, ItemBlockLeavesFlutter.class, name);
    }
    
    @Override
    public ArrayList<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
        ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
        if((Boolean)state.getValue(DECAYABLE))
            return ret;
        
        Random rand = world instanceof World ? ((World)world).rand : new Random();
        // 5.5% to 10.2% chance
        // Expected: 2.9 to 5.4 per tree
        if(rand.nextInt(128) < 7 + 2*fortune)
            ret.add(new ItemStack(ModBlocks.sapling_flutter));
        
        // 3.9% to 6.3% chance
        // Expected: 2.1 to 3.3 per tree
        if(rand.nextInt(128) < 5 + fortune)
            ret.add(new ItemStack(ModItems.resource, 1, ResourceType.YELLOW_FEATHER.ordinal()));
        
        return ret;
    }
    
    @Override
    protected boolean canSilkHarvest() {
        return false;
    }
    
    @Override
    public boolean isOpaqueCube() {
        return Blocks.leaves.isOpaqueCube();
    }
    
    @SideOnly(Side.CLIENT)
    @Override
    public boolean shouldSideBeRendered(IBlockAccess world, BlockPos pos, EnumFacing side) {
        if(isOpaqueCube()) {
            if(world.getBlockState(pos).getBlock() == this)
                return false;
            else
                return super.shouldSideBeRendered(world, pos, side);
        } else
            return true;
    }
    
    @SideOnly(Side.CLIENT)
    @Override
    public int getRenderColor(IBlockState state) {
        return 0xFF75AC; //Pink
    }
    
    @SideOnly(Side.CLIENT)
    @Override
    public int colorMultiplier(IBlockAccess blockAcc, BlockPos pos, int pass) {
        return 0xFF75AC; //Pink
    }

    @Override
    public List<ItemStack> onSheared(ItemStack item, IBlockAccess world, BlockPos pos, int fortune) {
        return null;
    }
    
    @Override
    protected BlockState createBlockState() {
        return new BlockState(this, DECAYABLE, CHECK_DECAY);
    }
    
    @Override
    public IBlockState getStateFromMeta(int meta) {
        return getDefaultState().withProperty(DECAYABLE, (meta & 4) != 0)
                .withProperty(CHECK_DECAY, (meta & 8) != 0);
    }
    
    @Override
    public int getMetaFromState(IBlockState state) {
        int ret = 0;
        if((Boolean)state.getValue(DECAYABLE))
            ret |= 4;
        if((Boolean)state.getValue(CHECK_DECAY))
            ret |= 8;
        return ret;
    }

    @Override
    public EnumType func_176233_b(int par1) {
        return null;
    };

}
