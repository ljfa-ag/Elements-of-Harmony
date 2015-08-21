package de.ljfa.elofharmony.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import de.ljfa.elofharmony.tile.TileLocker;

public class EohGuiHandler implements IGuiHandler {

    public enum GuiIDs {
        locker
    }
    
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity tile = world.getTileEntity(new BlockPos(x, y, z));
        switch(GuiIDs.values()[ID]) {
        case locker:
            return new ContainerLocker(player.inventory, (TileLocker)tile);
        }
        throw new IllegalArgumentException("Invalid GUI ID: " + ID);
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity tile = world.getTileEntity(new BlockPos(x, y, z));
        switch(GuiIDs.values()[ID]) {
        case locker:
            return new GuiLocker(player.inventory, (TileLocker)tile);
        }
        throw new IllegalArgumentException("Invalid GUI ID: " + ID);
    }

}
