package de.ljfa.lib.nbt;

import java.util.Iterator;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

/** A wrapper for NBTTagList of TagCompounds which implements Iterable */
public class NBTCompoundListWrapper implements Iterable<NBTTagCompound> {

    public final NBTTagList list;
    
    public NBTCompoundListWrapper(NBTTagList list) {
        this.list = list;
    }

    @Override
    public Iterator<NBTTagCompound> iterator() {
        return this.new ListIterator();
    }
    
    private class ListIterator implements Iterator<NBTTagCompound> {
        private int index = 0;
        
        @Override
        public boolean hasNext() {
            return index < list.tagCount();
        }

        @Override
        public NBTTagCompound next() {
            return list.getCompoundTagAt(index++);
        }
    }

}
