package ljfa.elofharmony;

import ljfa.elofharmony.blocks.ModBlocks;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class CreativeTabEoh {
    public static final CreativeTabs EOH_TAB = new CreativeTabs(Reference.MODID) {
        @Override
        public Item getTabIconItem() {
            return Item.getItemFromBlock(ModBlocks.poisonjoke);
        }
    };
}
