package de.ljfa.lib.tile;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IChatComponent;
import net.minecraftforge.common.util.Constants;
import de.ljfa.lib.items.ItemHelper;
import de.ljfa.lib.nbt.NBTCompoundListWrapper;

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
        worldObj.markBlockForUpdate(pos);
        if(stack != null && stack.stackSize > getInventoryStackLimit())
            stack.stackSize = getInventoryStackLimit();
    }

    @Override
    public abstract String getName();
    
    @Override
    public IChatComponent getDisplayName() {
        return hasCustomName()
                ? new ChatComponentText(getName())
                : new ChatComponentTranslation(getName());
    }

    @Override
    public boolean hasCustomName() {
        return false;
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer player) {
        return worldObj.getTileEntity(pos) == this
                && pos.distanceSqToCenter(player.posX, player.posY, player.posZ) < 64;
    }

    @Override
    public void openInventory(EntityPlayer player) {}

    @Override
    public void closeInventory(EntityPlayer player) {}

    @Override
    public boolean isItemValidForSlot(int slot, ItemStack stack) {
        return true;
    }
    
    @Override
    public int getField(int id) {
        return 0;
    }
    
    @Override
    public void setField(int id, int value) {
    }
    
    @Override
    public int getFieldCount() {
        return 0;
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
        for(NBTTagCompound stackTag: new NBTCompoundListWrapper(invList)) {
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
            if(stack != null)
                ItemHelper.dropItem(worldObj, pos, stack);
        }
    }
    
    public void clear() {
        inv = new ItemStack[inv.length];
    }
}
