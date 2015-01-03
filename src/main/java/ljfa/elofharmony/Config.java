package ljfa.elofharmony;

import java.io.File;

import ljfa.elofharmony.util.LogHelper;
import net.minecraft.potion.Potion;
import net.minecraftforge.common.config.Configuration;
import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class Config {
    public static Configuration conf;
    
    public static final String CAT_GENERAL = "general";
    public static final String CAT_POISONJOKE = "poisonjoke";
    
    public static boolean pjPlayersOnly;
    public static int[] pjPotionIDs;
    public static int[] pjAvgDurations;
    public static int[] pjMaxLevels;
    public static int pjNumEffects;
    public static int pjIncubationTime;
    public static int pjSpawnChance;
    
    public static void loadConfig(File file) {
        if(conf == null)
            conf = new Configuration(file);
        
        conf.load();
        loadValues();
        
        FMLCommonHandler.instance().bus().register(new ChangeHandler());
    }
    
    public static void loadValues() {
        pjPlayersOnly = conf.get(CAT_POISONJOKE, "onlyAffectPlayers", true, "Only players get effects from Poison Joke").getBoolean();
        pjPotionIDs = conf.get(CAT_POISONJOKE, "potionIDs",
                new int[] {Potion.moveSlowdown.id, Potion.digSlowdown.id, Potion.confusion.id,
                Potion.blindness.id, Potion.hunger.id, Potion.weakness.id, Potion.poison.id, Potion.wither.id},
                "List of potion effects that can be applied\n" +
                "Default: Slowness, Mining Fatigue, Nausea, Blindness, Hunger, Weakness, Poison, Wither", 0, 32).getIntList();
        pjAvgDurations = conf.get(CAT_POISONJOKE, "meanDurations",
                new int[] {360, 400, 240, 200, 320, 400, 160, 180},
                "Average durations in ticks of the effect\n" +
                "These entries correspond to the potion IDs specified in the I:potionIDs option.", 0, Integer.MAX_VALUE).getIntList();
        pjMaxLevels = conf.get(CAT_POISONJOKE, "maxLevels",
                new int[] {2, 3, 2, 1, 2, 2, 1, 1},
                "Maximum level of the effects\n" +
                "These entries correspond to the potion IDs specified in the I:potionIDs option.", 1, 5).getIntList();
        pjNumEffects = pjPotionIDs.length;
        if(pjAvgDurations.length != pjNumEffects || pjMaxLevels.length != pjNumEffects) {
            LogHelper.error("The length of the lists for potion IDs, durations and levels should be the same");
            pjNumEffects = Math.min(pjNumEffects, Math.min(pjAvgDurations.length, pjMaxLevels.length));
        }
        
        pjIncubationTime = conf.get(CAT_POISONJOKE, "incubationTime", 100, "Time in ticks between touching the plant and application of the effect").getInt();
        pjSpawnChance = conf.get(CAT_POISONJOKE, "spawnChance", 16, "Chance out of 256 that a chunk will generate Poison Joke", 0, 256).getInt();
   
        if(conf.hasChanged())
            conf.save();
    }
    
    /** Reloads the config values upon change */
    public static class ChangeHandler {
        @SubscribeEvent
        public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
            if(event.modID.equals(Reference.MODID))
                loadValues();
        }
    }
}
