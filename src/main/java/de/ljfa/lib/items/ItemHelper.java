package de.ljfa.lib.items;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;

public class ItemHelper {

    /** Heuristic function that doesn't detect armor that is not extending ItemArmor */
    public static boolean isItemArmor(Item item, int armorType) {
        if(item instanceof ItemArmor)
            return ((ItemArmor)item).armorType == armorType;
        else if(armorType == 0)
            return item == Items.skull || item == Item.getItemFromBlock(Blocks.pumpkin);
        else
            return false;
    }
}
