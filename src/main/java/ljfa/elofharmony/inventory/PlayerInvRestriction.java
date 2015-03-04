package ljfa.elofharmony.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;

/** Default implementation of an inventory restriction which checks every slot */
public class PlayerInvRestriction implements InventoryRestriction {
    /** @param slotRestr the slot restriction */
    public PlayerInvRestriction(SlotRestriction slotRestr) {
        this.slotRestr = slotRestr;
    }
    
    /** @return the slot restriction */
    public SlotRestriction getSlotRestr() {
        return slotRestr;
    }

    /** Checks if the player inventory follows the restriction.
     * @param inv the inventory to check
     * @return true if the restriction is being followed
     */
    @Override
    public boolean check(EntityPlayer player) {
        InventoryPlayer inv = player.inventory;
        for(int slot = 0; slot < inv.mainInventory.length; slot++) {
            if(!slotRestr.check(SlotType.NORMAL, slot, inv.mainInventory[slot]))
                return false;
        }
        for(int slot = 0; slot < inv.armorInventory.length; slot++) {
            if(!slotRestr.check(SlotType.ARMOR, slot, inv.armorInventory[slot]))
                return false;
        }
        return true;
    }
    
    /** Ejects all item stacks onto the ground that do not follow the restriction.
     * @param inv the inventory to check
     * @return true if the restriction is being followed
     */
    @Override
    public boolean checkAndEject(EntityPlayer player) {
        InventoryPlayer inv = player.inventory;
        boolean ret = true;
        for(int slot = 0; slot < inv.mainInventory.length; slot++) {
            if(!slotRestr.check(SlotType.NORMAL, slot, inv.mainInventory[slot])) {
                inv.player.entityDropItem(inv.mainInventory[slot], 0.0f);
                inv.mainInventory[slot] = null;
                ret = false;
            }
        }
        for(int slot = 0; slot < inv.armorInventory.length; slot++) {
            if(!slotRestr.check(SlotType.ARMOR, slot, inv.armorInventory[slot])) {
                inv.player.entityDropItem(inv.armorInventory[slot], 0.0f);
                inv.armorInventory[slot] = null;
                ret = false;
            }
        }
        return ret;
    }
    
    /** @return true if the player is allowed to pick up this item */
    @Override
    public boolean mayPickUp(EntityPlayer player, ItemStack stack) {
        return slotRestr.check(SlotType.NONE, 0, stack);
    }
    
    /** the slot restriction */
    protected final SlotRestriction slotRestr;
}
