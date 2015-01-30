package ljfa.elofharmony.inventory;

import net.minecraft.entity.player.EntityPlayer;

public abstract class InventoryRestriction {
    public abstract void check(EntityPlayer player);
}
