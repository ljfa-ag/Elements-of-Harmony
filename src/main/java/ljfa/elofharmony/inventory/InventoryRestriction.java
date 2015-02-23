package ljfa.elofharmony.inventory;

import net.minecraft.entity.player.EntityPlayer;

public abstract class InventoryRestriction {
    public abstract boolean check(EntityPlayer player);
    public abstract boolean checkAndEject(EntityPlayer player);
}
