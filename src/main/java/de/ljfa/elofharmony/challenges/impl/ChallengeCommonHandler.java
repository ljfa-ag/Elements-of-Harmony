package de.ljfa.elofharmony.challenges.impl;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.world.BlockEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import de.ljfa.elofharmony.challenges.Challenge;

//TODO: A more flexible solution for challenge event handling
public class ChallengeCommonHandler {

    @SubscribeEvent
    public void onLivingHurt(LivingHurtEvent event) {
        if(event.entity instanceof EntityPlayerMP) {
            Challenge ch = ChallengeHandler.getChallenge((EntityPlayerMP)event.entity);
            if(ch != null)
                ch.onPlayerHurt(event);
        }
    }
    
    @SubscribeEvent
    public void onLivingDeath(LivingDeathEvent event) {
        if(event.entity instanceof EntityPlayerMP) {
            Challenge ch = ChallengeHandler.getChallenge((EntityPlayerMP)event.entity);
            if(ch != null)
                ch.onPlayerDeath(event);
        }
    }
    
    @SubscribeEvent
    public void onBlockBreak(BlockEvent.BreakEvent event) {
        if(event.getPlayer() instanceof EntityPlayerMP) {
            Challenge ch = ChallengeHandler.getChallenge((EntityPlayerMP)event.getPlayer());
            if(ch != null)
                ch.onPlayerBreakBlock(event);
        }
    }

}
