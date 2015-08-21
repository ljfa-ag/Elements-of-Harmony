package de.ljfa.elofharmony.blocks;

import net.minecraft.block.BlockLog;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;

public class BlockLogFlutter extends BlockLog {
    private final String name = "log_flutter";
    
    public BlockLogFlutter() {
        setDefaultState(blockState.getBaseState().withProperty(AXIS_PROP, EnumAxis.Y));
        ModBlocks.register(this, name);
    }
    
    @Override
    protected BlockState createBlockState() {
        return new BlockState(this, AXIS_PROP);
    }
    
    @Override
    public IBlockState getStateFromMeta(int meta) {
        return getDefaultState().withProperty(AXIS_PROP, EnumAxis.values()[meta]);
    }
    
    @Override
    public int getMetaFromState(IBlockState state) {
        return ((EnumAxis)state.getValue(AXIS_PROP)).ordinal();
    }
}
