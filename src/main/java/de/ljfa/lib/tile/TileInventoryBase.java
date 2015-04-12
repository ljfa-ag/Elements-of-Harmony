package de.ljfa.lib.tile;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.Constants;

/** Base class for tile entities with inventory */
public abstract class TileInventoryBase extends TileEntity implements IInventory {
    protected ItemStack[] inv;
    
    public TileInventoryBase(int size) {
        inv = new ItemStack[size];
    }
    
    @Override
    public int getSizeInventory() {
        return inv.length;
    }
    
    public ItemStack[] getSlots() {
        return inv;
    }

    @Override
    public ItemStack getStackInSlot(int slot) {
        return inv[slot];
    }

    @Override
    public ItemStack decrStackSize(int slot, int num) {
        ItemStack stack = getStackInSlot(slot);
        if(stack != null) {
            if(stack.stackSize <= num)
                setInventorySlotContents(slot, null);
            else {
                stack = stack.splitStack(num);
                if(stack.stackSize == 0)
                    setInventorySlotContents(slot, null);
            }
        }
        return stack;
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int slot) {
        ItemStack stack = getStackInSlot(slot);
        if(stack != null)
            setInventorySlotContents(slot, null);
        return stack;
    }

    @Override
    public void setInventorySlotContents(int slot, ItemStack stack) {
        inv[slot] = stack;
        worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
        if(stack != null && stack.stackSize > getInventoryStackLimit())
            stack.stackSize = getInventoryStackLimit();
    }

    @Override
    public abstract String getInventoryName();

    @Override
    public boolean hasCustomInventoryName() {
        return false;
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer player) {
        return worldObj.getTileEntity(xCoord, yCoord, zCoord) == this
                && player.getDistanceSq(xCoord + 0.5, yCoord + 0.5, zCoord + 0.5) < 64;
    }

    @Override
    public void openInventory() {}

    @Override
    public void closeInventory() {}

    @Override
    public boolean isItemValidForSlot(int slot, ItemStack stack) {
        return true;
    }
    
    public void writeCustomNBT(NBTTagCompound tag) {
        NBTTagList invList = new NBTTagList();
        for(int i = 0; i < inv.length; i++) {
            if(inv[i] != null) { 
                NBTTagCompound stackTag = new NBTTagCompound();
                stackTag.setByte("Slot", (byte)i);
                inv[i].writeToNBT(stackTag);
                invList.appendTag(stackTag);
            }
        }
        
        tag.setTag("Inventory", invList);
    }

    public void readCustomNBT(NBTTagCompound tag) {
        clear();
        NBTTagList invList = tag.getTagList("Inventory", Constants.NBT.TAG_COMPOUND);
        for(int i = 0; i < invList.tagCount(); i++) {
            NBTTagCompound stackTag = invList.getCompoundTagAt(i);
            int slot = stackTag.getByte("Slot");
            
            if(slot >= 0 && slot < inv.length)
                inv[slot] = ItemStack.loadItemStackFromNBT(stackTag);
        }
    }
    
    @Override
    public void writeToNBT(NBTTagCompound tag) {
        super.writeToNBT(tag);
        writeCustomNBT(tag);
    }
    
    @Override
    public void readFromNBT(NBTTagCompound tag) {
        super.readFromNBT(tag);
        readCustomNBT(tag);
    }
    
    public void spillItems() {
        for(int i = 0; i < inv.length; i++) {
            ItemStack stack = getStackInSlot(i);
            if(stack != null) {
                EntityItem entity = new EntityItem(worldObj, xCoord + 0.5, yCoord + 0.5, zCoord + 0.5, stack);
                worldObj.spawnEntityInWorld(entity);
            }
        }
    }
    
    public void clear() {
        inv = new ItemStack[inv.length];
    }
}
