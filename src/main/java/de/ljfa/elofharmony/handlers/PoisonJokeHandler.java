package de.ljfa.elofharmony.handlers;

import java.util.Random;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import de.ljfa.elofharmony.Config;
import de.ljfa.lib.math.MathUtils;
import de.ljfa.lib.util.PotionHelper;

/** Event handler for the delayed effects originating from Poison Joke */
public class PoisonJokeHandler {
    /* Extra data about Poison Joke stored in an affected entity:
     * incubation: The beginning of the incubation timer. Is removed when incubation is over.
     * cooldown:   The time when the incubation timer can start again.
     */
    
    /** Marks an entity with an incubation timer */
    public static void startIncubation(World world, Entity entity) {
        if(entity instanceof EntityLivingBase) {
            NBTTagCompound data = entity.getEntityData();
            long worldTimer = world.getTotalWorldTime();
            //If the incubation timer hasn't started yet and the cooldown is over
            if(!data.hasKey("eoh:poisonjoke:incubation")
                    && worldTimer >= data.getLong("eoh:poisonjoke:cooldown")) {
                //Start incubation timer
                data.setLong("eoh:poisonjoke:incubation", worldTimer);
            }
        }
    }
    
    /** Chooses a random effect, strength and duration from those specified in the config
     * and applies it to the entity.
     * @return The duration of the effect
     */
    public int applyRandomEffect(Random rand, EntityLivingBase entity) {
        if(Config.pjNumEffects == 0)
            return 0;
        int index = rand.nextInt(Config.pjNumEffects);
        
        int id = Config.pjPotionIDs[index];
        
        int duration;
        
        if(!Potion.potionTypes[id].isInstant()) {
            int minDuration = (int)(0.4 * Config.pjAvgDurations[index]);
            int maxDuration = (int)(1.6 * Config.pjAvgDurations[index]);
            duration = MathUtils.triangularInt(rand, minDuration, maxDuration);
            if(isSquid(entity))
                duration *= 1.1;
        }
        else
            duration = 1;
        
        int level = rand.nextInt(Config.pjMaxLevels[index]);
        
        PotionHelper.addEffect(entity, id, duration, level);
        return duration;
    }
    
    /** Returns true if the entity is a cool or uncool squid */
    private static boolean isSquid(Entity entity) {
        if(entity instanceof EntitySquid)
            return true;
        else if(entity instanceof EntityPlayer)
            return ((EntityPlayer)entity).getCommandSenderName().toLowerCase().contains("squid");
        else
            return false;
    }
    
    @SubscribeEvent
    public void onLivingUpdateEvent(LivingUpdateEvent event) {
        EntityLivingBase entity = event.entityLiving;
        World world = entity.worldObj;
        long worldTimer = world.getTotalWorldTime();
        if(world.isRemote || (worldTimer & 31) != 0) //Check every 32 ticks
            return;
        else if(Config.pjPlayersOnly && !(entity instanceof EntityPlayer))
            return;
        NBTTagCompound data = entity.getEntityData();
        
        //If incubation time has started and is over
        if(data.hasKey("eoh:poisonjoke:incubation")
                && worldTimer >= data.getLong("eoh:poisonjoke:incubation") + Config.pjIncubationTime) {
            int duration = applyRandomEffect(entity.getRNG(), entity);
            //Next incubation can be applied when current effect is almost over
            data.setLong("eoh:poisonjoke:cooldown", worldTimer + duration - Config.pjIncubationTime);
            data.removeTag("eoh:poisonjoke:incubation");
        }
    }
}
