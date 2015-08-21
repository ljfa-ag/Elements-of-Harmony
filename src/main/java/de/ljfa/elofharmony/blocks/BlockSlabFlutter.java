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
    
    public BlockSlabFlutter(boolean is_double_slab) {
        super(Material.wood);
        setHardness(2.0F);
        setResistance(5.0F);
        setStepSound(soundTypeWood);
        ModBlocks.register(this, name);
    }
    
    public BlockSlabFlutter() { this(false); }
    
    @Override
    public Item getItem(World world, BlockPos pos) {
        return Item.getItemFromBlock(this);
    }
    
    @Override
    protected ItemStack createStackedBlock(IBlockState state) {
        return new ItemStack(this, 2, meta & 7);
    }

    @Override
    public String getFullSlabName(int p_150002_1_) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean isDouble() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public IProperty func_176551_l() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object func_176553_a(ItemStack p_176553_1_) {
        // TODO Auto-generated method stub
        return null;
    }

}
