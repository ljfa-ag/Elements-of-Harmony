package de.ljfa.elofharmony;

import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import de.ljfa.elofharmony.blocks.ModBlocks;
import de.ljfa.elofharmony.challenges.impl.ChallengeCommonHandler;
import de.ljfa.elofharmony.challenges.impl.ChallengeHandler;
import de.ljfa.elofharmony.gui.EohGuiHandler;
import de.ljfa.elofharmony.handlers.PoisonJokeHandler;
import de.ljfa.elofharmony.items.ModItems;
import de.ljfa.elofharmony.proxy.CommonProxy;
import de.ljfa.elofharmony.worldgen.DecorationPoisonJoke;

@Mod(modid = Reference.MODID, name = Reference.MODNAME, version = Reference.VERSION,
    guiFactory = Reference.GUI_FACTORY_CLASS)
public class ElementsOfHarmony {
    @Mod.Instance(Reference.MODID)
    public static ElementsOfHarmony instance;
    
    @SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
    public static CommonProxy proxy;
    
    public static SimpleNetworkWrapper network;
    
    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        Config.loadConfig(event.getSuggestedConfigurationFile());
        ModBlocks.init();
        ModItems.init();
        network = NetworkRegistry.INSTANCE.newSimpleChannel(Reference.MODID);
        NetworkRegistry.INSTANCE.registerGuiHandler(this, new EohGuiHandler());
        proxy.preInit(event);
    }
    
    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        if(Config.pjNumEffects != 0)
            MinecraftForge.EVENT_BUS.register(new PoisonJokeHandler());
        if(Config.pjSpawnChance != 0)
            MinecraftForge.TERRAIN_GEN_BUS.register(new DecorationPoisonJoke());
        
        ChallengeHandler chHandler = new ChallengeHandler();
        FMLCommonHandler.instance().bus().register(chHandler);
        MinecraftForge.EVENT_BUS.register(chHandler);
        MinecraftForge.EVENT_BUS.register(new ChallengeCommonHandler());
        
        ModRecipes.addOredict();
        ModRecipes.addRecipes();
        ModRecipes.addSmelting();
        proxy.init(event);
    }
    
    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit(event);
    }
}
