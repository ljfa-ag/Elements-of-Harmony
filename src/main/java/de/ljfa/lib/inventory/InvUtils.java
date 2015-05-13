package de.ljfa.lib.inventory;

import de.ljfa.lib.tile.TileInventoryBase;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class InvUtils {
    /**
     * If the specified slot in the inventory is empty, takes one item of what the player holds in their hand
     * and puts it into the slot. Otherwise, retrieves the slot's item into the player's inventory.
     * @param playerInv the player's inventory
     * @param inv the target inventory
     * @param slot the target slot
     * @return true if something was transferred
     */
    public static boolean transferFromToHand(InventoryPlayer playerInv, IInventory inv, int slot) {
        int playerSlot = playerInv.currentItem;
        ItemStack stack = inv.getStackInSlot(0);
        if(stack != null) {
            //Remove item from inventory
            if(playerInv.addItemStackToInventory(stack)) {
                inv.setInventorySlotContents(0, null);
                return true;
            }
            else
                return false;
        }
        else {
            //Put item into inventory
            if(inv.isItemValidForSlot(0, playerInv.getCurrentItem())) {
                ItemStack playerSplitStack = playerInv.decrStackSize(playerSlot, 1);
                inv.setInventorySlotContents(0, playerSplitStack);
                return playerSplitStack != null;
            }
            else
                return false;
        }
    }

    public static void spillInventory(World world, int x, int y, int z) {
        TileEntity tile = world.getTileEntity(x, y, z);
        if(tile instanceof TileInventoryBase)
            ((TileInventoryBase)tile).spillItems();
    }

}
