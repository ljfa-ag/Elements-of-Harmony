package de.ljfa.elofharmony.gui;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import de.ljfa.elofharmony.inventory.ContainerLocker;
import de.ljfa.elofharmony.tile.TileLocker;
import de.ljfa.lib.gui.GuiBase;

public class GuiLocker extends GuiBase {

    public GuiLocker(InventoryPlayer invPlayer, TileLocker tile) {
        super(new ContainerLocker(invPlayer, tile));
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_) {
        
    }

}
