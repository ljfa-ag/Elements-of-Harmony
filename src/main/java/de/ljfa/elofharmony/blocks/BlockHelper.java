package de.ljfa.elofharmony.blocks;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.registry.GameRegistry;
import de.ljfa.elofharmony.CreativeTabEoh;
import de.ljfa.elofharmony.Reference;
import de.ljfa.lib.tile.TileInventoryBase;

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

    public static void spillInventory(World world, int x, int y, int z) {
        TileEntity tile = world.getTileEntity(x, y, z);
        if(tile instanceof TileInventoryBase)
            ((TileInventoryBase)tile).spillItems();
    }
}
