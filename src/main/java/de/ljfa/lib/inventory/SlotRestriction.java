package de.ljfa.lib.inventory;

import net.minecraft.item.ItemStack;

public interface SlotRestriction {
    /** @return true if the stack is allowed in this slot */
    public boolean check(SlotType type, int slot, ItemStack stack);
}
