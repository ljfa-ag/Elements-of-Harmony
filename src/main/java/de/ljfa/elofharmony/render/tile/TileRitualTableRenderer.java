package de.ljfa.elofharmony.render.tile;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import de.ljfa.elofharmony.Reference;
import de.ljfa.elofharmony.blocks.ModBlocks;
import de.ljfa.elofharmony.render.models.ModelRitualTable;
import de.ljfa.elofharmony.tile.TileRitualTable;
import de.ljfa.elofharmony.util.LogHelper;

@SideOnly(Side.CLIENT)
public class TileRitualTableRenderer extends TileEntitySpecialRenderer {
    private final ModelRitualTable model;
    private final RenderItem renderItem;
    
    private static final ResourceLocation texture = new ResourceLocation(Reference.MODID + ":textures/tile/ritual_table.png");
    private static final ResourceLocation texture_running = new ResourceLocation(Reference.MODID + ":textures/tile/ritual_table_running.png");
    
    public TileRitualTableRenderer() {
        model = new ModelRitualTable();
        renderItem = new RenderItem();
        renderItem.setRenderManager(RenderManager.instance);
    }
    
    @Override
    public void renderTileEntityAt(TileEntity te, double x, double y, double z, float partialTick) {
        TileRitualTable tile = (TileRitualTable)te;
        
        GL11.glPushMatrix();
        GL11.glTranslated(x, y, z);
        
        if(tile.hasChallenge())
            bindTexture(texture_running);
        else
            bindTexture(texture);
        
        model.renderAll();
        
        if(tile.getStackInSlot(0) != null) {
            World world = tile.getWorldObj();
            GL11.glTranslated(0.5, 0.63, 0.5);
            EntityItem shownItem = new EntityItem(world);
            shownItem.setEntityItemStack(tile.getStackInSlot(0));
            shownItem.hoverStart = 0.0f;
            renderItem.doRender(shownItem, 0, 0, 0, 0, 0);
        }
        
        GL11.glPopMatrix();
    }

}
