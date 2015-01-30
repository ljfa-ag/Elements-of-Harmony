package ljfa.elofharmony.handlers;

import ljfa.elofharmony.challenges.Challenge;
import ljfa.elofharmony.util.ChatHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class ChallengeHandler {
    @SubscribeEvent
    public void onLivingUpdateEvent(LivingUpdateEvent event) {
        World world = event.entityLiving.worldObj;
        if(world.isRemote || !(event.entityLiving instanceof EntityPlayer))
            return;
        
        EntityPlayer player = (EntityPlayer)event.entityLiving;
        if(player.getEntityData().hasKey("eoh:challenge")) {
            NBTTagCompound tag = player.getEntityData().getCompoundTag("eoh:challenge");
            int chID = tag.getInteger("id");
            Challenge ch = Challenge.fromId(chID);
            
            ch.tick(player, tag);
            if(!ch.checkRestriction(player, tag)) {
                ch.abort(player, tag);
                player.getEntityData().removeTag("eoh:challenge");
                ChatHelper.toPlayer(player, "You failed the challenge!");
            }
            if((world.getWorldTime() & 31) == 0) {
                ChatHelper.toPlayer(player, "The challenge is running");
                if(ch.checkCondition(player, tag)) {
                    ch.complete(player, tag);
                    player.getEntityData().removeTag("eoh:challenge");
                    ChatHelper.toPlayer(player, "You completed the challenge!");
                }
            }
        }
    }
    
    public static boolean isChallengeRunning(EntityPlayer player) {
        return player.getEntityData().hasKey("eoh:challenge");
    }
}
