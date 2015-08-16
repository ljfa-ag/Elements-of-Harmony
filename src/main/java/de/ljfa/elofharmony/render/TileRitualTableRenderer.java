package de.ljfa.elofharmony.render;

import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.tileentity.TileEntity;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import de.ljfa.elofharmony.render.models.ModelRitualTable;
import de.ljfa.elofharmony.tile.TileRitualTable;

@SideOnly(Side.CLIENT)
public class TileRitualTableRenderer extends TileEntitySpecialRenderer {
    private final ModelRitualTable model;
    private final RenderItem renderItem;
    
    private final EntityItem shownItem;
    
    //private static final ResourceLocation texture = new ResourceLocation(Reference.MODID, "textures/tile/ritual_table.png");
    //private static final ResourceLocation texture_running = new ResourceLocation(Reference.MODID, "textures/tile/ritual_table_running.png");
    
    public TileRitualTableRenderer() {
        model = new ModelRitualTable();
        renderItem = new RenderItem();
        renderItem.setRenderManager(RenderManager.instance);
        
        shownItem = new EntityItem(null);
        shownItem.hoverStart = 0.0f;
    }
    
    @Override
    public void renderTileEntityAt(TileEntity te, double x, double y, double z, float partialTick) {
        TileRitualTable tile = (TileRitualTable)te;
        
        GL11.glPushMatrix();
        GL11.glTranslated(x, y, z);
        
        /*if(tile.hasChallenge())
            bindTexture(texture_running);
        else
            bindTexture(texture);
        
        model.renderAll();*/
        
        if(tile.getStackInSlot(0) != null) {
            GL11.glTranslated(0.5, 0.63, 0.5);
            shownItem.setEntityItemStack(tile.getStackInSlot(0));
            renderItem.doRender(shownItem, 0, 0, 0, 0, 0);
        }
        
        GL11.glPopMatrix();
    }

}
