package de.ljfa.elofharmony.render;

import org.lwjgl.opengl.GL11;

import de.ljfa.elofharmony.tile.TileRitualTable;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class TileRitualTableRenderer extends TileEntitySpecialRenderer {
    private final RenderItem renderItem;
    
    public TileRitualTableRenderer() {
        renderItem = Minecraft.getMinecraft().getRenderItem();
    }
    
    //destroyProgress: Ranges from 0 to 9 when the block gets dug, negative otherwise
    @Override
    public void renderTileEntityAt(TileEntity te, double x, double y, double z, float partialTick, int destroyProgress) {
        ItemStack stack = ((TileRitualTable)te).getStackInSlot(0);
        if(stack != null) {
            GL11.glPushMatrix();
            GL11.glTranslated(x+0.5, y+0.75, z+0.5);
            GL11.glScalef(0.5f, 0.5f, 0.5f);
            renderItem.renderItemModel(stack);
            GL11.glPopMatrix();
        }
    }

}
