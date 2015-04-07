package de.ljfa.elofharmony.tile;

import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import de.ljfa.lib.tile.TileInventoryBase;

public class TileLocker extends TileInventoryBase {
    /* Slot indexes:
     *  0-35: inventory
     * 36-39: armor
     */
    public static final int normalInvStart = 0, armorInvStart = 36;
    
    public TileLocker() {
        super(36 + 4);
    }

    @Override
    public String getInventoryName() {
        return "TileLocker";
    }

    @Override
    public boolean isItemValidForSlot(int slot, ItemStack stack) {
        if(slot < armorInvStart)
            return true;
        else {
            Item item = stack.getItem();
            if(item instanceof ItemArmor)
                return ((ItemArmor)item).armorType == slot-armorInvStart;
            else
                return false;
        }
    }
}
