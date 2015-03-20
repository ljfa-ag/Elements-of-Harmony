package de.ljfa.elofharmony.blocks;

import de.ljfa.lib.blocks.BlockHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;

public class BlockStairsFlutter extends BlockStairs {
    private final String name = "stairs_flutter";
    
    public BlockStairsFlutter(Block block, int meta) {
        super(block, meta);
        BlockHelper.register(this, name);
    }
}
