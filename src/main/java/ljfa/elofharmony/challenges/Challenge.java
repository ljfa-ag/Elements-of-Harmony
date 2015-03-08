package ljfa.elofharmony.challenges;

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
    
    /** @return true if the player is allowed to pick up this stack into the inventory */
    public abstract boolean mayPickUp(ItemStack stack);
    
    /** Called when the challenge should be aborted */
    public abstract void onAbort();
    
    /** Called when the challenge is completed */
    public abstract void onComplete();
    
    /** Writes the challenge data to NBT */
    public abstract void writeToNBT(NBTTagCompound tag);
    
    public EntityPlayerMP getPlayer() {
        return player;
    }
}
