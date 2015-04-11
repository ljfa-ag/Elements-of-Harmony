package de.ljfa.elofharmony.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import de.ljfa.elofharmony.tile.TileLocker;
import de.ljfa.lib.inventory.ContainerBase;

public class ContainerLocker extends ContainerBase {

    private static final int playerHotbarStart = 0, playerInvStart = 9, playerArmorStart = 36;
    private static final int lockerHotbarStart = 40, lockerInvStart = 49, lockerArmorStart = 76, endSlots = 80;
    
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
        Slot slot = (Slot)inventorySlots.get(slotInd);
        
        if(slot != null && slot.getHasStack()) {
            ItemStack stack = slot.getStack();
            int destSlotInd;
            //The preferred destination is the one at the same position on the other inventory
            if(slotInd < lockerHotbarStart)
                destSlotInd = slotInd + lockerHotbarStart;
            else
                destSlotInd = slotInd - lockerHotbarStart;
            
            if(!mergeItemStack(stack, destSlotInd, destSlotInd+1, false))
                return null;
            
            if(stack.stackSize == 0) {
                slot.putStack(null);
                stack = null;
            }
            else {
                slot.onSlotChanged();
            }
            
            slot.onPickupFromSlot(player, stack);
            return stack;
        }
        return null;
    }
    
    @Override
    public boolean canInteractWith(EntityPlayer player) {
        return tile.isUseableByPlayer(player);
    }

}
