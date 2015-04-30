package de.ljfa.elofharmony.tile;

import net.minecraft.item.ItemStack;
import de.ljfa.lib.items.ItemHelper;
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
        return "elofharmony.container.locker";
    }

    @Override
    public boolean isItemValidForSlot(int slot, ItemStack stack) {
        if(slot < armorInvStart)
            return true;
        else {
            return ItemHelper.isItemArmor(stack.getItem(), slot-armorInvStart);
        }
    }
}
