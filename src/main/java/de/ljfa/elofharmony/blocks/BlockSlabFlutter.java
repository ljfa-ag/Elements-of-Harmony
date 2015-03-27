package de.ljfa.elofharmony.blocks;

import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockSlabFlutter extends BlockSlab {
    private final String name = "slab_flutter";
    
    public BlockSlabFlutter(boolean is_double_slab) {
        super(is_double_slab, Material.wood);
        setHardness(2.0F);
        setResistance(5.0F);
        setStepSound(soundTypeWood);
        BlockHelper.register(this, name);
    }
    
    public BlockSlabFlutter() { this(false); }
    
    @Override
    public Item getItem(World world, int x, int y, int z) {
        return Item.getItemFromBlock(this);
    }
    
    @Override
    protected ItemStack createStackedBlock(int meta) {
        return new ItemStack(this, 2, meta & 7);
    }
    
    @Override
    public String func_150002_b(int par1) {
        return super.getUnlocalizedName();
    }
    
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta) {
        return ModBlocks.planks_flutter.getIcon(side, meta & 7);
    }
}
