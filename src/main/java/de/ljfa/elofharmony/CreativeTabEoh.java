package de.ljfa.elofharmony;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import de.ljfa.elofharmony.blocks.ModBlocks;

public class CreativeTabEoh {
    public static final CreativeTabs EOH_TAB = new CreativeTabs(Reference.MODID) {
        @Override
        public Item getTabIconItem() {
            return Item.getItemFromBlock(ModBlocks.sapling_flutter);
        }
    };
}
