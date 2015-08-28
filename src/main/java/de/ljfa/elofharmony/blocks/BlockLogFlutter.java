package de.ljfa.elofharmony.blocks;

import de.ljfa.lib.items.ModeledItem;
import net.minecraft.block.BlockLog;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockLogFlutter extends BlockLog implements ModeledItem {
    private final String name = "log_flutter";
    
    public BlockLogFlutter() {
        setDefaultState(blockState.getBaseState().withProperty(LOG_AXIS, EnumAxis.Y));
        ModBlocks.register(this, name);
    }
    
    @Override
    protected BlockState createBlockState() {
        return new BlockState(this, LOG_AXIS);
    }
    
    @Override
    public IBlockState getStateFromMeta(int meta) {
        return getDefaultState().withProperty(LOG_AXIS, EnumAxis.values()[meta]);
    }
    
    @Override
    public int getMetaFromState(IBlockState state) {
        return ((EnumAxis)state.getValue(LOG_AXIS)).ordinal();
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void registerItemModels(ItemModelMesher mesher) {
        ModBlocks.defaultRegisterModel(mesher, this, name);
    }
}
