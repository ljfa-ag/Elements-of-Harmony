package de.ljfa.elofharmony.render;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import de.ljfa.elofharmony.blocks.BlockRitualTable;
import de.ljfa.elofharmony.blocks.ModBlocks;
import de.ljfa.elofharmony.tile.TileRitualTable;

@SideOnly(Side.CLIENT)
public class RenderBlockRitualTable implements ISimpleBlockRenderingHandler {
    
    @Override
    public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {
        renderer.renderBlockAsItem(ModBlocks.slab_flutter, 0, 1.0f); //FIXME
    }

    @Override
    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
        boolean isRunning = false;
        TileEntity te = world.getTileEntity(x, y, z);
        if(te instanceof TileRitualTable)
            isRunning = ((TileRitualTable)te).hasChallenge();
        
        if(isRunning)
            renderer.overrideBlockTexture = ModBlocks.ritual_table.getIconRunning();
        renderer.overrideBlockBounds(0, 0, 0, 1, 0.5, 1);
        boolean ret = renderer.renderStandardBlock(block, x, y, z);
        renderer.unlockBlockBounds();
        if(isRunning)
            renderer.overrideBlockTexture = null;
        return ret;
    }

    @Override
    public boolean shouldRender3DInInventory(int modelId) {
        return true;
    }

    @Override
    public int getRenderId() {
        return BlockRitualTable.renderID;
    }

}
