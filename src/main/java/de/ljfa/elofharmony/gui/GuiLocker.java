package de.ljfa.elofharmony.gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import de.ljfa.elofharmony.ElementsOfHarmony;
import de.ljfa.elofharmony.Reference;
import de.ljfa.elofharmony.network.MessageLocker;
import de.ljfa.elofharmony.tile.TileLocker;
import de.ljfa.lib.gui.GuiBase;
import de.ljfa.lib.gui.GuiButtonPicture;

public class GuiLocker extends GuiBase {

    private static final ResourceLocation texture = new ResourceLocation(Reference.MODID, "textures/gui/locker.png");
    private static final ResourceLocation buttonSwapPic = new ResourceLocation(Reference.MODID, "textures/gui/swap_button.png");
    
    public GuiLocker(InventoryPlayer invPlayer, TileLocker tile) {
        super(new ContainerLocker(invPlayer, tile), texture);
        xSize = 198;
        ySize = 194;
    }
    
    @Override
    public void initGui() {
        super.initGui();
        buttonList.add(new GuiButtonPicture(0, guiLeft+(xSize-16)/2, guiTop+94, 16, 16, buttonSwapPic));
    }
    
    @Override
    protected void actionPerformed(GuiButton button) {
        if(button.id == 0) {
            ElementsOfHarmony.network.sendToServer(new MessageLocker());
            ((ContainerLocker)inventorySlots).swapWithPlayer();
        }
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        String name = I18n.format("elofharmony.container.locker");
        this.fontRendererObj.drawString(name, 30, 6, 0x404040);
        this.fontRendererObj.drawString(I18n.format("container.inventory"), 30, 101, 0x404040);
    }
}
