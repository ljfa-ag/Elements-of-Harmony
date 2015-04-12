package de.ljfa.lib.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

/** A restriction which can be applied to a player's inventory */
public interface PlayerInventoryRestriction {
    /** Checks if the player's inventory follows the restriction.
     * @param player the player to check
     * @return true if the restriction is being followed
     */
    public boolean check(EntityPlayer player);
    
    /** Ejects all item stacks onto the ground that do not follow the restriction.
     * @param player the player to check
     * @return true if the restriction is being followed
     */
    public boolean checkAndEject(EntityPlayer player);
    
    /** @return true if the player is allowed to pick up this item */
    public boolean mayPickUp(EntityPlayer player, ItemStack stack);
}
