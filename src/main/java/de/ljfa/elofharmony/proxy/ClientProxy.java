package de.ljfa.elofharmony.proxy;

import de.ljfa.elofharmony.blocks.ModBlocks;
import de.ljfa.elofharmony.items.ModItems;
import de.ljfa.elofharmony.render.TileRitualTableRenderer;
import de.ljfa.elofharmony.tile.TileRitualTable;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy {
    @Override
    public void preInit(FMLPreInitializationEvent event) {
        super.preInit(event);
    }
    
    @Override
    public void init(FMLInitializationEvent event) {
        super.init(event);
        ItemModelMesher mesher = Minecraft.getMinecraft().getRenderItem().getItemModelMesher();
        ModBlocks.registerItemModels(mesher);
        ModItems.registerItemModels(mesher);
        registerRenderers();
    }
    
    protected void registerRenderers() {
        ClientRegistry.bindTileEntitySpecialRenderer(TileRitualTable.class, new TileRitualTableRenderer());
        
        //MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.ritual_table), new TileItemRenderer(new TileRitualTable()));
    }
}
