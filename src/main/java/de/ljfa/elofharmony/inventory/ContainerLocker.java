package de.ljfa.elofharmony.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import de.ljfa.elofharmony.tile.TileLocker;
import de.ljfa.lib.gui.ContainerBase;
import de.ljfa.lib.gui.SlotArmor;

public class ContainerLocker extends ContainerBase {

    private static final int lockerInvStart = 0, lockerHotbarStart = 27, lockerArmorStart = 36;
    private static final int playerInvStart = 40, playerHotbarStart = 67, playerArmorStart = 76, endSlots = 80;
    
    private final TileLocker tile;
    
    public ContainerLocker(InventoryPlayer invPlayer, TileLocker tile) {
        this.tile = tile;
        
        //Locker inventory slots
        for(int i = 0; i < 3; i++)
            for(int j = 0; j < 9; j++)
                addSlotToContainer(new Slot(tile, 9 + j + 9*i, 30 + 18*j, 18 + 18*i));
        
        //Locker hotbar slots
        for(int j = 0; j < 9; j++)
            addSlotToContainer(new Slot(tile, j, 30 + 18*j, 76));
        
        //Locker armor slots
        for(int i = 0; i < 4; i++)
            addSlotToContainer(new SlotArmor(tile, 36 + i, 8, 20 + 18*i, i));
        
        //Player inventory and hotbar slots
        addPlayerInv(invPlayer, 30, 112);
        
        //Player armor slots
        for(int i = 0; i < 4; i++)
            addSlotToContainer(new SlotArmor(invPlayer, 39 - i, 8, 114 + 18*i, i));
    }
    
    @Override
    protected boolean doTransferStack(ItemStack stackInSlot, int slotInd) {
        boolean isInLockerInv = slotInd < playerInvStart;
        //Try to transfer into the corresponding slot on the other side first
        int corrSlotInd = slotInd + (isInLockerInv ? playerInvStart : -playerInvStart);
        if(!mergeItemStack(stackInSlot, corrSlotInd, corrSlotInd+1, false)) {
            //Try to transfer into the rest of the inventory
            int invStart = isInLockerInv ? playerInvStart : lockerInvStart;
            int invEnd = isInLockerInv ? playerArmorStart : lockerArmorStart;
            if(!mergeItemStack(stackInSlot, invStart, invEnd, true))
                return false;
        }
        return true;
    }
    
    @Override
    public boolean canInteractWith(EntityPlayer player) {
        return tile.isUseableByPlayer(player);
    }
    
    public void swapWithPlayer() {
        for(int i = 0; i < playerInvStart; i++)
            swapContent(getSlot(i), getSlot(i + playerInvStart));
    }
    
    private void swapContent(Slot first, Slot second) {
        ItemStack tmp = first.getStack();
        first.putStack(second.getStack());
        second.putStack(tmp);
    }

}
