package ljfa.elofharmony.proxy;

import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;
import ljfa.elofharmony.blocks.ModBlocks;
import ljfa.elofharmony.render.blocks.TileRitualTableRenderer;
import ljfa.elofharmony.render.items.ItemRendererRitualTable;
import ljfa.elofharmony.tile.TileRitualTable;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.event.FMLInitializationEvent;

public class ClientProxy extends CommonProxy {
    public void init(FMLInitializationEvent event) {
        super.init(event);
        registerRenderers();
    }
    
    protected void registerRenderers() {
        ClientRegistry.bindTileEntitySpecialRenderer(TileRitualTable.class, new TileRitualTableRenderer());
        
        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.ritual_table), new ItemRendererRitualTable());
    }
}
