package ljfa.elofharmony.render.items;

import ljfa.elofharmony.tile.TileRitualTable;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ItemRendererRitualTable implements IItemRenderer {

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

    private final TileRitualTable dummyTile = new TileRitualTable();
}
