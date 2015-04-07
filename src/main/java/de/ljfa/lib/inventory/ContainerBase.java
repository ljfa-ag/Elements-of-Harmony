package de.ljfa.lib.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public abstract class ContainerBase extends Container {

    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int slot) {
        return null;
    }
    
    protected void addPlayerInv(InventoryPlayer invPlayer, int x, int y) {
        for(int i = 0; i < 3; i++)
            for(int j = 0; j < 9; j++)
                addSlotToContainer(new Slot(invPlayer, 9 + j + 9*i, x + 18*j, y + 18*i));
        
        for(int j = 0; j < 9; j++)
            addSlotToContainer(new Slot(invPlayer, j, x + 18*j, y + 58));
    }
}
