package de.ljfa.elofharmony.blocks.itemblock;

import de.ljfa.elofharmony.blocks.BlockLeavesFlutter;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemBlockLeavesFlutter extends ItemBlock {
    public ItemBlockLeavesFlutter(Block block) {
        super(block);
    }
    
    @Override
    public int getMetadata(int meta) {
        return meta ^ 4; 
    }
    
    @SideOnly(Side.CLIENT)
    @Override
    public int getColorFromItemStack(ItemStack stack, int renderPass) {
        return BlockLeavesFlutter.pink_color;
    }
}
