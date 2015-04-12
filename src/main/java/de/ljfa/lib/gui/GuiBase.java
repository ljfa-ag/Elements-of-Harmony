package de.ljfa.lib.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

/** Base class for GUIs with containers */
public abstract class GuiBase extends GuiContainer {

    protected final ResourceLocation texture;
    
    public GuiBase(Container cont, ResourceLocation texture) {
        super(cont);
        this.texture = texture;
    }
    
    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTick, int mouseX, int mouseY) {
        mc.getTextureManager().bindTexture(texture);
        GL11.glColor3ub((byte)255, (byte)255, (byte)255);
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
    }

}
