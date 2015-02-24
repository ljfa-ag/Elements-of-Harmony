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
        return true;
    }
    
    public boolean checkAndEject(InventoryPlayer inv) {
        return true;
    }
    
    protected final SlotRestriction slotRestr;
}
