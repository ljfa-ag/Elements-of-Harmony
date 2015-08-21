package de.ljfa.elofharmony.challenges.impl;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;
import de.ljfa.elofharmony.challenges.Challenge;
import de.ljfa.lib.util.ChatHelper;

public class ChallengeHandler {

    @SubscribeEvent
    public void onPlayerTick(PlayerTickEvent event) {
        World world = event.player.worldObj;
        if(world.isRemote || event.phase != Phase.START)
            return;
        
        EntityPlayerMP player = (EntityPlayerMP)event.player;
        Challenge ch = getChallenge(player);
        if(ch != null) {
            ch.onTick();
            if(!ch.checkRestriction()) {
                ChallengeHandler.failChallenge(ch);
            }
            if(ch.checkCondition()) {
                ChallengeHandler.finishChallenge(ch);
            }
        }
    }
    
    @SubscribeEvent
    public void onItemPickup(EntityItemPickupEvent event) {
        EntityPlayerMP player = (EntityPlayerMP)event.entityPlayer;
        Challenge ch = getChallenge(player);
        if(ch != null && !ch.mayPickUp(event.item.getEntityItem())) {
            event.item.setPickupDelay(10);
            event.setCanceled(true);
        }
    }
    
    @SubscribeEvent
    public void onEntityConstruct(EntityConstructing event) {
        if(event.entity instanceof EntityPlayerMP)
            ChallengeContainer.initPlayer((EntityPlayerMP)event.entity);
    }
    
    @SubscribeEvent
    public void onPlayerClone(PlayerEvent.Clone event) {
        if(event.wasDeath) {
            ChallengeContainer oldCont = ChallengeContainer.get((EntityPlayerMP)event.original);
            Challenge ch = oldCont.getChallenge();
            if(ch != null) {
                ChallengeContainer newCont = ChallengeContainer.get((EntityPlayerMP)event.entityPlayer);
                newCont.setChallenge(ch);
                newCont.init(event.entityPlayer, event.entityPlayer.worldObj);
                oldCont.clearChallenge();
            }
        }
    }
    
    //TODO: Put these methods somewhere else, e.g. ChallengeContainer
    public static boolean tryStartChallenge(Challenge ch) {
        if(hasChallenge(ch.getPlayer())) {
            ChatHelper.toPlayerLoc(ch.getPlayer(), "elofharmony.challenge.already_have");
            return false;
        }
        if(ch.checkStartingCondition()) {
            startChallenge(ch);
            return true;
        }
        else
            return false;
    }
    
    public static boolean tryAbortChallenge(EntityPlayer player) {
        Challenge ch = getChallenge((EntityPlayerMP)player);
        if(ch != null) {
            abortChallenge(ch);
            return true;
        }
        else
            return false;
    }
    
    public static Challenge getChallenge(EntityPlayerMP player) {
        return ChallengeContainer.get(player).getChallenge();
    }
    
    public static boolean hasChallenge(EntityPlayerMP player) {
        return getChallenge(player) != null;
    }
    
    private static void startChallenge(Challenge ch) {
        ChallengeContainer.get(ch.getPlayer()).setChallenge(ch);
        ch.onStart();
    }
    
    private static void abortChallenge(Challenge ch) {
        ch.onAbort();
        ChallengeContainer.get(ch.getPlayer()).clearChallenge();
    }
    private static void failChallenge(Challenge ch) {
        ch.onFail();
        ChallengeContainer.get(ch.getPlayer()).clearChallenge();
    }
    
    private static void finishChallenge(Challenge ch) {
        ch.onComplete();
        ChallengeContainer.get(ch.getPlayer()).clearChallenge();
    }
}
