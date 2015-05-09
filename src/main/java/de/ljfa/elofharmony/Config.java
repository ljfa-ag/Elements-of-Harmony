package de.ljfa.elofharmony;

import java.io.File;

import net.minecraft.potion.Potion;
import net.minecraftforge.common.config.Configuration;
import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import de.ljfa.lib.exception.InvalidConfigValueException;

public class Config {
    public static Configuration conf;
    
    public static final String CAT_GENERAL = "general";
    public static final String CAT_POISONJOKE = "poisonjoke";
    
    public static boolean pjPlayersOnly;
    public static int[] pjPotionIDs; //TODO: This maybe shouldn't be configurable
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
        pjPlayersOnly = conf.get(CAT_POISONJOKE, "Only affect players", true, "Only players get effects from Poison Joke").getBoolean();
        pjPotionIDs = conf.get(CAT_POISONJOKE, "Applicable potion effects",
                new int[] {Potion.moveSlowdown.id, Potion.digSlowdown.id, Potion.harm.id, Potion.confusion.id,
                Potion.blindness.id, Potion.hunger.id, Potion.weakness.id, Potion.poison.id, Potion.wither.id},
                "List of potion effects that can be applied\n" +
                "Default: Slowness, Mining Fatigue, Harm, Nausea, Blindness, Hunger, Weakness, Poison, Wither", 0, 32).getIntList();
        pjAvgDurations = conf.get(CAT_POISONJOKE, "Mean effect durations",
                new int[] {360, 400, 1, 240, 200, 320, 400, 160, 180},
                "Average durations in ticks of the effect\n" +
                "These entries correspond to the potion IDs specified above.", 0, Integer.MAX_VALUE).getIntList();
        pjMaxLevels = conf.get(CAT_POISONJOKE, "Maximum effect levels",
                new int[] {2, 3, 2, 2, 1, 2, 2, 1, 1},
                "Maximum level of the effects\n" +
                "These entries correspond to the potion IDs specified above.", 1, 5).getIntList();
        pjNumEffects = pjPotionIDs.length;
        if(pjAvgDurations.length != pjNumEffects || pjMaxLevels.length != pjNumEffects) {
            //The exception can be caught, as is the case when using config GUIs
            pjNumEffects = Math.min(pjNumEffects, Math.min(pjAvgDurations.length, pjMaxLevels.length));
            throw new InvalidConfigValueException("The length of the lists for potion IDs, durations and levels must be the same");
        }
        
        pjIncubationTime = conf.get(CAT_POISONJOKE, "Incubation time", 100, "Time in ticks between touching the plant and application of the effect").getInt();
        pjSpawnChance = conf.get(CAT_POISONJOKE, "Spawn chance", 16, "Chance out of 256 that a chunk will generate Poison Joke", 0, 256).getInt();
   
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
