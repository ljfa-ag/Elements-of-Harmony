package ljfa.elofharmony.challenges;

import ljfa.elofharmony.tile.TileRitualTable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public abstract class Challenge {
    public final int id;
    
    protected Challenge(int id) {
        challenges[id] = this;
        this.id = id;
    }
    
    /** @return if the challenge is ready to be started on the player */
    public abstract boolean checkStartingCondition(EntityPlayer player, TileRitualTable tile);
    
    /** Called when a challenge is about to start for the player. */
    public abstract void start(EntityPlayer player, NBTTagCompound data, TileRitualTable tile);
    
    /** @return true if the challenge's restriction is met */
    public abstract boolean checkRestriction(EntityPlayer player, NBTTagCompound data);
    
    /** @return true if the challenge's completion condition is met */
    public abstract boolean checkCondition(EntityPlayer player, NBTTagCompound data);
    
    /** Called each tick during the challenge */
    public abstract void tick(EntityPlayer player, NBTTagCompound data);
    
    /** Called when the challenge should be aborted */
    public abstract void abort(EntityPlayer player, NBTTagCompound data);
    
    /** Called when the challenge is completed */
    public abstract void complete(EntityPlayer player, NBTTagCompound data);
    
    /** @return the reward the player gets for completing the challenge */
    public abstract ItemStack getResult();
    
    private static Challenge[] challenges = new Challenge[6];
    
    public static Challenge fromId(int id) {
        return challenges[id];
    }
}
