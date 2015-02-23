package ljfa.elofharmony.challenges;

import ljfa.elofharmony.tile.TileRitualTable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public abstract class Challenge {
    protected final EntityPlayerMP player;
    
    public Challenge(EntityPlayerMP player) {
        this.player = player;
    }
    
    /** @return if the challenge is ready to be started on the player */
    public abstract boolean checkStartingCondition();
    
    /** @return true if the challenge's restriction is met */
    public abstract boolean checkRestriction();
    
    /** @return true if the challenge's completion condition is met */
    public abstract boolean checkCondition();
    
    /** Called when a challenge is about to start for the player. */
    public abstract void onStart();
    
    /** Called each tick during the challenge */
    public abstract void onTick();
    
    /** Called when the challenge should be aborted */
    public abstract void onAbort();
    
    /** Called when the challenge is completed */
    public abstract void onComplete();
    
    public EntityPlayerMP getPlayer() {
        return player;
    }
}
