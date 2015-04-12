package de.ljfa.lib.gui;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import de.ljfa.lib.items.ItemHelper;

public class SlotArmor extends Slot {
    
    public final int armorType;
    
    public SlotArmor(IInventory inv, int index, int x, int y, int armorType) {
        super(inv, index, x, y);
        this.armorType = armorType;
    }

    @Override
    public boolean isItemValid(ItemStack stack) {
        return ItemHelper.isItemArmor(stack.getItem(), armorType);
    }
    
    @Override
    public int getSlotStackLimit() {
        return 1;
    }
    
    @SideOnly(Side.CLIENT)
    @Override
    public IIcon getBackgroundIconIndex() {
        return ItemArmor.func_94602_b(armorType);
    }
}
