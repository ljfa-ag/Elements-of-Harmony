package ljfa.elofharmony.inventory;

import net.minecraft.entity.player.InventoryPlayer;

public interface PlayerInvRestriction {
    public boolean check(InventoryPlayer inv);
    public boolean checkAndEject(InventoryPlayer inv);
}
