package de.ljfa.lib.inventory;

import net.minecraft.item.ItemStack;

/** A restriction which can be applied to a slot in a player's inventory */
public interface PlayerSlotRestriction {
    /** @return true if the stack is allowed in this slot */
    public boolean check(PlayerSlotType type, int slot, ItemStack stack);
}
