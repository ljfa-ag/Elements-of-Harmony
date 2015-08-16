package de.ljfa.elofharmony.proxy;

import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import de.ljfa.elofharmony.blocks.BlockRitualTable;
import de.ljfa.elofharmony.blocks.ModBlocks;
import de.ljfa.elofharmony.render.RenderBlockRitualTable;
import de.ljfa.elofharmony.render.TileRitualTableRenderer;
import de.ljfa.elofharmony.tile.TileRitualTable;
import de.ljfa.lib.render.items.TileItemRenderer;

public class ClientProxy extends CommonProxy {
    public void init(FMLInitializationEvent event) {
        super.init(event);
        registerRenderers();
    }
    
    protected void registerRenderers() {
        ClientRegistry.bindTileEntitySpecialRenderer(TileRitualTable.class, new TileRitualTableRenderer());
        
        BlockRitualTable.renderID = RenderingRegistry.getNextAvailableRenderId();
        RenderingRegistry.registerBlockHandler(new RenderBlockRitualTable());
        
        //MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.ritual_table), new TileItemRenderer(new TileRitualTable()));
    }
}
