package ljfa.elofharmony.challenges;

import ljfa.elofharmony.tile.TileRitualTable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public abstract class Challenge {
    public final int id;
    
    public static ChallengeGenerosity generosity;
    
    protected Challenge(int id) {
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
    public abstract void onTick(EntityPlayer player, NBTTagCompound data);
    
    /** Called when the challenge should be aborted */
    public abstract void onAbort(EntityPlayer player, NBTTagCompound data);
    
    /** Called when the challenge is completed */
    public abstract void onComplete(EntityPlayer player, NBTTagCompound data);
    
    /** @return the reward the player gets for completing the challenge */
    public abstract ItemStack getResult();
    
    private static Challenge[] challenges;
    
    public static void initChallenges() {
        challenges = new Challenge[6];
        challenges[4] = generosity = new ChallengeGenerosity(4);
    }
    
    public static Challenge fromId(int id) {
        return challenges[id];
    }
}
