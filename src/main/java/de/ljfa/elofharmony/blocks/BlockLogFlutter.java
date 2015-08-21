package de.ljfa.elofharmony.blocks;

import net.minecraft.block.BlockLog;

public class BlockLogFlutter extends BlockLog {
    private final String name = "log_flutter";
    
    public BlockLogFlutter() {
        setDefaultState(blockState.getBaseState().withProperty(AXIS_PROP, BlockLog.EnumAxis.Y));
        ModBlocks.register(this, name);
    }
}
