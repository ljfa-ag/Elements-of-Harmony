package de.ljfa.lib.gui;

import java.util.Objects;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.config.GuiButtonExt;

public class GuiButtonPicture extends GuiButtonExt {

    protected final ResourceLocation picture;
    
    public GuiButtonPicture(int id, int xPos, int yPos, int width, int height, String displayString, ResourceLocation picture) {
        super(id, xPos, yPos, width, height, displayString);
        this.picture = Objects.requireNonNull(picture);
    }
    
    public GuiButtonPicture(int id, int xPos, int yPos, int width, int height, ResourceLocation picture) {
        this(id, xPos, yPos, width, height, "", picture);
    }
    
    @Override
    public void drawButton(Minecraft mc, int mouseX, int mouseY) {
        if(this.visible) {
            super.drawButton(mc, mouseX, mouseY); //TODO: Maybe implement that ourselves
            mc.getTextureManager().bindTexture(picture);
            drawTexturedModalRect(xPosition, yPosition, 0, 0, width, height);
        }
    }

}
