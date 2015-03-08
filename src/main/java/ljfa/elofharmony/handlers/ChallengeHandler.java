package ljfa.elofharmony.handlers;

import ljfa.elofharmony.challenges.Challenge;
import ljfa.elofharmony.challenges.ChallengeHolder;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.Phase;
import cpw.mods.fml.common.gameevent.TickEvent.PlayerTickEvent;

public class ChallengeHandler {
    private static ChallengeHandler instance = new ChallengeHandler();
    
    public static ChallengeHandler getInstance() {
        return instance;
    }
    
    private ChallengeHandler() { }
    
    @SubscribeEvent(priority = EventPriority.LOW)
    public void onPlayerTick(PlayerTickEvent event) {
        World world = event.player.worldObj;
        if(world.isRemote || event.phase != Phase.START)
            return;
        
        EntityPlayerMP player = (EntityPlayerMP)event.player;
        Challenge ch = getChallenge(player);
        if(ch != null) {
            ch.onTick();
            if(!ch.checkRestriction()) {
                abortChallenge(ch);
            }
            if(world.getWorldTime() % 16 == 0) {
                if(ch.checkCondition()) {
                    endChallenge(ch);
                }
            }
        }
    }
    
    @SubscribeEvent
    public void onItemPickup(EntityItemPickupEvent event) {
        EntityPlayerMP player = (EntityPlayerMP)event.entityPlayer;
        Challenge ch = getChallenge(player);
        if(ch != null && !ch.mayPickUp(event.item.getEntityItem())) {
            event.item.delayBeforeCanPickup = 10;
            event.setCanceled(true);
        }
    }
    
    @SubscribeEvent
    public void onEntityConstruct(EntityConstructing event) {
        if(event.entity instanceof EntityPlayerMP)
            event.entity.registerExtendedProperties("eoh:Challenge", new ChallengeHolder((EntityPlayerMP)event.entity));
    }
    
    @SubscribeEvent
    public void onPlayerClone(PlayerEvent.Clone event) {
        event.entityPlayer.registerExtendedProperties("eoh:challenge", event.original.getExtendedProperties("eoh:challenge"));
    }
    
    public ChallengeHolder getHolder(EntityPlayerMP player) {
        return (ChallengeHolder)player.getExtendedProperties("eoh:Challenge");
    }
    
    public Challenge getChallenge(EntityPlayerMP player) {
        return getHolder(player).getChallenge();
    }
    
    public boolean hasChallengeRunning(EntityPlayerMP player) {
        return getChallenge(player) != null;
    }
    
    public boolean tryStartChallenge(Challenge ch) {
        if(ch.checkStartingCondition()) {
            startChallenge(ch);
            return true;
        } else
            return false;
    }
    
    public boolean tryAbortChallenge(EntityPlayer player) {
        Challenge ch = getChallenge((EntityPlayerMP)player);
        if(ch != null) {
            abortChallenge(ch);
            return true;
        }
        else
            return false;
    }
    
    private void startChallenge(Challenge ch) {
        getHolder(ch.getPlayer()).setChallenge(ch);
        ch.onStart();
    }
    
    private void abortChallenge(Challenge ch) {
        ch.onAbort();
        getHolder(ch.getPlayer()).clearChallenge();
    }
    
    private void endChallenge(Challenge ch) {
        ch.onComplete();
        getHolder(ch.getPlayer()).clearChallenge();
    }
    
}
