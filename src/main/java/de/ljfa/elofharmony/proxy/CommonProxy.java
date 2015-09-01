package de.ljfa.elofharmony.proxy;

import de.ljfa.elofharmony.Config;
import de.ljfa.elofharmony.ElementsOfHarmony;
import de.ljfa.elofharmony.ModRecipes;
import de.ljfa.elofharmony.Reference;
import de.ljfa.elofharmony.blocks.ModBlocks;
import de.ljfa.elofharmony.challenges.ChallengeGenerosity;
import de.ljfa.elofharmony.challenges.ChallengeKindness;
import de.ljfa.elofharmony.challenges.ChallengeLaughter;
import de.ljfa.elofharmony.challenges.impl.ChallengeContainer;
import de.ljfa.elofharmony.challenges.impl.ChallengeHandler;
import de.ljfa.elofharmony.challenges.impl.ChallengeProxyHandler;
import de.ljfa.elofharmony.command.CommandChallengeDebug;
import de.ljfa.elofharmony.gui.EohGuiHandler;
import de.ljfa.elofharmony.handlers.PoisonJokeHandler;
import de.ljfa.elofharmony.items.ModItems;
import de.ljfa.elofharmony.network.MessageLocker;
import de.ljfa.elofharmony.tile.TileLocker;
import de.ljfa.elofharmony.tile.TileRitualTable;
import de.ljfa.elofharmony.worldgen.DecorationPoisonJoke;
import de.ljfa.lib.network.DescriptionPacketHandler;
import net.minecraft.command.ServerCommandManager;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartedEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;

public class CommonProxy {
    public void preInit(FMLPreInitializationEvent event) {
        ElementsOfHarmony.network = NetworkRegistry.INSTANCE.newSimpleChannel(Reference.MODID);
        ElementsOfHarmony.network.registerMessage(MessageLocker.class, MessageLocker.class, 0, Side.SERVER);
        NetworkRegistry.INSTANCE.registerGuiHandler(ElementsOfHarmony.instance, new EohGuiHandler());
        ElementsOfHarmony.descHandler = new DescriptionPacketHandler(Reference.MODID + "Desc");
        ElementsOfHarmony.descHandler.preInit();
        ModBlocks.preInit();
        ModItems.preInit();
    }
    
    public void init(FMLInitializationEvent event) {
        if(Config.pjNumEffects != 0)
            MinecraftForge.EVENT_BUS.register(new PoisonJokeHandler());
        if(Config.pjSpawnChance != 0)
            MinecraftForge.TERRAIN_GEN_BUS.register(new DecorationPoisonJoke());
        
        ChallengeHandler chHandler = new ChallengeHandler();
        FMLCommonHandler.instance().bus().register(chHandler);
        MinecraftForge.EVENT_BUS.register(chHandler);
        MinecraftForge.EVENT_BUS.register(new ChallengeProxyHandler());
        
        ModRecipes.init();
        registerTileEntities();
        
        ChallengeContainer.register(ChallengeGenerosity.class, "Generosity");
        ChallengeContainer.register(ChallengeKindness.class, "Kindness");
        ChallengeContainer.register(ChallengeLaughter.class, "Laughter");
    }
    
    public void postInit(FMLPostInitializationEvent event) {
        
    }
    
    public void serverStarted(FMLServerStartedEvent event) {
        ServerCommandManager commandManager = (ServerCommandManager)MinecraftServer.getServer().getCommandManager();
        commandManager.registerCommand(new CommandChallengeDebug());
    }
    
    protected void registerTileEntities() {
        GameRegistry.registerTileEntity(TileRitualTable.class, "ritual_table");
        GameRegistry.registerTileEntity(TileLocker.class, "locker");
    }
}
