package ljfa.elofharmony.blocks;

import ljfa.elofharmony.CreativeTabEoh;
import ljfa.elofharmony.Reference;
import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;
import cpw.mods.fml.common.registry.GameRegistry;

public class BlockStairsFlutter extends BlockStairs {
    private String name = "stairs_flutter";
    
    public BlockStairsFlutter(Block block, int meta) {
        super(block, meta);
        ModBlocks.register(this, name);
    }
}
