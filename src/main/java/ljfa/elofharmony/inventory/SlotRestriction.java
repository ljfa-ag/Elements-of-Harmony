package ljfa.elofharmony.inventory;

import net.minecraft.item.ItemStack;

public interface SlotRestriction {
    public boolean check(SlotType type, int slot, ItemStack stack);
}
