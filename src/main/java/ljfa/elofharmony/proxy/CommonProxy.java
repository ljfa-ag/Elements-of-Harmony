package ljfa.elofharmony.proxy;

import ljfa.elofharmony.tile.*;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;

public class CommonProxy {
    public void preInit(FMLPreInitializationEvent event) {
        
    }
    
    public void init(FMLInitializationEvent event) {
        registerTileEntities();
    }
    
    public void postInit(FMLPostInitializationEvent event) {
        
    }
    
    protected void registerTileEntities() {
        GameRegistry.registerTileEntity(TileRitualTable.class, "ritual_table");
    }
}
