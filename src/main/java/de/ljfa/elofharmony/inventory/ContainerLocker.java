package de.ljfa.elofharmony.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import de.ljfa.elofharmony.tile.TileLocker;
import de.ljfa.lib.inventory.ContainerBase;

public class ContainerLocker extends ContainerBase {

    private final TileLocker tile;
    
    public ContainerLocker(InventoryPlayer invPlayer, TileLocker tile) {
        this.tile = tile;
        
        addPlayerInv(invPlayer, 8, 84);
    }
    
    @Override
    public boolean canInteractWith(EntityPlayer player) {
        return tile.isUseableByPlayer(player);
    }

}
