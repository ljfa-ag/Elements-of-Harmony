package de.ljfa.elofharmony.blocks;

import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class BlockSlabFlutter extends BlockSlab {
    private final String name = "slab_flutter";
    
    private final boolean isDouble;
    
    public BlockSlabFlutter(boolean isDouble) {
        super(Material.wood);
        this.isDouble = isDouble;
        setHardness(2.0F);
        setResistance(5.0F);
        setStepSound(soundTypeWood);
        IBlockState def = blockState.getBaseState();
        if(isDouble)
            def = def.withProperty(HALF_PROP, EnumBlockHalf.BOTTOM);
        setDefaultState(def);
        ModBlocks.register(this, name);
    }
    
    @Override
    public Item getItem(World world, BlockPos pos) {
        return Item.getItemFromBlock(this);
    }
    
    /*@Override
    protected ItemStack createStackedBlock(IBlockState state) {
        return new ItemStack(this, 2, meta & 7);
    }*/

    @Override
    public String getFullSlabName(int meta) {
        return super.getUnlocalizedName();
    }

    @Override
    public boolean isDouble() {
        return isDouble;
    }

    @Override
    public IProperty func_176551_l() {
        return null; //FIXME probably NPE
    }

    @Override
    public Object func_176553_a(ItemStack p_176553_1_) {
        return null; //FIXME
    }

}
