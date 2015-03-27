package de.ljfa.elofharmony.blocks;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import cpw.mods.fml.common.registry.GameRegistry;
import de.ljfa.elofharmony.CreativeTabEoh;
import de.ljfa.elofharmony.Reference;

public class BlockHelper {

    /** Sets the block's name and registers it with the given ItemBlock class */
    public static <T extends Block> T register(T block, Class<? extends ItemBlock> itemClass, String name) {
        block.setBlockName(Reference.MODID + ":" + name)
        .setCreativeTab(CreativeTabEoh.EOH_TAB);
        GameRegistry.registerBlock(block, itemClass, name);
        return block;
    }

    /** Sets the block's name and registers it */
    public static <T extends Block> T register(T block, String name) {
        block.setBlockName(Reference.MODID + ":" + name)
        .setCreativeTab(CreativeTabEoh.EOH_TAB);
        GameRegistry.registerBlock(block, name);
        return block;
    }

}
