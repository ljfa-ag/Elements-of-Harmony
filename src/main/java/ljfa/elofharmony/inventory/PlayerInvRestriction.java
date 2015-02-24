package ljfa.elofharmony.inventory;

import net.minecraft.entity.player.InventoryPlayer;

public class PlayerInvRestriction {
    public PlayerInvRestriction(SlotRestriction slotRestr) {
        this.slotRestr = slotRestr;
    }
    
    public SlotRestriction getSlotRestr() {
        return slotRestr;
    }

    public boolean check(InventoryPlayer inv) {
        for(int slot = 0; slot < inv.mainInventory.length; slot++) {
            SlotType type = slot < inv.getHotbarSize() ? SlotType.HOTBAR : SlotType.NORMAL;
            if(!slotRestr.check(type, slot, inv.mainInventory[slot]))
                return false;
        }
        for(int slot = 0; slot < inv.armorInventory.length; slot++) {
            if(!slotRestr.check(SlotType.ARMOR, slot, inv.armorInventory[slot]))
                return false;
        }
        return true;
    }
    
    public boolean checkAndEject(InventoryPlayer inv) {
        boolean ret = true;
        for(int slot = 0; slot < inv.mainInventory.length; slot++) {
            SlotType type = slot < inv.getHotbarSize() ? SlotType.HOTBAR : SlotType.NORMAL;
            if(!slotRestr.check(type, slot, inv.mainInventory[slot])) {
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
    
    protected final SlotRestriction slotRestr;
}
