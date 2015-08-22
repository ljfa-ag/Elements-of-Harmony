package de.ljfa.elofharmony.blocks;

import net.minecraft.block.BlockLog;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;

public class BlockLogFlutter extends BlockLog {
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
}
