package de.ljfa.lib.gui;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

/** A slot which only accpets stacks that are valid for the underlying inventory */
public class SlotRestricted extends Slot {

    public SlotRestricted(IInventory inv, int index, int x, int y) {
        super(inv, index, x, y);
    }

    @Override
    public boolean isItemValid(ItemStack stack) {
        return inventory.isItemValidForSlot(slotNumber, stack);
    }
}
