package de.ljfa.lib.gui;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import de.ljfa.lib.items.ItemHelper;

/** A slot which only accepts armor items of the specified type */
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
    public String func_178171_c()
    {
        return ItemArmor.EMPTY_SLOT_NAMES[armorType];
    }

}
