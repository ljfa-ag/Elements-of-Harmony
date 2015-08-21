package de.ljfa.elofharmony.blocks;

import net.minecraft.block.BlockStairs;
import net.minecraft.block.state.IBlockState;

public class BlockStairsFlutter extends BlockStairs {
    private final String name = "stairs_flutter";
    
    public BlockStairsFlutter(IBlockState modelState) {
        super(modelState);
        ModBlocks.register(this, name);
    }
}
