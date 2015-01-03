package ljfa.elofharmony.blocks.itemblock;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;

public class ItemBlockLeavesFlutter extends ItemBlock {
    public ItemBlockLeavesFlutter(Block block) {
        super(block);
    }
    
    @Override
    public int getMetadata(int meta) {
        return meta ^ 4; 
    }
}
