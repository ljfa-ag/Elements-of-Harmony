package de.ljfa.elofharmony.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import de.ljfa.elofharmony.tile.TileLocker;
import de.ljfa.lib.inventory.ContainerBase;

public class ContainerLocker extends ContainerBase {

    private static final int playerInvStart = 0, playerHotbarStart = 27, playerArmorStart = 36;
    private static final int lockerInvStart = 40, lockerHotbarStart = 67, lockerArmorStart = 76, endSlots = 80;
    
    private final TileLocker tile;
    
    public ContainerLocker(InventoryPlayer invPlayer, TileLocker tile) {
        this.tile = tile;
        addPlayerInv(invPlayer, 30, 108);
        
        //Player armor slots
        for(int i = 0; i < 4; i++)
            addSlotToContainer(new Slot(invPlayer, 39 - i, 8, 110 + 18*i));
        
        //Locker inventory slots
        for(int i = 0; i < 3; i++)
            for(int j = 0; j < 9; j++)
                addSlotToContainer(new Slot(tile, 9 + j + 9*i, 30 + 18*j, 18 + 18*i));
        
        //Locker hotbar slots
        for(int j = 0; j < 9; j++)
            addSlotToContainer(new Slot(tile, j, 30 + 18*j, 76));
        
        //Locker armor slots
        for(int i = 0; i < 4; i++)
            addSlotToContainer(new Slot(tile, 36 + i, 8, 20 + 18*i));
    }
    
    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int slotInd) {
        ItemStack copyStack = null;
        Slot slot = (Slot)inventorySlots.get(slotInd);
        
        if(slot != null && slot.getHasStack()) {
            ItemStack stackInSlot = slot.getStack();
            copyStack = stackInSlot.copy();
            boolean isInPlayerInv = slotInd < lockerInvStart;
            
            int corrSlotInd = slotInd + (isInPlayerInv ? lockerInvStart : -lockerInvStart);
            if(!mergeItemStack(stackInSlot, corrSlotInd, corrSlotInd+1, false)) {
                int invStart = isInPlayerInv ? lockerInvStart : playerInvStart;
                int invEnd = isInPlayerInv ? lockerArmorStart : playerArmorStart;
                if(!mergeItemStack(stackInSlot, invStart, invEnd, true))
                    return null;
            }
            
            if(stackInSlot.stackSize == 0)
                slot.putStack(null);
            else
                slot.onSlotChanged();
            
            if(copyStack.stackSize == stackInSlot.stackSize)
                return null;
            
            slot.onPickupFromSlot(player, stackInSlot);
        }
        return copyStack;
    }
    
    @Override
    public boolean canInteractWith(EntityPlayer player) {
        return tile.isUseableByPlayer(player);
    }

}
