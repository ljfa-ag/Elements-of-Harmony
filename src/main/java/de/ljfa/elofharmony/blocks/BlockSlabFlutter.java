package de.ljfa.elofharmony.blocks;

import de.ljfa.lib.items.ModeledItem;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockSlabFlutter extends BlockSlab implements ModeledItem {
    private final String name = "slab_flutter";
    
    private final boolean isDouble;
    
    public BlockSlabFlutter(boolean isDouble) {
        super(Material.wood);
        this.isDouble = isDouble;
        setHardness(2.0F);
        setResistance(5.0F);
        setStepSound(soundTypeWood);
        IBlockState def = blockState.getBaseState();
        if(!isDouble)
            def = def.withProperty(HALF, EnumBlockHalf.BOTTOM);
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
    public String getUnlocalizedName(int meta) {
        return super.getUnlocalizedName();
    }

    @Override
    public boolean isDouble() {
        return isDouble;
    }

    @Override
    public IProperty getVariantProperty() {
        return null; //FIXME probably NPE
    }

    @Override
    public Object getVariant(ItemStack p_176553_1_) {
        return null; //FIXME
    }
    
    @Override
    protected BlockState createBlockState() {
        if(!isDouble)
            return new BlockState(this, HALF);
        else
            return new BlockState(this);
    }
    
    @Override
    public IBlockState getStateFromMeta(int meta) {
        if(!isDouble)
            return getDefaultState().withProperty(HALF, EnumBlockHalf.values()[meta]);
        else
            return getDefaultState();
    }
    
    @Override
    public int getMetaFromState(IBlockState state) {
        if(!isDouble)
            return ((EnumBlockHalf)state.getValue(HALF)).ordinal();
        else
            return 0;
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void registerItemModels(ItemModelMesher mesher) {
        ModBlocks.defaultRegisterModel(mesher, this, name);
    }
}
