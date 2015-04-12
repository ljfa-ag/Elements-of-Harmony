package de.ljfa.elofharmony.gui;

import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
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

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        String name = "Locker";
        this.fontRendererObj.drawString(name, 30, 6, 0x404040);
        this.fontRendererObj.drawString(I18n.format("container.inventory"), 30, 97, 0x404040);
    }
}