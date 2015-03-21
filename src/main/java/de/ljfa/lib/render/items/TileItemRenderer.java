package de.ljfa.lib.render.items;

import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.IItemRenderer;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class TileItemRenderer implements IItemRenderer {

    public TileItemRenderer(TileEntity dummyTile) {
        this.dummyTile = dummyTile;
    }
    
    @Override
    public boolean handleRenderType(ItemStack item, ItemRenderType type) {
        return true;
    }

    @Override
    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
        return true;
    }

    @Override
    public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
        double x = 0, y = 0, z = 0;
        if(type == ItemRenderType.ENTITY) {
            x = -0.5;
            z = -0.5;
        }
        TileEntityRendererDispatcher.instance.renderTileEntityAt(dummyTile, x, y, z, 0);
    }

    public TileEntity getDummyTile() {
        return dummyTile;
    }

    private final TileEntity dummyTile;
}