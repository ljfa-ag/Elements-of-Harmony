package ljfa.elofharmony.handlers;

import java.util.HashMap;
import java.util.Map;

import ljfa.elofharmony.challenges.Challenge;
import ljfa.elofharmony.util.ChatHelper;
import ljfa.elofharmony.util.LogHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import cpw.mods.fml.common.eventhandler.Event.Result;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedOutEvent;
import cpw.mods.fml.common.gameevent.TickEvent.Phase;
import cpw.mods.fml.common.gameevent.TickEvent.PlayerTickEvent;

public class ChallengeHandler {
    private static ChallengeHandler instance = new ChallengeHandler();
    
    public static ChallengeHandler getInstance() {
        return instance;
    }
    
    private final Map<EntityPlayerMP, Challenge> challenges = new HashMap<EntityPlayerMP, Challenge>();
    
    private ChallengeHandler() { }
    
    @SubscribeEvent(priority = EventPriority.LOW)
    public void onPlayerTick(PlayerTickEvent event) {
        World world = event.player.worldObj;
        if(world.isRemote || event.phase != Phase.START)
            return;
        EntityPlayerMP player = (EntityPlayerMP)event.player;

        if(hasChallengeRunning(player)) {
            Challenge ch = challenges.get(player);

            ch.onTick();
            if(!ch.checkRestriction()) {
                abortChallenge(ch);
                ChatHelper.toPlayer(event.player, "You failed the challenge!");
            }
            if((world.getWorldTime() & 15) == 0) {
                if(ch.checkCondition()) {
                    endChallenge(ch);
                    ChatHelper.toPlayer(event.player, "You completed the challenge!");
                }
            }
        }
    }
    
    @SubscribeEvent
    public void onItemPickup(EntityItemPickupEvent event) {
        EntityPlayerMP player = (EntityPlayerMP)event.entityPlayer;
        if(hasChallengeRunning(player)) {
            if(!challenges.get(player).mayPickUp(event.item.getEntityItem())) {
                event.item.delayBeforeCanPickup = 10;
                event.setCanceled(true);
            }
        }
    }
    
    @SubscribeEvent
    public void onPlayerLogout(PlayerLoggedOutEvent event) {
        tryAbortChallenge(event.player);
    }
    
    public Challenge getChallenge(EntityPlayer player) {
        return challenges.get(player);
    }
    
    public boolean hasChallengeRunning(EntityPlayer player) {
        return challenges.containsKey(player);
    }
    
    public boolean tryStartChallenge(Challenge ch) {
        if(ch.checkStartingCondition()) {
            startChallenge(ch);
            ChatHelper.toPlayer(ch.getPlayer(), "The challenge is on");
            return true;
        } else {
            ChatHelper.toPlayer(ch.getPlayer(), "You're not quite ready for the challenge");
            return false;
        }
    }
    
    public void startChallenge(Challenge ch) {
        challenges.put(ch.getPlayer(), ch);
        ch.onStart();
    }
    
    public void tryAbortChallenge(EntityPlayer player) {
        if(hasChallengeRunning(player)) {
            Challenge ch = challenges.get(player);
            abortChallenge(ch);
            ChatHelper.toPlayer(player, "You aborted the challenge!");
        }
    }
    
    public void abortChallenge(Challenge ch) {
        ch.onAbort();
        challenges.remove(ch.getPlayer());
    }
    
    private void endChallenge(Challenge ch) {
        ch.onComplete();
        challenges.remove(ch.getPlayer());
    }

}
