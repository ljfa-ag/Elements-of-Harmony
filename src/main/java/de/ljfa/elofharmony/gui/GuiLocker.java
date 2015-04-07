package de.ljfa.elofharmony.gui;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;
import de.ljfa.elofharmony.Reference;
import de.ljfa.elofharmony.inventory.ContainerLocker;
import de.ljfa.elofharmony.tile.TileLocker;
import de.ljfa.lib.gui.GuiBase;

public class GuiLocker extends GuiBase {

    private static final ResourceLocation texture = new ResourceLocation(Reference.MODID + ":textures/gui/locker.png");
    
    public GuiLocker(InventoryPlayer invPlayer, TileLocker tile) {
        super(new ContainerLocker(invPlayer, tile), texture);
        xSize = 209;
        ySize = 226;
    }

}
