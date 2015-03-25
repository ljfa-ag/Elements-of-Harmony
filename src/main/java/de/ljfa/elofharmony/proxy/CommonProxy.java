package de.ljfa.elofharmony.proxy;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import de.ljfa.elofharmony.challenges.ChallengeContainer;
import de.ljfa.elofharmony.challenges.ChallengeGenerosity;
import de.ljfa.elofharmony.challenges.ChallengeKindness;
import de.ljfa.elofharmony.tile.TileRitualTable;

public class CommonProxy {
    public void preInit(FMLPreInitializationEvent event) {
        
    }
    
    public void init(FMLInitializationEvent event) {
        registerTileEntities();
        
        ChallengeContainer.register(ChallengeGenerosity.class, "Generosity");
        ChallengeContainer.register(ChallengeKindness.class, "Kindness");
    }
    
    public void postInit(FMLPostInitializationEvent event) {
        
    }
    
    protected void registerTileEntities() {
        GameRegistry.registerTileEntity(TileRitualTable.class, "ritual_table");
    }
}
